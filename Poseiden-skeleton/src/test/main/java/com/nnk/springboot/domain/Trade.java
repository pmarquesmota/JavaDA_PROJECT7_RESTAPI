package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @With
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Long  tradeId;
    @Column
    @Size(min = 5, message = "Minimum 5 caractères.")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date tradeDate;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date  creationDate;
    @Column
    String  revisionName;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date  revisionDate;
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
