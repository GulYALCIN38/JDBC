import java.sql.*;

public class ExequteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");//driver a kayit ol

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Endemik38*");
        Statement st = con.createStatement();

//1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql = "select country_name  from  countries where region_id=1;";


        boolean sql1 = st.execute(sql);
        System.out.println("sql1 = " + sql1); // trueverir cunku data aldik


        // recordlari gormek icin executeQuery () methodu kullaniriz
        ResultSet result=st.executeQuery(sql);
        while(result.next()){
            System.out.print(result.getString(1)+" ");//get string icne colon no yaz


        }
        //
//2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 ="select country_name,country_id from  countries\n" +
                "where region_id>2 ";
        ResultSet result2=st.executeQuery(sql2);
        while(result2.next()){
            System.out.println(result2.getString(1) +" "+ result2.getString(2)); // 1 ve 2 yeine country_name,country_id


        }


        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın
String sql3="SELECT * FROM companies\n" +
        "\n" +
        "where number_of_employees=(select min(number_of_employees)from  companies)";

        ResultSet result3=st.executeQuery(sql3);
        while(result3.next()){
            System.out.println(result3.getInt(1) + " " + result3.getString(2) + " " + result3.getInt(3));


        }

        con.close();
        st.close();
    }
}
