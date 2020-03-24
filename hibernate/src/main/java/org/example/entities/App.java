package org.example.entities;

import java.util.List;
import org.example.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class App {
    public static void main( String[] args ) {
        Auto a1 = new Auto("BMW");
        Auto a2 = new Auto("AUDI");
        Auto a3 = new Auto("Honda");
        Auto a4 = new Auto("Kia");
        Auto a5 = new Auto("Mercedez Benz");

//        create(a1);
//        create(a2);
//        create(a3);
//        create(a4);
//        create(a5);
        System.out.println(getAuto(3));
        System.out.println(getAllAuto());
        a3.setName("Hyundai");
        update(a3);
        System.out.println(getAuto(3));

        HibernateUtil.shutdown();
    }

    public static void create(Auto auto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(auto);
        session.getTransaction().commit();
        session.close();
        System.out.println("Record was created successfully");
    }

    public static Auto getAuto(Integer auto_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Auto a = (Auto) session.get(Auto.class, auto_id);
        session.close();
        return a;
    }

    public static List<Auto> getAllAuto() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Auto> autos = session.createQuery("FROM Auto").list();
        session.close();
        return autos;
    }

    public static void update(Auto auto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Auto a = (Auto) session.load(Auto.class, auto.getId());
        session.update(a);
        session.getTransaction().commit();
        session.close();
        System.out.println("Record was updated successfully");
    }

    public static void deleteById(Integer auto_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query sql = session.createQuery("delete Auto where id = :id");
        sql.setParameter("id", auto_id).executeUpdate();
        session.close();
        System.out.println("Record was deleted successfully");
    }
}
