package core;

import Sql.SQLConection;
import Sql.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {

    private long id;

    private long cpf;

    private String name;



    public Client(long id, long cpf, String name){
        this.id = id;
        this.cpf = cpf;
        this.name = name;
    }

    public static Client getClient() {
        try (Connection connection = SQLConection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQueries.getClientsQuery());
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                return new Client(id, cpf, nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> getAllClients(){
        List<Client> clientsList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        try(Connection connection = SQLConection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQueries.getAllClientsQuery());
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                Client client =  new Client(id, cpf, nome);
                clientsList.add(client);
            }
            result.put("status", 200);
            result.put("body", clientsList);
        } catch (SQLException e){
            result.put("status", 500);
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> postClient() {
        Map<String, Object> result = new HashMap<>();
        try (Connection connection = SQLConection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQLQueries.postClientsQuery(),
                    Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, this.cpf);
            statement.setString(2, this.name);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                result.put("status", 200);
                result.put("id", id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.put("status", 500);
        }
        return result;
    }

    public Map<String, Object> updateClient(final long id){
        try (Connection connection = SQLConection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.putClientsQuery());
            statement.setLong(1, this.cpf);
            statement.setString(2, this.name);
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteClient(final long id) {
        try (Connection connection = SQLConection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.deleteClientsQuery());
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
