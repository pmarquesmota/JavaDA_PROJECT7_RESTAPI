package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @With
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Long  id;
    @Column
    Integer  curveId;
    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date asOfDate;
    @Column
    Double  term;
    @Column
    Double  value;
    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date  creationDate;

    public  CurvePoint(Integer curveId, Double term, Double value){
        this.curveId = curveId;
        this.asOfDate = Timestamp.from(ZonedDateTime.now().toInstant());
        this.term = term;
        this.value = value;
        this.creationDate = Timestamp.from(ZonedDateTime.now().toInstant());
    }
}
