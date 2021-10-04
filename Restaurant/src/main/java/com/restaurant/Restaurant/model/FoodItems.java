package com.restaurant.Restaurant.model;

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
    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "food_id")
    private Long id;
    @NotBlank
    private String foodName;
    @NotNull
    private Double itemPrice;
    @NotNull
    private Integer quantity;
}






