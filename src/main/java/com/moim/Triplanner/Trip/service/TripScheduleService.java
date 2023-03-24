package com.moim.Triplanner.Trip.service;

import com.moim.Triplanner.Trip.domain.TripSchedule;
import com.moim.Triplanner.Trip.repository.TripScheduleRepository;
import com.moim.Triplanner.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripScheduleService {
    @Autowired
    private TripScheduleRepository tripScheduleRepository;

    // 모든 일정 조회
    public List<TripSchedule> getAllTripSchedules() {
        return tripScheduleRepository.findAll();
    }

    public TripSchedule getTripSchedule(Long scheduleId) {
        return tripScheduleRepository.findById(scheduleId).orElse(null);
    }

    public void createTripSchedule(TripSchedule tripSchedule) {
        tripScheduleRepository.save(tripSchedule);
        System.out.println("save 호출");
    }

    public void updateTripSchedule(TripSchedule tripSchedule) {
        tripScheduleRepository.save(tripSchedule);
    }

    public void deleteTripSchedule(Long scheduleId) {
        tripScheduleRepository.deleteById(scheduleId);
    }

    public List<TripSchedule> getTripSchedulesByUser(User user) {
        return tripScheduleRepository.findByUser(user);
    }
}
