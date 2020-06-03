package com.mr13.weather.components.weatherdata.controller;

import com.mr13.weather.components.weatherdata.domain.StandardWeatherData;
import com.mr13.weather.components.weatherdata.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/standard")
@RequiredArgsConstructor
public class WeatherDataController {

  private final WeatherService weatherService;

  @GetMapping("/{cityName}")
  public StandardWeatherData getStandardWeatherData(@PathVariable("cityName") String cityName) {
    return weatherService.getStandardWeatherData(cityName);
  }
}
