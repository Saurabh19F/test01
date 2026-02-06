package com.localexplorer.api.repository;

import com.localexplorer.api.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface PlaceRepository extends MongoRepository<Place, String> {

    // Places near point within meters (Mongo uses radians internally; we’ll use $nearSphere with $maxDistance in meters)
    @Query("{ 'location': { $nearSphere: { $geometry: ?0, $maxDistance: ?1 } }, 'category': ?2 }")
    List<Place> findNearbyByCategory(GeoJsonPoint point, double maxDistanceMeters, String category);

    @Query("{ 'location': { $nearSphere: { $geometry: ?0, $maxDistance: ?1 } } }")
    List<Place> findNearby(GeoJsonPoint point, double maxDistanceMeters);
}


