package com.mr13.weather.components.city.api;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageResult;
import com.mr13.weather.core.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiCityData implements ApiCityDataService {

  private final JOpenCageGeocoder jOpenCageGeocoder;

  @Override
  public JOpenCageResult getResponse(String cityName) {

    JOpenCageForwardRequest request = new JOpenCageForwardRequest(cityName);
    request.setMinConfidence(1);
    request.setNoAnnotations(false);
    request.setNoDedupe(true);

    JOpenCageResponse forward = jOpenCageGeocoder.forward(request);
    List<JOpenCageResult> results = forward.getResults();

    return results.stream()
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }
}
