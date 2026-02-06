package com.localexplorer.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PlaceDtos {

    public record CreatePlaceRequest(
            @NotBlank String name,
            @NotBlank String category,
            String address,
            String city,
            @NotNull Double lat,
            @NotNull Double lng,
            List<String> tags
    ) {}

    public record PlaceResponse(
            String id,
            String name,
            String category,
            String address,
            String city,
            double lat,
            double lng,
            List<String> tags,
            double ratingAvg
    ) {}
}

