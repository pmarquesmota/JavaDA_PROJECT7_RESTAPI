package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @With
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Long id;
    @Column
    @Size(min = 5, message = "Minimum 5 caractères.")
    String  name;
    @Column
    String  description;
    @Column
    String  json;
    @Column
    String  template;
    @Column
    String  sqlStr;
    @Column
    String  sqlPart;

    public RuleName(String  name, String  description, String  json, String  template, String  sqlStr, String  sqlPart){
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }
}
