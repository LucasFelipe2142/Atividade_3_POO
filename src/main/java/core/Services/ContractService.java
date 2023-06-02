package core.Services;

import Sql.SQLConection;
import Sql.SQLQueries;
import core.Client;
import core.ClientService;
import core.Contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractService {

    private ClientService clientService;
    public Contract getContract(long id) {
        try (Connection connection = SQLConection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.getContractQuery());
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long contractId = resultSet.getLong("id");
                String redacao = resultSet.getString("redacao");
                LocalDate ultimaAtualizacao = resultSet.getDate("ultimaAtualizacao").toLocalDate();
                // Obtém o cliente associado ao contrato
                long clienteId = resultSet.getLong("cliente_id");

                return new Contract(contractId, redacao, ultimaAtualizacao, clienteId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contract> getAllContracts() {
        List<Contract> contractsList = new ArrayList<>();
        try (Connection connection = SQLConection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.getAllContractQuery());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long contractId = resultSet.getLong("id");
                String redacao = resultSet.getString("redacao");
                LocalDate ultimaAtualizacao = resultSet.getDate("ultimaAtualizacao").toLocalDate();
                // Obtém o cliente associado ao contrato
                long clienteId = resultSet.getLong("cliente_id");

                Contract contract = new Contract(contractId, redacao, ultimaAtualizacao, clienteId);
                contractsList.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractsList;
    }

    public Map<String, Object> postContract(Contract contract, Long client_id) {
        Map<String, Object> result = new HashMap<>();
        try (Connection connection = SQLConection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.postContractQuery(),
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, contract.getRedacao());
            statement.setDate(2, java.sql.Date.valueOf(contract.getUltimaAtualizacao()));
            statement.setLong(3, client_id );
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

    public Map<String, Object> updateContract(Contract contract, long id, Long client_id) {
        Map<String, Object> result = new HashMap<>();
        try (Connection connection = SQLConection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.putContractQuery());
            statement.setString(1, contract.getRedacao());
            statement.setDate(2, java.sql.Date.valueOf(contract.getUltimaAtualizacao()));
            statement.setLong(3, client_id);
            statement.setLong(4, id);
            statement.executeUpdate();

            result.put("status", 200);
        } catch (SQLException e) {
            e.printStackTrace();
            result.put("status", 500);

        }
        return result;
    }
    public Map<String, Object> deleteContract(long id) {
        Map<String, Object> result = new HashMap<>();
        try (Connection connection = SQLConection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.deleteContractQuery());
            statement.setLong(1, id);
            statement.executeUpdate();

            result.put("status", 200);
        } catch (SQLException e) {
            e.printStackTrace();
            result.put("status", 500);
        }
        return result;
    }

}