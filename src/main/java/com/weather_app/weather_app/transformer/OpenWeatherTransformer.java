package com.weather_app.weather_app.transformer;
import com.weather_app.weather_app.domain.CityWeather;
import com.weather_app.weather_app.entity.OpenWeatherResponseEntity;
import com.weather_app.weather_app.entity.WeatherResponse;
import org.springframework.stereotype.Service;


@Service
public class OpenWeatherTransformer {

    public CityWeather transformToDomain(final OpenWeatherResponseEntity entity) {
        return CityWeather.builder()
                .weather(entity.getWeather()[0].getMain())
                .details(entity.getWeather()[0].getDescription())
                .build();
    }

    public WeatherResponse transformToEntity(final CityWeather cityWeather) {
        return WeatherResponse.builder()
                .weather(cityWeather.getWeather())
                .details(cityWeather.getDetails())
                .build();
    }
}
