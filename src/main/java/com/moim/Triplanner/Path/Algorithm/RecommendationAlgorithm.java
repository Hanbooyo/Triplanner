package com.moim.Triplanner.Path.Algorithm;

import com.moim.Triplanner.Path.VO.Location;

import java.util.List;

public interface RecommendationAlgorithm {
    List<Location> getRecommendation(List<Location> locations);
}
