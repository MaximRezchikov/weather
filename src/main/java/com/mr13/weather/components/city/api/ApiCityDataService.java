package com.mr13.weather.components.city.api;

import com.byteowls.jopencage.model.JOpenCageResult;

public interface ApiCityDataService {

  JOpenCageResult getResponse(String cityName);
}
