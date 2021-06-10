package lesson7.Classes;

import lesson7.Annotations.AppColumn;
import lesson7.Annotations.AppTable;

import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class AppTableSQLLite<T extends SQLClass> implements AppTableManager<T> {
    private static String ConnectionString = "jdbc:sqlite:src/main/java/lesson7/demobase.db";
    private static Connection connection;
    private static Statement statement;

    private Class ClassToManage;

    private Map<Class, String> hashMap = new HashMap<Class, String> () {{
            put(int.class, "INTEGER");
            put(String.class, "TEXT");
            put(double.class, "REAL");
    }};

    public Class getClassToManage() {
        return ClassToManage;
    }

    public AppTableSQLLite(Class<T> cl) {
        ClassToManage = cl;
    }

    public void createTable() {
        try {
            connect();
            createTableStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void save(T obj) {
        try {
            connect();
            insertStatement(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void read() {
        try {
            connect();
            readStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(ConnectionString);
        statement = connection.createStatement();
    }

    private void disconnect() {
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

    private void createTableStatement() throws SQLException {
        if (ClassToManage.isAnnotationPresent(AppTable.class)) {
            StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
            sb.append(ClassToManage.getName());
            sb.append(" (");
            Field[] fields = ClassToManage.getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(AppColumn.class)) {
                    String fieldName = f.getName();
                    sb.append(fieldName);
                    sb.append(" ");
                    sb.append(hashMap.get(f.getType()));
                    if (fieldName.toLowerCase().equals("id")) {
                        sb.append("PRIMARY KEY AUTOINCREMENT");
                    }
                    sb.append(", ");
                }
            }
            sb.setLength(sb.length() - 2);
            sb.append(");");
            String result = sb.toString();
            System.out.println(result);
            statement.executeUpdate(result);
//        statement.executeUpdate("CREATE TABLE IF NOT EXISTS students2 (\n" +
//                "        id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//                "        name  TEXT,\n" +
//                "        score INTEGER\n" +
//                "    );");
        }
    }

    private void insertStatement(T obj) throws SQLException, IllegalAccessException {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(ClassToManage.getName());
        sb.append(" (");
        Field[] fields = ClassToManage.getDeclaredFields();
        StringBuilder vb = new StringBuilder(") VALUES (");
        for (Field f : fields) {
            if (f.isAnnotationPresent(AppColumn.class)) {
                String fieldName = f.getName();
                sb.append(fieldName);
                sb.append(", ");
                vb.append(f.get(obj));
                vb.append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append(vb);
        sb.append(");");
        //statement.executeUpdate("INSERT INTO students2 (name, score) VALUES ('Bob4', 60);");
    }

    private void readStatement() throws SQLException {
        try (ResultSet rs = statement.executeQuery("SELECT * FROM " + ClassToManage.getClass().getName() + ";")) {
            Field[] fields = ClassToManage.getDeclaredFields();
            if (fields.length > 0) {
                while (rs.next()) {
                    StringBuilder sb = new StringBuilder();
                    for (Field f : fields) {
                        String fieldName = f.getName();
                        sb.append(f);
                        sb.append(": ");
                        int fieldIndex = rs.findColumn(fieldName);
                        switch (getFieldType(f)) {
                            case "INTEGER":
                                sb.append(rs.getInt(fieldIndex));
                                break;
                            case "REAL":
                                sb.append(rs.getDouble(fieldIndex));
                                break;
                            case "BLOB":
                                sb.append(rs.getBlob(fieldIndex));
                                break;
                            default:
                                sb.append(rs.getString(fieldIndex));
                                break;
                        }
                        sb.append("; ");
                    }
                    sb.setLength(sb.length() - 2);
                    System.out.println(sb.toString());
                }
            }
        }
    }

    private void dropTableStatement() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS " + ClassToManage.getClass().getName() + ";");
    }

    private void clearTableStatement() throws SQLException {
        statement.executeUpdate("DELETE FROM " + ClassToManage.getClass().getName() + ";");
    }


    private void deleteStatement() throws SQLException {
        statement.executeUpdate("DELETE FROM students2 WHERE name = 'Bob1';");
    }

    private void updateStatement() throws SQLException {
        statement.executeUpdate("UPDATE students2 SET score = 100 WHERE name = 'Bob4';");
    }

    private String generateSQLTableCode(Class cl) {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    private String getFieldType(Field field) {
        Class fieldType = field.getType();

        // Странно, что ругается на Incompatible class в switch, но нормально в if
//        String result = "";
//        switch (fieldType) {
//            case String.class:
//            case Date.class:
//                result = "TEXT";
//                break;
//            case int.class:
//            case boolean.class:
//                result = "INTEGER";
//                break;
//            case double.class:
//            case float.class:
//                result = "REAL";
//                break;
//            default:
//                result = "BLOB";
//                break;
//        }
//        return result;

        if (fieldType == String.class || fieldType == Date.class) {
            return "TEXT";
        }
        if (fieldType == int.class || fieldType == boolean.class) {
            return "INTEGER";
        }
        if (fieldType == float.class || fieldType == double.class) {
            return "REAL";
        }
        return "BLOB";
    }
}
