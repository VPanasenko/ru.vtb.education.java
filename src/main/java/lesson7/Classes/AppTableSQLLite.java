package lesson7.Classes;

import lesson7.Annotations.AppColumn;
import lesson7.Annotations.AppTable;

import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class AppTableSQLLite<T extends SQLClass> implements AppTableManager<T> {
    private static String ConnectionString = "jdbc:sqlite:src/main/java/lesson7/demobase.db";
    private static Connection connection;
    private static Statement statement;

    private Class ClassToManage;

    private Map<Class, String> converter = new HashMap<Class, String>() {{
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
            sb.append(ClassToManage.getSimpleName());
            sb.append(" (");
            sb.append(getAllFields(ClassToManage));
            sb.append(");");
            String result = sb.toString();
            System.out.println(result);
            statement.executeUpdate(result);
        }
    }

    private void insertStatement(T obj) throws SQLException, IllegalAccessException, NoSuchFieldException {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(ClassToManage.getSimpleName());
        sb.append(" (");
        ArrayList<Field> fields = getAppColumnFields(ClassToManage);
        StringBuilder vb = new StringBuilder(") VALUES (");
        for (Field f : fields) {
            String fieldName = f.getName();
            if (!fieldName.equalsIgnoreCase("id")) {
                sb.append(fieldName);
                sb.append(", ");
                // Вот в таком виде вытаскивается значение поля родителя (на примере поля Name) SQLClass, а не ребёнка, например, Cat.
                // vb.append(f.get(obj));
                // Приходится получать через выборку этого поля у ребёнка.
                // А в случае ошибки получать у родителя.
                Object vbValueToAppend = "";
                try {
                    Field fieldChild = ClassToManage.getField(fieldName);
                    vbValueToAppend = fieldChild.get(obj);
                } catch (NoSuchFieldException ex) {
                    vbValueToAppend = f.get(obj);
                }
                if (getFieldType(f).equals("TEXT")) {
                    vb.append("'");
                    vb.append(vbValueToAppend);
                    vb.append("'");
                }
                else{
                    vb.append(vbValueToAppend);
                }
                vb.append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        vb.setLength(vb.length() - 2);
        sb.append(vb);
        sb.append(");");
        String result = sb.toString();
        System.out.println(result);
        statement.executeUpdate(result);
    }

    private void readStatement() throws SQLException {
        try (ResultSet rs = statement.executeQuery("SELECT * FROM " + ClassToManage.getSimpleName() + ";")) {
            ArrayList<Field> fields = getAppColumnFields(ClassToManage);
            if (!fields.isEmpty()) {
                while (rs.next()) {
                    StringBuilder sb = new StringBuilder();
                    for (Field f : fields) {
                        String fieldName = f.getName();
                        sb.append(fieldName);
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

    private String getAllFields(Class cl) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Field> fields = getAppColumnFields(cl);
        for (Field f : fields) {
            String fieldName = f.getName();
            if (fieldName.equalsIgnoreCase("id")) {
                sb.insert(0, " PRIMARY KEY AUTOINCREMENT, ");
                sb.insert(0, converter.get(f.getType()));
                sb.insert(0, " ");
                sb.insert(0, fieldName);
            } else {
                sb.append(fieldName);
                sb.append(" ");
                sb.append(converter.get(f.getType()));
                sb.append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private ArrayList<Field> getAppColumnFields(Class cl) {
        Field[] fields = new Field[]{};
        if (cl != null) {
            fields = cl.getDeclaredFields();
        }
        ArrayList<Field> fieldsArray = new ArrayList<>(Arrays.asList(fields));
        if (cl.getSuperclass() != null) {
            if (cl.getSuperclass() != Object.class) {
                fieldsArray.addAll(getAppColumnFields(cl.getSuperclass()));
            }
        }
        Class[] interfaces = cl.getInterfaces();
        if (interfaces.length > 0) {
            for (Class c : interfaces) {
                fieldsArray.addAll(getAppColumnFields(c));
            }
        }
        List<Field> fieldsList = fieldsArray.stream().filter(f -> f.isAnnotationPresent(AppColumn.class)).collect(Collectors.toList());
        fieldsList.stream().distinct().collect(Collectors.toList());
        return new ArrayList<>(fieldsList);
    }
}
