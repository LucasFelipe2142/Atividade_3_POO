package Sql;

public class SQLQueries {
    public static String getClientsQuery() {
        return "SELECT id, cpf, nome FROM Cliente WHERE id = ?";
    }

    public static String getAllClientsQuery() {
        return "SELECT id, cpf, nome FROM Cliente";
    }

    public static String postClientsQuery() {
        return "INSERT INTO Client (cpf, nome) VALUES (?, ?)";
    }

    public static String putClientsQuery() {
        return "UPDATE Client SET cpf = ?, nome = ? WHERE id = ?";
    }

    public static String deleteClientsQuery() {
        return "DELETE FROM Client WHERE id = ?";
    }

    public static String getContractQuery() {
        return "SELECT id, redacao, ultimaAtualizacao, cliente_id FROM Contract WHERE id = ?";
    }

    public static String getAllContractQuery() {
        return "SELECT id, redacao, ultimaAtualizacao, cliente_id FROM Contract";
    }

    public static String postContractQuery() {
        return "INSERT INTO Contract (redacao, ultimaAtualizacao, cliente_id) VALUES (?, ?, ?)";
    }

    public static String putContractQuery() {
        return "UPDATE Contract SET redacao = ?, ultimaAtualizacao = ?, cliente_id = ? WHERE id = ?";
    }

    public static String deleteContractQuery() {
        return "DELETE FROM Contract WHERE id = ?";
    }
}
