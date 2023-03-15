package com.moim.Triplanner.Trip.Repository;

import com.moim.Triplanner.Trip.VO.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUserId(Long userId);

    //특정 사용자의 여행일정조회
    List<Trip> findByUserIdOrderByStartDateAsc(Long userId);
    Optional<Trip> findByIdAndUserId(Long tripId, Long userId);
    //특정 여행 일정의 상세 정보 조회

    //여행 일정의 일자별 일정 저장
    @Modifying
    @Query("UPDATE Trip t SET t.scheduleMap = :scheduleMap WHERE t.tripId = :tripId")
    void updateScheduleMap(@Param("tripId") Long tripId, @Param("scheduleMap") Map<Date, String> scheduleMap);

    //특정 여행 일정의 일자별 일정 조회
    @Query("SELECT t.scheduleMap FROM Trip t WHERE t.tripId = :tripId")
    Map<Date, String> findScheduleMapByTripId(@Param("tripId") Long tripId);

    @Query("SELECT t FROM Trip t WHERE t.userId = :userId")
    List<Trip> findAllByUserId(Long userId);
}
