package com.example.weather;

import com.example.weather.WeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    @Autowired
    private RestTemplate restTemplate;

    public List<WeatherForecast> getWeatherForecast(double latitude, double longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("daily", "temperature_2m_max,temperature_2m_min,precipitation_sum,wind_speed_10m_max")
                .queryParam("timezone", "auto")
                .toUriString();

        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        return response.getDaily().stream().map(day -> {
            WeatherForecast forecast = new WeatherForecast();
            forecast.setDate(day.getDate());
            forecast.setTemperature((day.getTemperatureMax() + day.getTemperatureMin()) / 2); // Average temperature
            forecast.setHumidity(day.getHumidityMax());
            forecast.setWindSpeed(day.getWindSpeedMax());
            return forecast;
        }).collect(Collectors.toList());
    }

    public String compareWeatherForecasts(List<WeatherForecast> city1Forecast, List<WeatherForecast> city2Forecast) {
        StringBuilder comparisonResult = new StringBuilder();
        for (int i = 0; i < city1Forecast.size(); i++) {
            WeatherForecast city1Day = city1Forecast.get(i);
            WeatherForecast city2Day = city2Forecast.get(i);

            comparisonResult.append("Day ").append(i + 1).append(":\n");
            comparisonResult.append("City 1: Temp = ").append(city1Day.getTemperature())
                    .append(", Wind Speed = ").append(city1Day.getWindSpeed()).append("\n");
            comparisonResult.append("City 2: Temp = ").append(city2Day.getTemperature())
                    .append(", Wind Speed = ").append(city2Day.getWindSpeed()).append("\n");
            comparisonResult.append("--------\n");
        }
        return comparisonResult.toString();
    }
}
