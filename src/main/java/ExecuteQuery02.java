import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");//driver a kayit ol

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Endemik38*");
        Statement st = con.createStatement();

//1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        String sql = "select   company,number_of_employees from companies\n" +
                "order by number_of_employees desc\n" +
                "offset  1 limit 1";
        /*String sqlalternatif="select   company,number_of_employees from companies\n" +
                "where number_of_employees =(SELECT MAX(number_of_employees)FROM companies\n" +
                "where number_of_employees<(select max(number_of_employees)from companies ))";*/ //ikinci yol

        ResultSet w = st.executeQuery(sql);
        while (w.next()) {
            System.out.println(w.getString(1) + " " + w.getInt(2));
        }


        con.close();
        st.close();

        w.close();
    }
}
