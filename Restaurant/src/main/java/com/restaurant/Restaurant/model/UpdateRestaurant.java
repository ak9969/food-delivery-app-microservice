package com.restaurant.Restaurant.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateRestaurant {
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
}
