package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.model.FoodItems;
import com.restaurant.Restaurant.model.Restaurant;
import com.restaurant.Restaurant.model.UpdateRestaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> findByLocation(Long locationId);

    List<Restaurant> findByLocationAndPrice(Long locationId,Double price);

    List<Restaurant> findByLocationAndRating(Long locationId,Double rating);

    List<Restaurant> findByLocationAndPriceAndRating(Long locationId,Double price, Double rating);

    Restaurant updateRestaurantsByRestaurantId(UpdateRestaurant restaurant, Long id);

    Restaurant updateFoodQuantity(Long restaurantId, Long foodId, FoodItems foodItems);
}
