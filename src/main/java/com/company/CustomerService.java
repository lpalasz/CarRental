package com.company;

import org.hibernate.*;
import javax.persistence.EntityManager;
import java.util.Scanner;

public class CustomerService {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//    private static EntityManager entityManager = sessionFactory.createEntityManager();

    public void addCustomer(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.print("Enter your First Name: ");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Enter your Last Name: ");
        customer.setLastName(scanner.nextLine());
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void deleteCustomer() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer Id you want to delete: ");
        int customerId = scanner.nextInt();
        Customer customer = entityManager.find(Customer.class, customerId);
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

