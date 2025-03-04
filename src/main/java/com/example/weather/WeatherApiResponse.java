package com.example.weather;

import java.time.LocalDate;
import java.util.List;

public class WeatherApiResponse {
    private List<WeatherApiDay> daily;

    public List<WeatherApiDay> getDaily() {
        return daily;
    }

    public void setDaily(List<WeatherApiDay> daily) {
        this.daily = daily;
    }

    public static class WeatherApiDay {
        private LocalDate date;
        private double temperatureMax;
        private double temperatureMin;
        private double humidityMax;
        private double windSpeedMax;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getTemperatureMax() {
            return temperatureMax;
        }

        public void setTemperatureMax(double temperatureMax) {
            this.temperatureMax = temperatureMax;
        }

        public double getTemperatureMin() {
            return temperatureMin;
        }

        public void setTemperatureMin(double temperatureMin) {
            this.temperatureMin = temperatureMin;
        }

        public double getHumidityMax() {
            return humidityMax;
        }

        public void setHumidityMax(double humidityMax) {
            this.humidityMax = humidityMax;
        }

        public double getWindSpeedMax() {
            return windSpeedMax;
        }

        public void setWindSpeedMax(double windSpeedMax) {
            this.windSpeedMax = windSpeedMax;
        }
    }
}
