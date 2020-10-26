package com.myoldschool.manager;

import com.myoldschool.manager.api.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {

    private static ApplicationContext context;

    public static void main(String[] args) {

        context = new AnnotationConfigApplicationContext(Config.class);

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
