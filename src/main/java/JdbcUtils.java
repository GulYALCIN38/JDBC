import java.sql.*;

public class JdbcUtils {


    private static Connection connection;
    private static Statement statement;


    //1. Adım: Driver'a kaydol
    //2. Adım: Datbase'e bağlan
    public static Connection connectToDataBase(String hostName, String dbName, String username, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null) {
            System.out.println("Connection Success");
        } else {
            System.out.println("Connection Fail");
        }

        return connection;
    }

    //3. Adım: Statement oluştur.
    public static Statement createStatement() {


        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return statement;
    }

    // 4. adim query calistir
    public static boolean execute(String sql) {
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    // 5. ADIM BAGLANTI VE STATEMENT KAPAT
    public static void closeConSt() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("connection  et statement sont ferme");
            } else {
                System.out.println("Connection and statement NOT closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //odev  executecuery ve executeupdate methodu yap ,, insert into methodunu olustur
    // table olusturan methot


    public static void createTable(String tablename, String... columnName_datatype) {
        StringBuilder columnName_datavalue = new StringBuilder("");
        for (String w : columnName_datatype) {
            columnName_datavalue.append(w).append(",");

        }
        columnName_datavalue.deleteCharAt(columnName_datavalue.length() - 1);
        System.out.println(columnName_datavalue);

        try {
            statement.execute("CREATE TABLE  " + tablename + "(" + columnName_datavalue + ")");
            System.out.println(tablename + " " + "la table a ete cree");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeQuery(String tableName, String... columnName) {
        StringBuilder columns = new StringBuilder("");
        for (String w : columnName) {
            columns.append(w).append(",");
        }
        columns.deleteCharAt(columns.length() - 1);

        Statement statement = JdbcUtils.createStatement();

        try {
            String sql2 = "select   " + columns + " from " + tableName;
            ResultSet resultset1 = statement.executeQuery(sql2);
            while (resultset1.next()) {

                for (String j : columnName) {

                    System.out.print(resultset1.getObject(j) + " ");
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }

    public static void executeUpdate(String tableName, String setColumn, String newValues, String Condition) {

        Statement statement = JdbcUtils.createStatement();
        String sql1 = "  update " + tableName + " set " + setColumn + "=" + newValues + " where " + Condition + "";

        try {
            int desLignesQuiChange = statement.executeUpdate(sql1);
            System.out.println("desLignesQuiChange = " + desLignesQuiChange);
            System.out.println("Table updated succesfully! ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void insertInto(String nameTable, String... values) {

        StringBuilder deger = new StringBuilder("");
        for (String w : values) {

            deger.append("'").append(w).append("',");
        }
        deger.deleteCharAt(deger.length() - 1);

        String sqli = "Insert into "+nameTable+" values ("+deger+");";
        boolean h = false;
        try {
            h = statement.execute(sqli);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("h = " + h);

    }
    public static void insertvalues(String nameTable, String values) {




        String sqli = "Insert into "+nameTable+" values ("+values+");";
        JdbcUtils.execute(sqli);
        System.out.println("L'operation d'insertion a été éfectué avec succes");



    }




}
