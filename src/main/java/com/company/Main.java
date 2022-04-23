package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.EntityManager;
import java.util.Scanner;


public class Main {
    static void menu() {
        System.out.println("1. Add customer");
        System.out.println("2. Delete customer");
        System.out.println("3. Add a car");
        System.out.println("4. Remove a car");
        System.out.println("5. Rent a car");
    }
    //    public static void main(String[] args) {
//        final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        final EntityManager entityManager = sessionFactory.createEntityManager();
//
//
//
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Welcome to car rental, what would you like to do (Enter the number):");
        menu();
        Scanner scanner = new Scanner(System.in);

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                CustomerService addCustomer = new CustomerService();
                addCustomer.addCustomer();
                break;
            case "2":
                CustomerService deleteCustomer = new CustomerService();
                deleteCustomer.deleteCustomer();
                break;
            case "3":
                CarService addCar = new CarService();
                addCar.addCar();
                break;
            case "4":
                CarService removeCar = new CarService();
                removeCar.removeCar();
                break;
            case "5":
                break;
            default:
                System.out.println("Options not recognized");

        }


//
//
//
//    session.close();
//    HibernateUtil.shutdown();


    }
}

