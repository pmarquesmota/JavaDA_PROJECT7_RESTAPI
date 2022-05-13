package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bidlist")
public class BidList {
    @With
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Long id;

    // exemple : ne doit jamais être vide
    @NotBlank(message = "ce champ ne doit pas être vide")
    @Size(min = 5, message = "Minimum 5 caractères.")
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
    //@JsonFormat(pattern = "dd/mm/YYYY")
    @Column
    LocalDateTime  bidListDate;
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
    LocalDateTime  creationDate;
    @Column
    String  revisionName;
    @Column
    LocalDateTime revisionDate;
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
