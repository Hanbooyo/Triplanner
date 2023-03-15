package com.moim.Triplanner.Trip.Service;

import com.moim.Triplanner.Trip.Repository.TripRepository;
import com.moim.Triplanner.Trip.VO.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(Trip trip) {
        // Trip 생성 로직
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Trip trip) {
        // Trip 수정 로직
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long tripId) {
        // Trip 삭제 로직
        tripRepository.deleteById(tripId);
    }
}
