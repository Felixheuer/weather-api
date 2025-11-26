package com.weather_app.weather_app.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WeatherResponse {
    private String weather;
    private String details;
}