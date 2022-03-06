package com.nnk.springboot.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Integer  id;
    @Column
    Integer  curveId;
    @Column
    Timestamp asOfDate;
    @Column
    Double  term;
    @Column
    Double  value;
    @Column
    Timestamp  creationDate;

    public  CurvePoint(Integer curveId, Double term, Double value){
        this.curveId = curveId;
        this.asOfDate = Timestamp.from(ZonedDateTime.now().toInstant());
        this.term = term;
        this.value = value;
        this.creationDate = Timestamp.from(ZonedDateTime.now().toInstant());
    }
}
