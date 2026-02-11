package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connexion {
    private static Connexion instance;
    private final Connection conn;

    private static final String URL  = "jdbc:mysql://localhost:3306/atelier?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connexion() throws SQLException {
        try {
            // Charger explicitement le driver MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL introuvable. Veuillez ajouter mysql-connector-j.jar au classpath", e);
        }
        conn = DriverManager.getConnection(URL, USER, PASS);
        conn.setAutoCommit(true);
    }

    public static synchronized Connexion getInstance() throws SQLException {
        if (instance == null || instance.conn.isClosed()) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
