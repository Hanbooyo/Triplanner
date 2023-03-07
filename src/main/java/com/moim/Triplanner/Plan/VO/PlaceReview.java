package com.moim.Triplanner.Plan.VO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLACE_REVIEW")
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private PlanPlace place;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    // getter, setter, toString 생략
}