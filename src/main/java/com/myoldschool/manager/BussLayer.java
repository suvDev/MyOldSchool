package com.myoldschool.manager;

import com.myoldschool.manager.api.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BussLayer {

    @Autowired
    DBManager db;

    public ArrayList<Student> showData(){
        return db.selectRecords();
    }

    public String insertData(int id, String name, int rollno, Double marks){
        String query = "insert into tbl_student(sid, sname, rollno, marks) values('"+id+"','"+name+"','"+rollno+"','"+marks+"')";
        if(db.recordsManipulation(query)){
            System.out.println("Records inserted!");
            return "Records inserted!";
        }else{
            System.out.println("Insertion failed!");
            return "Insertion failed!";
        }
    }

    public String updateData(int id, String name){
        String query = "update tbl_student set sname= '"+name+"' where sid = '"+id+"'";
        if(db.recordsManipulation(query)){
            System.out.println("Record updated!");
            return "Records updated!";
        }else{
            System.out.println("Update Failed!");
            return "Update Failed!";
        }
    }

    public String deleteRecord(int id){
        String query = "delete from tbl_student where sid = '"+id+"'";
        if(db.recordsManipulation(query)){
            System.out.println("Record deleted!");
            return "Record deleted!";
        }else{
            System.out.println("Deletion Failed!");
            return "Deletion Failed!";
        }
    }
}
