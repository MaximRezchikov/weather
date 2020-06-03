package com.mr13.weather.components.weatherdata.service;

import com.mr13.weather.components.city.domain.City;
import com.mr13.weather.components.city.service.CityService;
import com.mr13.weather.components.weatherdata.api.ApiWeatherData;
import com.mr13.weather.components.weatherdata.domain.StandardWeatherData;
import com.mr13.weather.components.weatherdata.dto.StandardWeatherDataDto;
import com.mr13.weather.components.weatherdata.repo.WeatherDataRepository;
import com.mr13.weather.core.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WeatherDataServiceImpl implements WeatherDataService {

  private final WeatherDataRepository weatherDataRepository;
  private final ApiWeatherData apiWeatherData;


  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public StandardWeatherData save(City city) {

    String cityName = city.getCityName();

    StandardWeatherDataDto standardWeatherDataFromApi = apiWeatherData.getStandardWeatherDataFromApi(city);
    Long humidity = standardWeatherDataFromApi.getHumidity();
    Double temperature = standardWeatherDataFromApi.getTemperature();

    if (humidity != null && temperature != null) {
      StandardWeatherData standardWeatherData = StandardWeatherData.builder()
          .temperature(temperature)
          .humidity(humidity)
          .cityName(cityName)
          .build();

      return weatherDataRepository.save(standardWeatherData);
    }
    else {
      throw new NotFoundException();
    }
  }

  @Override
  @Transactional
  public StandardWeatherData getStandardWeatherData(String cityName) {

    return weatherDataRepository.findByCityName(cityName)
        .orElseThrow(NotFoundException::new);
  }
}
