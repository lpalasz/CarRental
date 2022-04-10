package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = session.createNativeQuery("SELECT version();").getSingleResult().toString();
        System.out.println(result);
        session.close();
        HibernateUtil.shutdown();


    }
}
