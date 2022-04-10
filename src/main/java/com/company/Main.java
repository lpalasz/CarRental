package com.company;

import org.hibernate.Session;


public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = session.createNativeQuery("SELECT version();").getSingleResult().toString();
        System.out.println(result);
        session.close();
        HibernateUtil.shutdown();

    }
}
