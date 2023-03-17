package com.moim.Triplanner.Plan.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;


import com.moim.Triplanner.Plan.VO.PlanVO;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<PlanVO, Long> {
    List<PlanVO> findByDate(LocalDate date);
}
