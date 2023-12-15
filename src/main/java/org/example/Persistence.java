package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Persistence {

    private final String DB_DISK = "jdbc:h2:~/yahoo";
    private final String DB_MEMORY = "jdbc:h2:mem:~/yahoo";
    private final String USER = "sa";
    private final String PASS = "pass";

    private final Connection connection;

    private static Persistence instance;

    public static Persistence init(PersistanceOption option) {
        if (instance != null) {
            instance.close();
        }

        instance = new Persistence(option);

        return instance;
    }

    public static Persistence getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Datenbankverbindung ist nicht initialisiert");
        }

        return instance;
    }

    private Persistence(PersistanceOption option) {
        try {
            switch (option) {
                case DATABASE_DISK -> {
                    Class.forName("org.h2.Driver");
                    connection = DriverManager.getConnection(DB_DISK, USER, PASS);
                }
                case DATABASE_MEMORY -> {
                    Class.forName("org.h2.Driver");
                    connection = DriverManager.getConnection(DB_MEMORY, USER, PASS);
                }
                default -> connection = null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void clear() {
        if (connection == null) {
            return;
        }

        try (Statement statement = getConnection().createStatement()) {
            statement.execute("""
                    DROP TABLE IF EXISTS QUESTIONS;
                    CREATE TABLE QUESTIONS(ID BIGINT PRIMARY KEY, TOPIC BIGINT, TITLE VARCHAR, CONTENT VARCHAR, ANSWER VARCHAR);
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
