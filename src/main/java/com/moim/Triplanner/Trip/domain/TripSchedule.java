package com.moim.Triplanner.Trip.domain;

import com.moim.Triplanner.User.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "trip_schedule")
public class TripSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_trip_schedule")
    @SequenceGenerator(name = "seq_trip_schedule", sequenceName = "SEQ_TRIP_SCHEDULE", allocationSize = 1)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user = null;

    // getter, setter 메서드 생략
}
