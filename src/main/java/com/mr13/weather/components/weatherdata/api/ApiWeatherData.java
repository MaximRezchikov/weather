package com.mr13.weather.components.weatherdata.api;

import com.mr13.weather.components.city.domain.City;
import com.mr13.weather.components.city.service.CityService;
import com.mr13.weather.components.weatherdata.domain.StandardWeatherData;
import com.mr13.weather.components.weatherdata.dto.StandardWeatherDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.mr13.weather.core.Constant.FORECAST_API_TOKEN;
import static com.mr13.weather.core.Constant.FORECAST_URL_API;

@Component
@RequiredArgsConstructor
public class ApiWeatherData {

  private final RestTemplate restTemplate;

  public StandardWeatherDataDto getStandardWeatherDataFromApi(City city) {

    String formatTime = getFormattedTime();
    String cityCoordinates = getCityCoordinates(city);

    return restTemplate.getForObject(
        FORECAST_URL_API
            + cityCoordinates
            + "/"
            + formatTime
            + FORECAST_API_TOKEN,
        StandardWeatherDataDto.class);
  }

  private String getCityCoordinates(City city) {

    Double latitude = city.getLatitude();
    Double longitude = city.getLongitude();

    double formattedLatitude = BigDecimal.valueOf(latitude)
        .setScale(4, RoundingMode.UP)
        .doubleValue();

    double formattedLongitude = BigDecimal.valueOf(longitude)
        .setScale(4, RoundingMode.UP)
        .doubleValue();

    return formattedLatitude + ";" + formattedLongitude;
  }

  private String getFormattedTime() {

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime nowMinusHour = now.minusHours(1);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    return nowMinusHour.format(dateTimeFormatter);
  }
}
