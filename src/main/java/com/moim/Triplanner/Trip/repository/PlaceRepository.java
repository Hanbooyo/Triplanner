package com.moim.Triplanner.Trip.repository;

import com.moim.Triplanner.Trip.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
