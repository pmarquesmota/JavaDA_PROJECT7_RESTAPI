package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
    @javax.persistence.Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    Long id;
    @Column
    String  account;
    @Column
    String  type;
    @Column
    Double  bidQuantity;
    @Column
    Double  askQuantity;
    @Column
    Double  bid;
    @Column
    Double  ask;
    @Column
    String  benchmark;
    @Column
    Timestamp  bidListDate;
    @Column
    String  commentary;
    @Column
    String  security;
    @Column
    String  status;
    @Column
    String  trader;
    @Column
    String  book;
    @Column
    String  creationName;
    @Column
    Timestamp  creationDate;
    @Column
    String  revisionName;
    @Column
    Timestamp  revisionDate;
    @Column
    String  dealName;
    @Column
    String  dealType;
    @Column
    String  sourceListId;
    @Column
    String  side;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
