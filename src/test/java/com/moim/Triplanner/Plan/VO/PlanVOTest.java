package com.moim.Triplanner.Plan.VO;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlanVOTest {

    @Test
    public void testGetterBuilderSetter() {
        // given
        String title = "Test title";
        String locationName = "Test location";
        String latitude = "123.456";
        String longitude = "789.012";
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(1);
        String memo = "Test memo";

        // when
        PlanVO planVO = PlanVO.builder()
                .title(title)
                .locationName(locationName)
                .latitude(latitude)
                .longitude(longitude)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .memo(memo)
                .build();

        // then
        assertNotNull(planVO);
        assertEquals(title, planVO.getTitle());
        assertEquals(locationName, planVO.getLocationName());
        assertEquals(latitude, planVO.getLatitude());
        assertEquals(longitude, planVO.getLongitude());
        assertEquals(date, planVO.getDate());
        assertEquals(startTime, planVO.getStartTime());
        assertEquals(endTime, planVO.getEndTime());
        assertEquals(memo, planVO.getMemo());
    }
}
