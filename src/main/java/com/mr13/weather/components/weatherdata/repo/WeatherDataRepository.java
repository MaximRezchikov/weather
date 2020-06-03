package com.mr13.weather.components.weatherdata.repo;

import com.mr13.weather.components.weatherdata.domain.StandardWeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<StandardWeatherData, Long> {

  Optional<StandardWeatherData> findByCityName(String cityName);
}
