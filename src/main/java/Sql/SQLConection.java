package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConection {
    private static final String URL = "jdbc:mysql://localhost/nome_do_banco_de_dados";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "106652";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
