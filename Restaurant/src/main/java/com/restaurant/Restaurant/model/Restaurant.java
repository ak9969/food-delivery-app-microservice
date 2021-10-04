package com.restaurant.Restaurant.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    private Long restaurantId;

    @NotBlank
    private String restaurantName;

    @NotNull
    private Double rating;

    @NotNull
    private Double avgPrice;

    private Long locationId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private List<FoodItems> foodItemsList;
}
