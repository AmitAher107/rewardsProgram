package com.infosys.rewardsProgram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;


@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    public Transaction(Long customerId, double v, LocalDate of) {
    }

    private Long customerId;
    private Double amount;
    private LocalDate date;


    // Default no-argument constructor
    public Transaction() {
    }
    public Transaction(Long id, Long customerId, Double amount, LocalDate date) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
