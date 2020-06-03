package com.mr13.weather.components.weatherdata.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardWeatherData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String cityName;

  private Double temperature;

  private Long humidity;
}
