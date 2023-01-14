import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Emailtestmedunna {
    //  1-MedunnaMessageEmailTest
//User connects to the database
    //   JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
    //Statement statement = JdbcUtils.createStatement();
//User sends the query to get the names of "email" column from "cmessage" table
//Assert that there are some "cmessage" email "zeynep05@gmail.com".
//User closes the connection

    @Test
    public void emailTest() {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        // JdbcUtils.executeQuery("cmessage","email");
        List<Object> l = JdbcUtils.listeEkleme("cmessage", "email");
        Assert.assertTrue(l.contains("zeynep05@gmail.com"));
        JdbcUtils.closeConSt();

    }

    @Test
    public void medunnapatientTest() throws SQLException {
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        //2- MedunnaPatientTest


        //User sends the query to get the names of "patient_id" column from "appointment" table
        String sql = "select patient_id from appointment";
        ResultSet rs = statement.executeQuery(sql);
        List<Integer> l = new ArrayList<>();
        while (rs.next()) {
            l.add(rs.getInt("patient_id"));
        }
        System.out.println(l.size());//20306


        //Assert that there are some appointment patient_id "405892".
        Assert.assertTrue(l.contains(405892));

        //Assert verify patients have 20295


        Assert.assertEquals(20295, l.size());


        //User closes the connection
        JdbcUtils.closeConSt();
    }

    @Test
    public void MedunnaStaffBirthDay() throws SQLException {

    //User connects to the database
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
          Statement statement = JdbcUtils.createStatement();
    //User sends the query to get the names of birth_date column from "staff" table
        String sql = "select birth_date from staff where birth_date='2022-12-03 23:00:00'";
        ResultSet rs = statement.executeQuery(sql);
        List<String> l = new ArrayList<>();
        while (rs.next()) {
            l.add(rs.getString("birth_date"));
        }
        System.out.println(l);

        //System.out.println(l);
        //Assert that there are some staff birth_date "2022-12-03 23:00:00".
        // Assert.assertEquals("2022-12-03 23:00:00",l.get(0));
        Assert.assertTrue(l.contains("2022-12-03 23:00:00"));
        //User closes the connection
        JdbcUtils.closeConSt();


    }

    @Test
    public void MedunnaColumnNameTest() throws SQLException {


        //4-MedunnaColumnNameTest
        //User connects to the database
        JdbcUtils.connectToDataBase("medunna.com", "medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the columns of room table
        String sql = "select * from room";
        
        boolean sorguYapildimi=statement.execute(sql);
        System.out.println("sorguYapildimi = " + sorguYapildimi);

        //Assert verify one of column name is "room_type"
        String sql2= "select room_type from room";
        boolean colonvarmi=statement.execute(sql2);
        Assert.assertTrue(colonvarmi);

        //User closes the connection
        JdbcUtils.closeConSt();
    }
}
