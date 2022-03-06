package com.nnk.springboot.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Integer  tradeId;
    @Column
    String  account;
    @Column
    String  type;
    @Column
    Double  buyQuantity;
    @Column
    Double  sellQuantity;
    @Column
    Double  buyPrice;
    @Column
    Double  sellPrice;
    @Column
    String  benchmark;
    @Column
    Timestamp tradeDate;
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

    public Trade(String  account, String  type){
        this.account = account;
        this.type = type;
    }
}
