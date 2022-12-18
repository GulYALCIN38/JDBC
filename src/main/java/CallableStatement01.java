import java.sql.*;

public class CallableStatement01 {
    /*
        java da methotlar return typ sahibi olsa da olmasa da methd olarak adlandirilir
        sql de data retuen ediyorsa " funttion" denir return yapmiyorsa " procedure" olarak adlandirilir
         */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");//driver a kayit ol

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Endemik38*");
        Statement st = con.createStatement();
        // callable statement ile function cagirmayi parametelendirecegiz .
        // 1. adim function codunu yaz
        String sql1 = "\n" +
                "\t   CREATE  OR REPLACE FUNCTION toplamaF(X numeric,y numeric)\n" +
                "\t   \n" +
                "\t   returns numeric\n" +
                "\t   language plpgsql\n" +
                "\t   as\n" +
                "\t   $$\n" +
                "\t   begin\n" +
                "\t   return x+y;\n" +
                "\t   \n" +
                "\t   end\n" +
                "\t   \n" +
                "\t   $$";


        // 2. adim function calistir
        st.execute(sql1);

        //3. adim function cagir.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?,?)}");

        //4. ADIM;  RETURN ICIN  registerOutParameter() methodunu, parametreler icin ise set()..methotlar kullanacagiz

        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);
        //https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm // BURADAN UYGUN ESLESEN DTA TIPLERINE BAK

        // execute() ile callabalStatement calistir
        cst1.execute();

        // 6. adim sonucu cagirmak icin return data tipine gotre

        System.out.println(cst1.getBigDecimal(1));

        //2. ornek koninin hacmini hesaplayan formul yazin
        String sql2 = "\n" +
                "\t   CREATE  OR REPLACE FUNCTION konihacim(r numeric,h numeric)\n" +
                "\t   \n" +
                "\t   returns numeric\n" +
                "\t   language plpgsql\n" +
                "\t   as\n" +
                "\t   $$\n" +
                "\t   begin\n" +
                "\t   return 3.14*r*r*h/3;\n" +
                "\t   \n" +
                "\t   end\n" +
                "\t   \n" +
                "\t   $$";
        st.execute(sql2);
        CallableStatement cst2 = con.prepareCall("{? = call konihacim(?,?)}");
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 1);
        cst2.setInt(3, 6);
        cst2.execute();
        System.out.printf("%.2f",cst2.getBigDecimal(1));
        System.out.println();
        System.out.printf("%.2f",12315.123112);
        con.close();
        st.close();
        cst1.close();
        cst2.close();
    }
}
