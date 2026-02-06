package com.localexplorer.api.service;

import com.localexplorer.api.dto.PlaceDtos;
import com.localexplorer.api.model.Place;
import com.localexplorer.api.repository.PlaceRepository;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public PlaceDtos.PlaceResponse create(PlaceDtos.CreatePlaceRequest req) {
        Place place = Place.builder()
                .name(req.name())
                .category(req.category().toLowerCase())
                .address(req.address())
                .city(req.city())
                .location(new GeoJsonPoint(req.lng(), req.lat())) // GeoJsonPoint(lng, lat)
                .tags(req.tags())
                .ratingAvg(0.0)
                .build();

        place = placeRepository.save(place);
        return toResponse(place);
    }

    public List<PlaceDtos.PlaceResponse> nearby(double lat, double lng, double radiusMeters, String category) {
        GeoJsonPoint p = new GeoJsonPoint(lng, lat);

        List<Place> places = (category == null || category.isBlank())
                ? placeRepository.findNearby(p, radiusMeters)
                : placeRepository.findNearbyByCategory(p, radiusMeters, category.toLowerCase());

        return places.stream().map(this::toResponse).toList();
    }

    private PlaceDtos.PlaceResponse toResponse(Place place) {
        return new PlaceDtos.PlaceResponse(
                place.getId(),
                place.getName(),
                place.getCategory(),
                place.getAddress(),
                place.getCity(),
                place.getLocation().getY(), // lat
                place.getLocation().getX(), // lng
                place.getTags(),
                place.getRatingAvg()
        );
    }
}

