package com.location.Location.service.imp;

import com.location.Location.model.Location;
import com.location.Location.repository.LocationRepository;
import com.location.Location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LocationServiceImp implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImp(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public Location updateLocation(Long id, Location location) {
        Optional<Location> getLocation = locationRepository.findById(id);
        Location updatedLocation = null;
        if(getLocation.isPresent()){
            updatedLocation = getLocation.get();
            updatedLocation.setLocationId(location.getLocationId());
            updatedLocation.setLocationName(location.getLocationName());
        }
        return updatedLocation;
    }
}
