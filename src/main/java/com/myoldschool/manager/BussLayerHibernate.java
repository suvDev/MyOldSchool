package com.myoldschool.manager;

import com.myoldschool.manager.api.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BussLayerHibernate {

    private Session session;
    private Transaction t;
    private SessionFactory factory;

    public BussLayerHibernate(){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        factory = meta.getSessionFactoryBuilder().build();
//        t = session.beginTransaction();
    }

    @Autowired
    DBManager db;

    public ArrayList<Student> showData(){
        Query query = null;
        try{
            session = factory.openSession();
            String hql = "FROM StudentHibernate";
            query = session.createQuery(hql);
            return new ArrayList<Student>(query.list());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return null;
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
