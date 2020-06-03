package com.mr13.weather.components.weatherdata.service;

import com.mr13.weather.components.city.domain.City;
import com.mr13.weather.components.city.service.CityService;
import com.mr13.weather.components.weatherdata.domain.StandardWeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

  private final CityService cityService;
  private final WeatherDataService weatherDataService;

  public StandardWeatherData getStandardWeatherData(String cityName) {

    City savedCity = cityService.save(cityName);
    weatherDataService.save(savedCity);
    String savedCityCityName = savedCity.getCityName();

    return weatherDataService.getStandardWeatherData(savedCityCityName);
  }
}
