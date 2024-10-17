package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    @Autowired
    public WeatherService(WeatherRepository weatherRepositry){
        this.weatherRepository = weatherRepositry;
    }

    public List<WeatherRecord> getAllWeather(){
        return weatherRepository.findAll();
    }

    public WeatherRecord getWeatherByCity(String city){
        return weatherRepository.findByCity(city).orElseThrow(() -> new RuntimeException("City not found"));
    }

    public WeatherRecord addWeather(WeatherRecord weatherRecord){
        return weatherRepository.save(weatherRecord);
    }
    public void deleteWeather(Long id){
        weatherRepository.deleteById(id);
    }
    public WeatherRecord updateWeather(WeatherRecord weatherRecord){
        return weatherRepository.save(weatherRecord);
    }

}
