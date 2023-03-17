package com.moim.Triplanner.Plan.Service;

import com.moim.Triplanner.Plan.Repository.PlanRepository;
import com.moim.Triplanner.Plan.VO.PlanVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public PlanVO createPlan(PlanVO planVO) {
        return planRepository.save(planVO);
    }

    @Override
    public PlanVO getPlanById(Long id) {
        Optional<PlanVO> optionalPlanVO = planRepository.findById(id);
        if (optionalPlanVO.isEmpty()) {
            throw new RuntimeException("Plan not found for the given id");
        }
        return optionalPlanVO.get();
    }

    @Override
    public List<PlanVO> getPlansByDate(LocalDate date) {
        return planRepository.findByDate(date);
    }


    @Override
    public List<PlanVO> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public PlanVO updatePlan(Long id, PlanVO planVO) {
        Optional<PlanVO> optionalPlanVO = planRepository.findById(id);
        if (optionalPlanVO.isEmpty()) {
            throw new RuntimeException("Plan not found for the given id");
        }
        PlanVO existingPlan = optionalPlanVO.get();
        existingPlan.setTitle(planVO.getTitle());
        existingPlan.setDate(planVO.getDate());
        existingPlan.setStartTime(planVO.getStartTime());
        existingPlan.setEndTime(planVO.getEndTime());
        existingPlan.setLocationName(planVO.getLocationName());
        existingPlan.setLatitude(planVO.getLatitude());
        existingPlan.setLongitude(planVO.getLongitude());
        existingPlan.setMemo(planVO.getMemo());
        return planRepository.save(existingPlan);
    }

    @Override
    public void deletePlan(Long id) {
        Optional<PlanVO> optionalPlanVO = planRepository.findById(id);
        if (optionalPlanVO.isEmpty()) {
            throw new RuntimeException("Plan not found for the given id");
        }
        planRepository.delete(optionalPlanVO.get());
    }
}
