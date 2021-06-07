package lesson7;

import java.sql.*;

public class MainApp7ConnectionExample {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();
            createTableStatement();
            insertStatement();
            readStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/lesson7/demobase.db");
        statement = connection.createStatement();
    }

    private static void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void createTableStatement() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS students2 (\n" +
                "        id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "        name  TEXT,\n" +
                "        score INTEGER\n" +
                "    );");
    }

    private static void dropTableStatement() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS students2;");
    }

    private static void readStatement() throws SQLException {
        try (ResultSet rs = statement.executeQuery("SELECT * FROM students2;")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt(3));
            }
        }
    }

    private static void clearTableStatement() throws SQLException {
        statement.executeUpdate("DELETE FROM students2;");
    }

    private static void deleteStatement() throws SQLException {
        statement.executeUpdate("DELETE FROM students2 WHERE name = 'Bob1';");
    }

    private static void updateStatement() throws SQLException {
        statement.executeUpdate("UPDATE students2 SET score = 100 WHERE name = 'Bob4';");
    }

    private static void insertStatement() throws SQLException {
        statement.executeUpdate("INSERT INTO students2 (name, score) VALUES ('Bob4', 60);");
    }

}
