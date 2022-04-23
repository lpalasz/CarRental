package com.company;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class RentalService {
        Scanner scanner = new Scanner(System.in);


    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void addOrder() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        boolean isCustomerCorrect = false;
        Rental rental = new Rental();
        while (!isCustomerCorrect) {
            try {
                System.out.print("Please enter customer ID: ");
                int customerId = scanner.nextInt();
                scanner.nextLine();
                Customer customer = entityManager.find(Customer.class, customerId);
                rental.setCustomer(customer);
                if (customer.getId() != null) {
                    isCustomerCorrect = true;
                }

            } catch (NullPointerException e) {
                System.out.println("Wrong Id.");
            }
        }
        boolean isCarCorrect = false;
        while (!isCarCorrect) {
            try {
                System.out.print("Please enter car ID: ");
                int carId = scanner.nextInt();
                scanner.nextLine();
                Car car = entityManager.find(Car.class,carId);

                if (car.getCarCondition().equals(carCondition.DAMAGED)) {
                     System.out.println("Car is damaged. Choose another car.");
                } else {
                    rental.setCar(car);
                if (car.getId() != null) {
                    isCarCorrect = true;
                }
                }

            } catch (NullPointerException e) {
                System.out.println("Wrong Id.");
            }
        }

        boolean isFromDateCorrect = false;
        while (!isFromDateCorrect) {
            try {
                System.out.println("Enter rental's start date (yyyy-MM-dd): ");
                String startDate = scanner.nextLine();
                LocalDate start = LocalDate.parse(startDate);
                if (LocalDate.now().isBefore(start) ) {
                    if (isStartDateAvailable(start, rental.getCar().getId())) {
                        rental.setFromDate(start);
                        isFromDateCorrect = true;
                    } else {
                        System.out.println("Car already booked for this date. See conflicting rentals above.");
                    }
                } else {
                    System.out.println("Incorrect start date.");
                }
            } catch (NullPointerException | DateTimeParseException | IllegalArgumentException e) {
                System.out.println("Wrong date format.");
            }
//
        }

        boolean isToDateCorrect = false;
        while (!isToDateCorrect) {
            try {
                System.out.println("Enter rental's end date (yyyy-MM-dd): ");
                String endDate = scanner.nextLine();
                LocalDate end = LocalDate.parse(endDate);
                LocalDate start = rental.getFromDate();
                if (start.isBefore(end) || start.equals(end)) {
                    if (isEndDateAvailable(start,end, rental.getCar().getId())) {
                        rental.setToDate(end);
                        isToDateCorrect = true;
                    } else {
                        System.out.println("Car already booked for this date. See conflicting rentals above.");
                    }
                } else {
                    System.out.println("Incorrect date.");
                }
            } catch (NullPointerException | DateTimeParseException | IllegalArgumentException e) {
                System.out.println("Wrong date format.");
            }
        }

        entityManager.getTransaction().begin();
        entityManager.persist(rental);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void updateOrder() {

    }
    public void returnCar() {

    }

    public boolean isStartDateAvailable(LocalDate start, int carID) {
        boolean isListEmpty;
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Rental> rentalList = entityManager.createQuery("select r from Rental r join fetch r.car where r.car.id = :carID AND " +
                        ":date between r.fromDate AND r.toDate", Rental.class)
                .setParameter("carID", carID)
                .setParameter("date",start)
                .getResultList();
        rentalList.forEach(x -> System.out.println("Rental id: " + x.getId() + ", CAR ID: " + x.getCar().getId() +
                ", START Date: " + x.getFromDate() + ", END Date: " + x.getToDate()));
        if (rentalList.isEmpty()) {
            isListEmpty = true;
        } else {
            isListEmpty = false;
        } return isListEmpty;
    }

    public boolean isEndDateAvailable(LocalDate start, LocalDate end, int carID) {
        boolean isListEmpty;
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Rental> rentalList = entityManager.createQuery("select r from Rental r join fetch r.car where r.car.id = :carID AND " +
                        "((:start between r.fromDate AND r.toDate) OR (:end between r.fromDate AND r.toDate) OR " +
                        "(r.fromDate >= :start AND r.toDate <= :end))", Rental.class)
                .setParameter("carID", carID)
                .setParameter("start",start)
                .setParameter("end", end)
                .getResultList();
        rentalList.forEach(x -> System.out.println("Rental id: " + x.getId() + ", CAR ID: " + x.getCar().getId() +
                ", START Date: " + x.getFromDate() + ", END Date: " + x.getToDate()));
        if (rentalList.isEmpty()) {
            isListEmpty = true;
        } else {
            isListEmpty = false;
        } return isListEmpty;
    }


}
