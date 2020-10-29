package com.myoldschool.manager;

import com.myoldschool.manager.api.Student;
import com.myoldschool.manager.hibernate.StudentHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {

    private static ApplicationContext context;

    public static void main(String[] args) {

        context = new AnnotationConfigApplicationContext(Config.class);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentHibernate std = new StudentHibernate();
        std.setSid(199);
        std.setMarks(99.99);
        std.setRollno(324);
        std.setSname("Hibernate");

        session.save(std);
        t.commit();
        System.out.println("successfully saved");
        factory.close();
        session.close();

        BussLayer bs = context.getBean("bussLayer", BussLayer.class);
        ArrayList<Student> arrayList = bs.showData();

        for(Student st: arrayList){
            System.out.println("ID: " + st.getId());
            System.out.println("Name: " + st.getName());
            System.out.println("Roll Num: " + st.getRollno());
            System.out.println("Marks: " + st.getMarks());
            System.out.println("*****************************");
        }
//        bs.insertData(2,"Test insertion",221, 99.34);
//        bs.updateData(334, "Robert Downy JR!");

//        bs.deleteRecord(333);
//        bs.showData();
    }

}
