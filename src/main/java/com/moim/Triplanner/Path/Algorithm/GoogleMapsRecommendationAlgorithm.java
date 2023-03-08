package com.moim.Triplanner.Path.Algorithm;

import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.moim.Triplanner.Path.VO.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleMapsRecommendationAlgorithm implements RecommendationAlgorithm{

    @Autowired
    private DirectionsService directionsService;

    @Override
    public List<Location> getRecommendation(List<Location> locations) {
        if (locations.size() <= 2) {
            return locations;
        }

        // 시작 지점 설정
        Location startLocation = locations.get(0);
        LatLng currentLatLng = new LatLng(startLocation.getLatitude(), startLocation.getLongitude());

        // 결과 저장 리스트
        List<Location> result = new ArrayList<>();
        result.add(startLocation);

        // 다음 위치 찾기
        for (int i = 0; i < locations.size() - 1; i++) {
            // getNextLocation() 호출 시 currentLatLng은 LatLng 타입으로 변환해서 전달
            Location nextLocation = getNextLocation(currentLatLng, locations.subList(i + 1, locations.size()));
            result.add(nextLocation);
            currentLatLng = new LatLng(nextLocation.getLatitude(), nextLocation.getLongitude());

        }


        return result;
    }

    private Location getNextLocation(LatLng currentLatLng, List<Location> locations) {
        Location nearestLocation = null;
        double shortestDistance = Double.MAX_VALUE;

        // 각 위치들과의 거리를 비교하여 가장 가까운 위치 찾기
        for (Location location : locations) {
            LatLng destLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            DirectionsResult directionsResult = directionsService.getDirections(currentLatLng, destLatLng);
            double distance = getTotalDistance(directionsResult);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestLocation = location;
            }
        }

        return nearestLocation;
    }

    private double getTotalDistance(DirectionsResult directionsResult) {
        double totalDistance = 0.0;
        for (DirectionsRoute route : directionsResult.routes) {
            for (DirectionsLeg leg : route.legs) {
                totalDistance += leg.distance.inMeters;
            }
        }
        return totalDistance;
    }
}
