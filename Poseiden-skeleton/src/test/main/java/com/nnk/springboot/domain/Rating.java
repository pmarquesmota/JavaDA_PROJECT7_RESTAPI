package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @With
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Long  id;
    @Column
    @Size(min = 5, message = "Minimum 5 caractères.")
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
