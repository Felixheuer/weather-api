package com.weather_app.weather_app.transformer;

import com.weather_app.weather_app.domain.CityCoordinates;
import com.weather_app.weather_app.entity.GeocodingCoordinatesEntity;
import org.springframework.stereotype.Service;

@Service
public class GeocodingCoordinatesTransformer {

    public CityCoordinates transformToDomain(final GeocodingCoordinatesEntity entity) {
        return CityCoordinates.builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}
