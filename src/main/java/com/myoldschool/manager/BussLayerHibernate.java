package com.myoldschool.manager;

import com.myoldschool.manager.api.Student;
import com.myoldschool.manager.api.StudentCount;
import com.myoldschool.manager.hibernate.StudentCountHibernate;
import com.myoldschool.manager.hibernate.StudentHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;

@Component
public class BussLayerHibernate {

    private Session session;
    private Transaction t;
    private SessionFactory factory;

    public BussLayerHibernate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        factory = meta.getSessionFactoryBuilder().build();
    }

    public ArrayList<Student> showData() {
        Query query = null;
        try {
            session = factory.openSession();
            String hql = "FROM StudentHibernate";
            query = session.createQuery(hql);
            return new ArrayList<Student>(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            session = null;
        }
        return null;
    }


    public String insertData(int id, String name, int rollno, Double marks) {

        try {
            session = factory.openSession();
            t = session.beginTransaction();

            StudentHibernate std = new StudentHibernate();
            std.setSid(id);
            std.setMarks(marks);
            std.setRollno(rollno);
            std.setSname(name);

            session.save(std);
            t.commit();

            if (session.getTransaction().getStatus() == TransactionStatus.COMMITTED) {
                return "Records inserted!";
            } else {
                return "Insertion failed!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Insertion failed!";
        } finally {
            session.close();
            session = null;
        }

//        return null;
    }

    public String updateData(int id, String name, int rollno, String marks) {
        try {
            session = factory.openSession();
            t = session.beginTransaction();


            StudentHibernate std = session.get(StudentHibernate.class, id);
            if (!name.isEmpty())
                std.setSname(name);
            if (rollno > 0)
                std.setRollno(rollno);
            if (!marks.isEmpty())
                std.setMarks(Double.valueOf(marks));

            session.update(std);
            t.commit();

            if (session.getTransaction().getStatus() == TransactionStatus.COMMITTED) {
                return "Records updated!";
            } else {
                return "Update failed!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Update failed!";
        } finally {
            session.close();
            session = null;
        }

//        return null;
    }

    @Cacheable(value = "student-cache", key = "'StudentCache'+#id")
    public StudentHibernate getRecord(int id){
        try{
            Thread.sleep(4000);
            session = factory.openSession();
            StudentHibernate std = session.get(StudentHibernate.class, id);
            return std;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            session = null;
        }
        return null;
    }

    public String deleteRecord(int id) {
        try {
            session = factory.openSession();
            t = session.beginTransaction();

            StudentHibernate std = session.get(StudentHibernate.class, id);
            session.delete(std);

            t.commit();
            if (session.getTransaction().getStatus() == TransactionStatus.COMMITTED) {
                return "Records deleted!";
            } else {
                return "Deletion failed!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Deletion failed!";
        } finally {
            session.close();
            session = null;
        }
    }

    public ArrayList<Student> callStudentProcedure(int count,
                                                   double marks,
                                                   String names,
                                                   int sid) {
        try {
            session = factory.openSession();
            Query query = session.createSQLQuery("CALL student (:count, :marks, :names, :sid)")
                    .addEntity(StudentCountHibernate.class)
                    .setParameter("count", count)
                    .setParameter("marks", marks)
                    .setParameter("names", names)
                    .setParameter("sid", sid);

            return new ArrayList<Student>(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            session = null;
        }
        return null;
    }

    public ArrayList<String> callTableStudentFunction() {
        try {
            ArrayList<String> names = new ArrayList<>();
            session = factory.openSession();

            // The below code works to execute the stored function in mysql database w.r.t to the table name.
            // The below code will result in concating the column sname and rollno together for all rows.

            Query query = session.createSQLQuery("SELECT name_rollnum(sname,rollno) FROM tbl_student");

            return new ArrayList<String>(query.list());

            // The below code works to execute only stored function in mysql database. It does not get result related
            // to any table. The below code will result only a single arraylist ["Spam 93"]

          /*  session.doWork(connection -> {
                try (CallableStatement function = connection.prepareCall(
                        "SELECT { ? = call name_rollnum(?,?) } FROM `tbl_student`")) {
                    function.registerOutParameter(1,Types.VARCHAR);
                    function.setString(2,"Spam");
                    function.setInt(3, 93);
                    function.execute();
                    names.add(function.getString(1));
                }
            });

            return names;*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            session = null;
        }
        return null;
    }


//    @org.springframework.data.jpa.repository.Query(value = "CALL student(:count, :marks, :names, :sid", nativeQuery = true)
//    public ArrayList<Student> callStudentProcedure(@Param("count")int count,
//                                                            @Param("marks")double marks,
//                                                            @Param("names")String names,
//                                                            @Param("sid")int sid){
//
//    }
}
