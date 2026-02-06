package com.localexplorer.api.controller;

import com.localexplorer.api.dto.PlaceDtos;
import com.localexplorer.api.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    // Protected (needs token)
    @PostMapping
    public PlaceDtos.PlaceResponse create(@Valid @RequestBody PlaceDtos.CreatePlaceRequest req) {
        return placeService.create(req);
    }

    // Public
    @GetMapping("/nearby")
    public List<PlaceDtos.PlaceResponse> nearby(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "5000") double radiusMeters,
            @RequestParam(required = false) String category
    ) {
        return placeService.nearby(lat, lng, radiusMeters, category);
    }
}

