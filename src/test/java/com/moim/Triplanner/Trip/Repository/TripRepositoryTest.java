package com.moim.Triplanner.Trip.Repository;

import com.moim.Triplanner.Trip.VO.Trip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TripRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    @Test
    public void testFindAllByUserId() {
        // given
        Long userId = 1L;
        Trip trip1 = new Trip(userId, Date.valueOf("2022-01-01"), Date.valueOf("2022-01-05"));
        Trip trip2 = new Trip(userId, Date.valueOf("2022-02-01"), Date.valueOf("2022-02-05"));
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.flush();

        // when
        List<Trip> trips = tripRepository.findAllByUserId(userId);

        // then
        assertThat(trips).isNotEmpty();
        assertThat(trips.size()).isEqualTo(2);
    }

    @Test
    public void testFindByIdAndUserId() {
        // given
        Long userId = 1L;
        Trip trip1 = new Trip(userId, Date.valueOf("2022-01-01"), Date.valueOf("2022-01-05"));
        entityManager.persist(trip1);
        entityManager.flush();

        // when
        Optional<Trip> trip = tripRepository.findByIdAndUserId(trip1.getTripId(), userId);

        // then
        assertThat(trip).isPresent();
        assertThat(trip.get().getTripId()).isEqualTo(trip1.getTripId());
    }

    @Test
    public void testUpdateScheduleMap() {
        // given
        Long userId = 1L;
        Map<Date, String> scheduleMap = new HashMap<>();
        scheduleMap.put(Date.valueOf("2022-01-01"), "서울");
        Trip trip1 = new Trip(userId, Date.valueOf("2022-01-01"), Date.valueOf("2022-01-05"));
        trip1.setScheduleMap(scheduleMap);
        entityManager.persist(trip1);
        entityManager.flush();

        Map<Date, String> newScheduleMap = new HashMap<>();
        newScheduleMap.put(Date.valueOf("2022-01-02"), "부산");

        // when
        tripRepository.updateScheduleMap(trip1.getTripId(), newScheduleMap);
        entityManager.flush();

        // then
        Map<Date, String> updatedScheduleMap = tripRepository.findScheduleMapByTripId(trip1.getTripId());
        assertThat(updatedScheduleMap).isNotEmpty();
        assertThat(updatedScheduleMap.get(Date.valueOf("2022-01-02"))).isEqualTo("부산");
    }

    @Test
    public void testFindByUserIdOrderByStartDateAsc() {
        // given
        Long userId = 1L;
        Trip trip1 = new Trip(userId, Date.valueOf("2022-02-01"), Date.valueOf("2022-02-05"));
        Trip trip2 = new Trip(userId, Date.valueOf("2022-01-01"), Date.valueOf("2022-01-05"));
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.flush();

        // when
        List<Trip> trips = tripRepository.findByUserIdOrderByStartDateAsc(userId);

        // then
        assertThat(trips).isNotEmpty();
        assertThat(trips.size()).isEqualTo(2);
        assertThat(trips.get(0).getStartDate()).isEqualTo(Date.valueOf("2022-01-01"));
    }
}
