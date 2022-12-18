import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.driver a kayit ol

        Class.forName("org.postgresql.Driver");
        // 2-Databes e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "Endemik38*");

        //3. statement olustur
        Statement st = con.createStatement();

        System.out.println("Connection Succes");

        // baglanti ve statement kapat
        // execute metot ddl(create alter drop table)ve dql(select) icin kullanilir
        // eger execute metot ddl icin kullanilirsa false doner
        // eger execute metot dql icin kullanilirsa result set alinirsa true  yoksa  false doner
        //
//1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin
        boolean sql1 = st.execute("CREATE TABLE workers(worker_id varchar(20), worker_name varchar(20),worker_salary int)");

        System.out.println("sql1 = " + sql1);// false verir cunku data cagirmiyoruz  sadece olusturuyoruz . tabloyu yapar ve false verir


        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        //st.execute("ALTER TABLE workers add worker_adres varchar(80)");
        String sql2 = "ALTER TABLE workers add worker_adres varchar(80)";
        boolean sql2b=st.execute(sql2);
        System.out.println("sql2 = " + sql2b);
//3. workers table yi sil
        String sql3 = "drop table workers";
        st.execute(sql3);

        // 5. adim baglanti ve statementi kapat
        con.close();
        st.close();

    }
}
