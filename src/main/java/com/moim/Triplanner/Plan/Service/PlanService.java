package com.moim.Triplanner.Plan.Service;

import com.moim.Triplanner.Plan.VO.PlanVO;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {

    PlanVO createPlan(PlanVO planVO);

    PlanVO getPlanById(Long id);

    List<PlanVO> getPlansByDate(LocalDate date);

    List<PlanVO> getAllPlans();

    PlanVO updatePlan(Long id, PlanVO planVO);

    void deletePlan(Long id);
}
