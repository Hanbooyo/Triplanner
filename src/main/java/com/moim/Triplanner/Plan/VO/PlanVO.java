package com.moim.Triplanner.Plan.VO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "plan")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class PlanVO {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Title is required")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "Location name is required")
    @JsonProperty("locationName")
    private String locationName;

    @NotBlank(message = "Latitude is required")
    @JsonProperty("latitude")
    private String latitude;

    @NotBlank(message = "Longitude is required")
    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("startTime")
    private LocalTime startTime;

    @JsonProperty("endTime")
    private LocalTime endTime;

    @JsonProperty("memo")
    private String memo;

    public Long getId() {
        return id;
    }
}
