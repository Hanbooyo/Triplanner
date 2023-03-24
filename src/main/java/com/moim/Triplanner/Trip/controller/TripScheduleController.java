package com.moim.Triplanner.Trip.controller;

import com.moim.Triplanner.Trip.domain.TripSchedule;
import com.moim.Triplanner.Trip.service.TripScheduleService;
import com.moim.Triplanner.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create")
    public String create(@ModelAttribute("tripSchedule") TripSchedule tripSchedule, BindingResult bindingResult, Model model) {
        System.out.println("Create호출");
        if (bindingResult.hasErrors()) {
            // 에러 발생 시 처리
            System.out.println("바인딩에러");
            model.addAttribute("tripSchedules", tripScheduleService.getAllTripSchedules());
            return "test";
        }
        User user = new User();
        user.setUsername("tester");
        tripSchedule.setUser(user);

        tripScheduleService.createTripSchedule(tripSchedule);
        System.out.println("createTrip");
        List<TripSchedule> tripSchedules = tripScheduleService.getAllTripSchedules();
        model.addAttribute("tripSchedules", tripSchedules);
        return "list";
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
    public String getScheduleForm(Model model) {
        model.addAttribute("tripSchedule", new TripSchedule()); // TripSchedule 객체 생성 및 모델에 추가
        return "test";
    }

}
