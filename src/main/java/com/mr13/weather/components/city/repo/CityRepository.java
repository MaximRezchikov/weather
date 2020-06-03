package com.mr13.weather.components.city.repo;

import com.mr13.weather.components.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

  Optional<City> findByCityName(String cityName);
}
