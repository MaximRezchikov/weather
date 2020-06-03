package com.mr13.weather.components.weatherdata.service;

import com.mr13.weather.components.city.domain.City;
import com.mr13.weather.components.weatherdata.domain.StandardWeatherData;

public interface WeatherDataService {

  StandardWeatherData save(City city);

  StandardWeatherData getStandardWeatherData(String cityName);
}
