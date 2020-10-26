package com.myoldschool.manager;

import com.myoldschool.manager.api.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Component
public class DBManager {

    @Value("${dbManager.driver}")
    private String driver;

    @Value("${dbManager.url}")
    private String url;

    @Value("${dbManager.uid}")
    private String uid;

    @Value("${dbManager.password}")
    private String password;

    public ArrayList<Student> selectRecords(){
        ArrayList arr = new ArrayList<Student>();
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, uid, password);
            Statement smt = conn.createStatement();
            ResultSet result = smt.executeQuery("select * FROM tbl_student");
            while(result.next()){
                arr.add(new Student(result.getInt(1), result.getString(2),
                        result.getInt(3), result.getString(4)));
//                System.out.println("ID: "+ result.getInt(1));
//                System.out.println("Name: "+ result.getString(2));
//                System.out.println("Roll Num: "+ result.getInt(3));
//                System.out.println("Marks: "+result.getString(4));
//                System.out.println("*****************************");
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    public boolean recordsManipulation(String query){
        boolean flag  = false;
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, uid, password);
            Statement smt = conn.createStatement();
            flag  = smt.executeUpdate(query) > 0 ? true : false;
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


}
