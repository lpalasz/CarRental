package com.company;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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

    @OneToOne(mappedBy = "car",fetch = FetchType.EAGER)
    private Rental rental;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public com.company.carCondition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(com.company.carCondition carCondition) {
        this.carCondition = carCondition;
    }

    public BigDecimal getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(BigDecimal rentalCost) {
        this.rentalCost = rentalCost;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(licensePlate, car.licensePlate) && carCondition == car.carCondition && Objects.equals(rentalCost, car.rentalCost) && Objects.equals(rental, car.rental);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, color, licensePlate, carCondition, rentalCost, rental);
    }
}
