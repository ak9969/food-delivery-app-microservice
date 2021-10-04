package com.restaurant.Restaurant.repository;

import com.restaurant.Restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findAllByLocationId(Long locationId);

    List<Restaurant> findAllByLocationIdAndAvgPrice(Long locationId,Double price);

    List<Restaurant> findByLocationIdAndRating(Long locationId,Double rating);

    List<Restaurant> findByLocationIdAndAvgPriceAndRating(Long locationId, Double price, Double rating);
}
