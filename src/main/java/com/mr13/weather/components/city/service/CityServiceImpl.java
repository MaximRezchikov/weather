package com.mr13.weather.components.city.service;

import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResult;
import com.mr13.weather.components.city.api.ApiCityDataService;
import com.mr13.weather.components.city.domain.City;
import com.mr13.weather.components.city.repo.CityRepository;
import com.mr13.weather.core.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

  private final ApiCityDataService apiCityDataService;
  private final CityRepository cityRepository;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public City save(String cityName) {

    JOpenCageResult response = apiCityDataService.getResponse(cityName);
    JOpenCageLatLng geometry = response.getGeometry();

    Double lat = geometry.getLat();
    Double lng = geometry.getLng();

    double latitude = BigDecimal.valueOf(lat)
        .setScale(4, RoundingMode.UP)
        .doubleValue();
    double longitude = BigDecimal.valueOf(lng)
        .setScale(4, RoundingMode.UP)
        .doubleValue();

    String cityNameUpperCase = cityName.toUpperCase();

      City city = City.builder()
          .cityName(cityNameUpperCase)
          .latitude(latitude)
          .longitude(longitude)
          .build();
      return cityRepository.save(city);

  }

  @Override
  public City getCity(String cityName) {

    return cityRepository.findByCityName(cityName)
        .orElseThrow(NotFoundException::new);
  }
}
