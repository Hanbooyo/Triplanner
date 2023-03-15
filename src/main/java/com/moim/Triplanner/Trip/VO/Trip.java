package com.moim.Triplanner.Trip.VO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Map;

@Entity
@Table(name = "TRIP")
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRIP_ID")
    private Long tripId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    public Trip(Long userId, Date valueOf, Date valueOf1) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setScheduleMap(Map<java.sql.Date, String> scheduleMap) {
    }

    // 생성자, Getter, Setter 생략
}
