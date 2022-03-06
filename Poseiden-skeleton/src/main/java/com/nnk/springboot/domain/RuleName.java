package com.nnk.springboot.domain;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Integer id;
    @Column
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
