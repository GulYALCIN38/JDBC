import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Deneme {
    private static Connection connection;
    private static Statement statement;
   private static ResultSet resultset1;

public static void  executeQuery (String tableName, String... columnName){
    StringBuilder columns= new StringBuilder("");
    for(String w:columnName){
        columns.append(w).append(",");
    }
   columns.deleteCharAt(columns.length()-1);

    Statement statement=JdbcUtils.createStatement();

    try {
        String sql2="select   "+columns+" from "+tableName;
        ResultSet resultset1=statement.executeQuery(sql2);
        while(resultset1.next()){

            for (String j:columnName){

                System.out.print(resultset1.getObject(j)+" ");
            }

        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
public static void executeUpdate(String tableName,String setColumn,String newValues,String Condition){

    Statement statement= JdbcUtils.createStatement();
    String sql1="  update "+tableName+" set "+setColumn+"="+newValues+" where "+Condition+"";

    try {
        int desLignesQuiChange=statement.executeUpdate(sql1);
        System.out.println("desLignesQuiChange = " + desLignesQuiChange);
        System.out.println("Table updated succesfully! ");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }



}

}

