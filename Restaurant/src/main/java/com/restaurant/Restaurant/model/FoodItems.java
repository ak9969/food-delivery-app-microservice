package com.restaurant.Restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodItems {
    @Id
    private Long id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Restaurant restaurant;
    @NotBlank
    private String foodName;
    @NotNull
    private Double itemPrice;
    @NotNull
    private Integer quantity;
}






