package com.restaurant.Restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long restaurantId;

    @NotBlank
    private String restaurantName;

    @NotNull
    private Double rating;

    @NotNull
    private Double avgPrice;

    private Long locationId;

    @JsonManagedReference
    @OneToMany(mappedBy ="restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FoodItems> foodItemsList;
}
