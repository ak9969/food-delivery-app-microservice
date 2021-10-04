package com.location.Location.controller;

import com.location.Location.model.Location;
import com.location.Location.repository.LocationRepository;
import com.location.Location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationRepository locationRepository;
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationRepository locationRepository, LocationService locationService) {
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocation(){
        return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping({"/{Id}"})
    public ResponseEntity<Location> getLocationById(@PathVariable Long Id){
        Optional<Location> locationById = locationRepository.findById(Id);
        return locationById.map(location -> new ResponseEntity<>(location, HttpStatus.OK)).orElse(null);
    }
    @PostMapping({""})
    public ResponseEntity<Location> addLocation(@RequestBody Location location){
        locationRepository.save(location);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<Location> updateLocation(@PathVariable Long id,@RequestBody Location location){
        Location updatedLocation = locationService.updateLocation(id,location);
        locationRepository.save(updatedLocation);
        return new ResponseEntity<>(updatedLocation,HttpStatus.CREATED);
    }
}
