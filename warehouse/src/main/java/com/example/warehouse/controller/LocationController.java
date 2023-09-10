package com.example.warehouse.controller;


import com.example.warehouse.dto.LocationDto;
import com.example.warehouse.dto.PartDto;
import com.example.warehouse.model.Location;
import com.example.warehouse.model.Part;
import com.example.warehouse.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocationController {


    @Autowired
    private LocationRepository locationRepository;

    @PostMapping("/location")
    public LocationDto createLocation(@RequestBody LocationDto locationDto) {
        Location location = new Location();
        location.setPartsLocation(locationDto.getPartsLocation());
        location.setAmount(locationDto.getAmount());
        locationRepository.save(location);
        return locationDto;
    }
    @PutMapping("location/{id}")
    public LocationDto editLocation (@RequestBody LocationDto locationDto, @PathVariable Long id){
        Location location = locationRepository.getReferenceById(id);
        location.setPartsLocation(locationDto.getPartsLocation());
        location.setAmount(locationDto.getAmount());
        locationRepository.save(location);
        return locationDto;
    }
    @GetMapping("/location/{id}")
    public LocationDto getLocation(@PathVariable Long id) {
        LocationDto locationDto = new LocationDto();
        Location location = locationRepository.getReferenceById(id);
        locationDto.setPartsLocation(location.getPartsLocation());
        locationDto.setAmount(location.getAmount());
        return locationDto;
    }
    @DeleteMapping("/location/{id}")
    public Boolean deleteLocation(@PathVariable Long id) {

        locationRepository.deleteById(id);
        return true;
    }
}
