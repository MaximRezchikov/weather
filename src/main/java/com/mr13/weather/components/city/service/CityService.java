package com.mr13.weather.components.city.service;

import com.mr13.weather.components.city.domain.City;

import java.util.Optional;

public interface CityService {

  City save(String cityName);

  City getCity(String cityName);
}
