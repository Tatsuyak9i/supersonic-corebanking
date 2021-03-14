package com.redhat.supersonic.corebanking.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.math.BigDecimal;
import java.lang.String;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account extends PanacheEntity {
    @Column(name = "id")
    public String id;
    @Column(name = "balance")
    public BigDecimal balance;

}