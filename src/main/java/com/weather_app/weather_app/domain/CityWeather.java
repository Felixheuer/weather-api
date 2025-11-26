package com.weather_app.weather_app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CityWeather {
    private String weather;
    private String details;
}