package com.location.Location.service;

import com.location.Location.model.Location;
import org.springframework.stereotype.Service;


public interface LocationService {
    Location updateLocation(Long id, Location location);
}
