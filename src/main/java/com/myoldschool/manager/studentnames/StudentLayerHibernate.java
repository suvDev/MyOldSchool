package com.myoldschool.manager.studentnames;

import com.myoldschool.manager.api.StudentCount;
import com.myoldschool.manager.hibernate.StudentCountHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StudentLayerHibernate {

    private Session session;
    private Transaction t;
    private SessionFactory factory;

    public StudentLayerHibernate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        factory = meta.getSessionFactoryBuilder().build();
    }

    public ArrayList<StudentCount> showStudentCounts() {
        Query query = null;
        try {
            session = factory.openSession();
            String hql = "FROM StudentCountHibernate";
            query = session.createQuery(hql);
            return new ArrayList<StudentCount>(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            session = null;
        }
        return null;
    }

    public String insertData(int id, String name, int count) {
        try {
            session = factory.openSession();
            t = session.beginTransaction();

            StudentCountHibernate std = new StudentCountHibernate();
            std.setId(id);
            std.setNames(name);
            std.setCount(count);

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
    }
}
