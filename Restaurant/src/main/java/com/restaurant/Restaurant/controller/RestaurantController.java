package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.model.FoodItems;
import com.restaurant.Restaurant.model.Restaurant;
import com.restaurant.Restaurant.model.UpdateRestaurant;
import com.restaurant.Restaurant.repository.RestaurantRepository;
import com.restaurant.Restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository, RestaurantService restaurantService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }
    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return new ResponseEntity<>(restaurantRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{Id}")
    public ResponseEntity<Restaurant> getRestaurantsId(@PathVariable Long Id){
        Optional<Restaurant>findRestaurant =  restaurantRepository.findById(Id);
        return findRestaurant.map(restaurant -> new ResponseEntity<>(restaurant, HttpStatus.OK)).orElse(null);
    }

    @PostMapping("")
    public ResponseEntity<Restaurant> getRestaurantsById(@RequestBody Restaurant restaurant){
        restaurantRepository.save(restaurant);
        return new ResponseEntity<>(restaurant,HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Restaurant> updateRestaurantsById(@PathVariable Long Id,
                                                            @RequestBody UpdateRestaurant restaurant){
        Restaurant updatedRestaurant = restaurantService.updateRestaurantsByRestaurantId(restaurant,Id);
        System.out.println(updatedRestaurant);
        restaurantRepository.save(updatedRestaurant);
        return new ResponseEntity<>(updatedRestaurant,HttpStatus.CREATED);
    }

    @DeleteMapping("/{Id}")
    public void deleteRestaurantsById(@PathVariable Long Id){
        restaurantRepository.deleteById(Id);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsByLocation(@PathVariable Long locationId){
        return new ResponseEntity<>(
                restaurantService.findByLocation(locationId),
                HttpStatus.OK);
    }

    @GetMapping("/location/{locationId}/{rating}")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsByLocationAndRating(
            @PathVariable Long locationId,
            @PathVariable Double rating) {
        return new ResponseEntity<>(
                restaurantService.findByLocationAndRating(locationId, rating),
                HttpStatus.OK);
    }

    @GetMapping("/location/{locationId}/{avgPrice}")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsByLocationAndAvgPrice(
            @PathVariable Long locationId,
            @PathVariable Double avgPrice) {
        return new ResponseEntity<>(
                restaurantService.findByLocationAndPrice(locationId,avgPrice),
                HttpStatus.OK);
    }

    @GetMapping("/location/{locationId}/{rating}/{avgPrice}}")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsByLocationAvgPriceAndRating(
            @PathVariable Long locationId,
            @PathVariable Double avgPrice,
            @PathVariable Double rating) {
        return new ResponseEntity<>(
                restaurantService.findByLocationAndPriceAndRating(locationId,avgPrice,rating),
                HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/{foodId}")
    public ResponseEntity<Restaurant> updateFoodInRestaurant(@PathVariable Long foodId,
                                                             @PathVariable Long restaurantId,
                                                             @RequestBody FoodItems foodItems){
        return new ResponseEntity<>(restaurantService.updateFoodQuantity(restaurantId,foodId,foodItems),
                        HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}/{foodId}")
    public ResponseEntity<Restaurant> deleteFoodId(@PathVariable Long foodId,
                                                   @PathVariable Long restaurantId){
        Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);
        if(currentRestaurant.isPresent()){
            Restaurant getCurrentRestaurant = currentRestaurant.get();
            Set<FoodItems> listOfFoodItem = getCurrentRestaurant.getFoodItemsList();
            Optional<FoodItems> currentFoodItem = listOfFoodItem.stream()
                    .filter(s->s.getId().equals(foodId))
                    .findFirst();
            currentFoodItem.ifPresent(listOfFoodItem::remove);
            getCurrentRestaurant.setFoodItemsList(listOfFoodItem);
            restaurantRepository.save(getCurrentRestaurant);
            return new ResponseEntity<>(getCurrentRestaurant,HttpStatus.OK);
        }
        return null;
    }
}
