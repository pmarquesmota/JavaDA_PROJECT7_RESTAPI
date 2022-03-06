package com.nnk.springboot.domain;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Integer  id;
    @Column
    String  moodysRating;
    @Column
    String  sandPRating;
    @Column
    String  fitchRating;
    @Column
    Integer orderNumber;

    public Rating(String moodysRating, String  sandPRating, String  fitchRating, Integer orderNumber){
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}
