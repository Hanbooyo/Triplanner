package com.moim.Triplanner.Path.Service;

import com.google.maps.model.DirectionsResult;
import com.moim.Triplanner.Path.VO.Location;
import com.moim.Triplanner.Path.VO.Schedule;

import java.util.List;

public interface RecommendationService {
    List<DirectionsResult> getDirections(List<Schedule> schedules, List<Location> locations);
}
