package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.model.FoodItems;
import com.restaurant.Restaurant.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    List<Restaurant> findByLocation(Long locationId);

    List<Restaurant> findByLocationAndPrice(Long locationId,Double price);

    List<Restaurant> findByLocationAndRating(Long locationId,Double rating);

    List<Restaurant> findByLocationAndPriceAndRating(Long locationId,Double price, Double rating);

    Restaurant updateRestaurantsByRestaurantId(Restaurant restaurant,Long id);

    Restaurant updateFoodQuantity(Long restaurantId, Long foodId, FoodItems foodItems);
}
