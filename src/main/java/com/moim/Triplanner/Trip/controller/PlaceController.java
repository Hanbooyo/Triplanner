package com.moim.Triplanner.Trip.controller;

import com.moim.Triplanner.Trip.domain.Place;
import com.moim.Triplanner.Trip.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("/{id}")
    public ResponseEntity<Place> findById(@PathVariable("id") Long id) {
        Place place = placeService.getPlace(id);
        return ResponseEntity.ok(place);
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody Place place) {
        placeService.createPlace(place);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Place place) {
        place.setId(id);
        placeService.updatePlace(place);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.ok().build();
    }
}
