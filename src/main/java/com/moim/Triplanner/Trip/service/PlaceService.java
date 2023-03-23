package com.moim.Triplanner.Trip.service;

import com.moim.Triplanner.Trip.domain.Place;
import com.moim.Triplanner.Trip.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElse(null);
    }

    public void createPlace(Place place) {
        placeRepository.save(place);
    }

    public void updatePlace(Place place) {
        placeRepository.save(place);
    }

    public void deletePlace(Long placeId) {
        placeRepository.deleteById(placeId);
    }
}
