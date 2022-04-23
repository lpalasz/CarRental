package com.company;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "is_damage")
    private boolean isDamage;

    @Column(name = "fine_cost")
    private BigDecimal fineCost;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    public Rental() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public boolean isDamage() {
        return isDamage;
    }

    public void setDamage(boolean damage) {
        isDamage = damage;
    }

    public BigDecimal getFineCost() {
        return fineCost;
    }

    public void setFineCost(BigDecimal fineCost) {
        this.fineCost = fineCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return isDamage == rental.isDamage && Objects.equals(id, rental.id) && Objects.equals(fromDate, rental.fromDate) && Objects.equals(toDate, rental.toDate) && Objects.equals(fineCost, rental.fineCost) && Objects.equals(customer, rental.customer) && Objects.equals(car, rental.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromDate, toDate, isDamage, fineCost, customer, car);
    }


}
