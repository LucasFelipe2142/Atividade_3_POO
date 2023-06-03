package Sql;

public class SQLQueries {
    public static String getClientsQuery() {
        return "SELECT id, cpf, name FROM clients WHERE id = ?";
    }

    public static String getAllClientsQuery() {
        return "SELECT id, cpf, name FROM clients";
    }

    public static String postClientsQuery() {
        return "INSERT INTO clients (cpf, name) VALUES (?, ?)";
    }

    public static String putClientsQuery() {
        return "UPDATE clients SET cpf = ?, name = ? WHERE id = ?";
    }

    public static String deleteClientsQuery() {
        return "DELETE FROM clients WHERE id = ?";
    }

    public static String getContractQuery() {
        return "SELECT id, redacao, ultimaAtualizacao, cliente_id FROM contracts WHERE id = ?";
    }

    public static String getAllContractQuery() {
        return "SELECT id, redacao, ultimaAtualizacao, cliente_id FROM contracts";
    }

    public static String postContractQuery() {
        return "INSERT INTO contracts (redacao, ultimaAtualizacao, cliente_id) VALUES (?, ?, ?)";
    }

    public static String putContractQuery() {
        return "UPDATE contracts SET redacao = ?, ultimaAtualizacao = ?, cliente_id = ? WHERE id = ?";
    }

    public static String deleteContractQuery() {
        return "DELETE FROM contracts WHERE id = ?";
    }
}
