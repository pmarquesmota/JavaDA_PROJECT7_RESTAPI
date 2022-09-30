package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    Date bidListDate;
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column
    Date creationDate;
    @Column
    String  revisionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    Date revisionDate;
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
