package com.moim.Triplanner.Trip.controller;

import com.moim.Triplanner.Trip.domain.TripSchedule;
import com.moim.Triplanner.Trip.service.TripScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedule")
public class TripScheduleController {
    @Autowired
    private TripScheduleService tripScheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<TripSchedule> findById(@PathVariable("id") Long id) {
        TripSchedule tripSchedule = tripScheduleService.getTripSchedule(id);
        return ResponseEntity.ok(tripSchedule);
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody TripSchedule tripSchedule) {
        tripScheduleService.createTripSchedule(tripSchedule);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody TripSchedule tripSchedule) {
        tripSchedule.setScheduleId(id);
        tripScheduleService.updateTripSchedule(tripSchedule);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        tripScheduleService.deleteTripSchedule(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
