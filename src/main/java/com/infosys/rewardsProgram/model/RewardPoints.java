package com.infosys.rewardsProgram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class RewardPoints {

    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;
    private String month;
    private Integer points;

    public RewardPoints(Long id, Long customerId, String month, Integer points) {
        this.id = id;
        this.customerId = customerId;
        this.month = month;
        this.points = points;
    }

    public RewardPoints() {
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}

