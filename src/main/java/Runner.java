import java.sql.Connection;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {

        JdbcUtils.connectToDataBase("localhost", "Techpro", "postgres", "Endemik38*");
        JdbcUtils.createStatement();
/*
Connection connection=JdbcUtils.connectToDataBase();
Statement statement=JdbcUtils.createStatement(); kullanmak isteyince boyle yap!!!!!!!!!!!!!!!!
 */

        //   4. ADIM BIR QUERY CALISTIR


       // JdbcUtils.createTable("School12","classes varchar(20)","teacherName VARCHAR(20)","id INT");
        //JdbcUtils.createTable("ABC","classes varchar(15)","teacherName VARCHAR(34)","id INT");
        //Deneme.table("COUNTRI","country_id CHAR(3)","country_name VARCHAR(50)");


        /*JdbcUtils.execute("CREATE TABLE countries\n" +
                "(\n" +
                "    country_id CHAR(3),\n" +
                "    country_name VARCHAR(50),\n" +
                "    region_id SMALLINT\n" +
                ");");*/
        // 5.baglanti ve statement kapat

       //JdbcUtils.insertInto("countries","sa","patagonya","5");
       JdbcUtils.insertvalues("countries","'jk','jameika',25");
       //JdbcUtils.executeQuery("countries","country_name");

        JdbcUtils.closeConSt();

    }
}
