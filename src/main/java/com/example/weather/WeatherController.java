package com.example.weather;

import com.example.weather.WeatherForecast;
import com.example.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/forecast")
    public List<WeatherForecast> getWeatherForecast(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getWeatherForecast(13.00, 90.70);
    }

    @GetMapping("/compare")
    public String compareWeatherForecasts(
            @RequestParam double latitude1, @RequestParam double longitude1,
            @RequestParam double latitude2, @RequestParam double longitude2) {

        List<WeatherForecast> city1Forecast = weatherService.getWeatherForecast(latitude1, longitude1);
        List<WeatherForecast> city2Forecast = weatherService.getWeatherForecast(latitude2, longitude2);

        return weatherService.compareWeatherForecasts(city1Forecast, city2Forecast);
    }
}
