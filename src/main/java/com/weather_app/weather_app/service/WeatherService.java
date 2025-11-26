package com.weather_app.weather_app.service;

import com.weather_app.weather_app.domain.CityCoordinates;
import com.weather_app.weather_app.domain.CityWeather;
import com.weather_app.weather_app.domain.WeatherRequestDetails;
import com.weather_app.weather_app.entity.WeatherResponse;
import com.weather_app.weather_app.provider.GeocodingProvider;
import com.weather_app.weather_app.provider.WeatherProvider;
import com.weather_app.weather_app.transformer.GeocodingCoordinatesTransformer;
import com.weather_app.weather_app.transformer.OpenWeatherTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private GeocodingProvider geocodingProvider;
    private GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    private WeatherProvider weatherProvider;
    private OpenWeatherTransformer openWeatherTransformer;

    @Autowired
    public WeatherService ( final GeocodingProvider geocodingProvider,
                            final GeocodingCoordinatesTransformer geocodingCoordinatesTransformer,
                            final WeatherProvider weatherProvider,
                            final OpenWeatherTransformer openWeatherTransformer) {
        this.geocodingProvider = geocodingProvider;
        this.geocodingCoordinatesTransformer = geocodingCoordinatesTransformer;
        this.weatherProvider = weatherProvider;
        this.openWeatherTransformer = openWeatherTransformer;
    }

    public WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails) throws Exception {
        // get latitude and longitude
        final CityCoordinates cityCoordinates = geocodingCoordinatesTransformer
                .transformToDomain(geocodingProvider.getCoordinates(weatherRequestDetails));

        // get weather for geo coordinates
        final CityWeather cityWeather = openWeatherTransformer
                .transformToDomain(weatherProvider.getWeather(cityCoordinates));

        // call another API

        return openWeatherTransformer.transformToEntity(cityWeather);
    }
}
