package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Long id;

    // exemple : ne doit jamais être vide
    @NotBlank(message = "ce champ ne doit pas être vide")
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

    public BidList(String  account, String  type, Double  bidQuantity){
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
