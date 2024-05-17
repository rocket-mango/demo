package com.demogroup.demoweb.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class WeatherDto {
    private double humidity;
    private double precipitation;
    private double temperature;
    private double wind;
    private double wind_velocity;

    public static WeatherDto of(double humidity,
                                double precipitation,
                                double temperature,
                                double wind,
                                double wind_velocity){
        return WeatherDto.builder()
                .humidity(humidity)
                .precipitation(precipitation)
                .temperature(temperature)
                .wind(wind)
                .wind_velocity(wind_velocity)
                .build();
    }
}
