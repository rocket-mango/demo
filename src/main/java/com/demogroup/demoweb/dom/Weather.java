package com.demogroup.demoweb.dom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Weather {
    String humidity;
    String precipitation;
    String temperature;
    String wind;
    String wind_velocity;
}
