package com.moim.Triplanner.Plan.Controller;

import com.moim.Triplanner.Plan.Service.PlanService;
import com.moim.Triplanner.Plan.VO.PlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/{id}")
    public PlanVO getPlanById(@PathVariable("id") Long id) {
        return planService.getPlanById(id);
    }

    @GetMapping("/plans/date")
    public ResponseEntity<List<PlanVO>> getPlansByDate(@RequestParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<PlanVO> plans = planService.getPlansByDate(parsedDate);
        return ResponseEntity.ok(plans);
    }


    @PostMapping
    public PlanVO createPlan(@Valid @RequestBody PlanVO planVO) {
        return planService.createPlan(planVO);
    }

    @PutMapping("/{id}")
    public PlanVO updatePlan(@PathVariable("id") Long id, @Valid @RequestBody PlanVO planVO) {
        return planService.updatePlan(id, planVO);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable("id") Long id) {
        planService.deletePlan(id);
    }
}
