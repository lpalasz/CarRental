package com.company;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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



}
