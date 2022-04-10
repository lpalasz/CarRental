package com.company;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    @Column(name="color")
    private String color;

    @Column(name="license_plate")
    private String licensePlate;

    @Column(name="car_condition")
    @Enumerated(EnumType.STRING)
    private carCondition carCondition;

    @Column(name = "rental_cost")
    private BigDecimal rentalCost;







}
