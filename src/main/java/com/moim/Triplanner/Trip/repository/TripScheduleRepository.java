package com.moim.Triplanner.Trip.repository;

import com.moim.Triplanner.Trip.domain.TripSchedule;
import com.moim.Triplanner.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
    List<TripSchedule> findByUser(User user);
}
