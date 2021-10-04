package com.restaurant.Restaurant.service.Imp;

import com.restaurant.Restaurant.model.FoodItems;
import com.restaurant.Restaurant.model.Restaurant;
import com.restaurant.Restaurant.repository.RestaurantRepository;
import com.restaurant.Restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RestaurantServiceImp implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImp(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findByLocation(Long locationId) {
        return restaurantRepository.findAllByLocationId(locationId);
    }

    @Override
    public List<Restaurant> findByLocationAndPrice(Long locationId, Double price) {
        return restaurantRepository.findAllByLocationIdAndAvgPrice(locationId,price);
    }

    @Override
    public List<Restaurant> findByLocationAndRating(Long locationId, Double rating) {
        return restaurantRepository.findByLocationIdAndRating(locationId,rating);
    }

    @Override
    public List<Restaurant> findByLocationAndPriceAndRating(Long locationId, Double price, Double rating) {
        return restaurantRepository.findByLocationIdAndAvgPriceAndRating(locationId,price,rating);
    }

    @Override
    public Restaurant updateRestaurantsByRestaurantId(Restaurant restaurant, Long id) {
        Optional<Restaurant> currentRestaurant = restaurantRepository.findById(id);
        Restaurant updatedRestaurant = null;
        if(currentRestaurant.isPresent()){
            updatedRestaurant = currentRestaurant.get();
            updatedRestaurant.setRestaurantId(restaurant.getRestaurantId());
            updatedRestaurant.setRestaurantName(restaurant.getRestaurantName());
            updatedRestaurant.setAvgPrice(restaurant.getAvgPrice());
            updatedRestaurant.setRating(restaurant.getRating());
            updatedRestaurant.setLocationId(restaurant.getLocationId());
            updatedRestaurant.setFoodItemsList(restaurant.getFoodItemsList());
        }
        return updatedRestaurant;

    }

    @Override
    public Restaurant updateFoodQuantity(Long restaurantId, Long foodId, FoodItems foodItems) {
        Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);
        Restaurant updatedFoodByRestaurant = null;
        if(currentRestaurant.isPresent()){
            updatedFoodByRestaurant = currentRestaurant.get();
            List<FoodItems> listOfFoodItems = updatedFoodByRestaurant.getFoodItemsList();
            Optional<FoodItems> orderedFoodItem = listOfFoodItems.stream()
                    .filter(s->s.getId().equals(foodId))
                    .findFirst();
            if(orderedFoodItem.isPresent()){
                FoodItems foodItem = orderedFoodItem.get();
                listOfFoodItems.remove(foodItem);
                if(foodItem.getQuantity()>=foodItems.getQuantity()){
                    foodItem.setQuantity(foodItem.getQuantity()-foodItems.getQuantity());
                    listOfFoodItems.add(foodItem);
                }
                else{
                    listOfFoodItems.add(foodItem);
                    return null;
                }
            }
        }
        return updatedFoodByRestaurant;
    }
}
