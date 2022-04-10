package com.company;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class CarService {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final EntityManager entityManager = sessionFactory.createEntityManager();


    public void addCar() {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();
        System.out.print("Insert brand: ");
        car.setBrand(scanner.nextLine());
        System.out.print("Insert model: ");
        car.setModel(scanner.nextLine());
        System.out.print("Insert color: ");
        car.setColor(scanner.nextLine());
        System.out.print("Insert license plate: ");
        car.setLicensePlate(scanner.nextLine());
        System.out.print("Insert car condition: working - w, damaged - d): ");
        String carCond = scanner.nextLine();
        if (carCond.equalsIgnoreCase("d")) {
            car.setCarCondition(carCondition.DAMAGED);
        } else {
            car.setCarCondition(carCondition.WORKING);
        }
        System.out.print("Insert rental cost: ");
        car.setRentalCost(scanner.nextBigDecimal());
        scanner.nextLine();
        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();

    }

    public void removeCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert car id that you wish to remove: ");
        int carId = scanner.nextInt();
        entityManager.getTransaction().begin();
        Car car = entityManager.find(Car.class, carId);
        entityManager.remove(car);
        entityManager.getTransaction().commit();

    }

    public void changeCarCondition() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert car id that needs condition update: ");
        int carId = scanner.nextInt();
        scanner.nextLine();
        entityManager.getTransaction().begin();
        Car car = entityManager.find(Car.class, carId);
        System.out.print("Insert car condition: working - w, damaged - d): ");
        String carCond = scanner.nextLine();
        if (carCond.equalsIgnoreCase("d")) {
            car.setCarCondition(carCondition.DAMAGED);
        } else {
            car.setCarCondition(carCondition.WORKING);
        }
        entityManager.merge(car);
        entityManager.getTransaction().commit();


    }
}