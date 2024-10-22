package com.example;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service  // Singleton by default
public class WeatherService {
 private Map<String, WeatherRecord> weatherData = new HashMap<>();
 private int totalRequests = 0;  // Shared counter for all users

 public void updateWeather(String city, double temperature, String condition) {
     weatherData.put(city, new WeatherRecord(city, temperature, condition));
     totalRequests++;
 }

 public WeatherRecord getWeather(String city) {
     totalRequests++;
     return weatherData.get(city);
 }

 public int getTotalRequests() {
     return totalRequests;
 }
}























//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.transaction.annotation.Transactional;
//import com.example.WeatherRequestDTO;
//@Service
//@Validated
//public class WeatherService {
//    private final WeatherRepository weatherRepository;
//
//    private final WeatherValidator weatherValidator;
//    @Autowired
//    public WeatherService(WeatherRepository weatherRepository, WeatherValidator weatherValidator){
//        this.weatherRepository = weatherRepository;
//        this.weatherValidator = weatherValidator;
//    }
//
//    public List<WeatherRecord> getAllWeather(){
//        return weatherRepository.findAll();
//    }
//
//    public WeatherRecord getWeatherByCity(String city){
//        return weatherRepository.findByCity(city).orElseThrow(() -> new RuntimeException("City not found"));
//    }
//
//    private WeatherRecord convertToEntity(WeatherRequestDTO weatherRecordDTO){
//        WeatherRecord weatherRecord = new WeatherRecord();
//        weatherRecord.setCity(weatherRecordDTO.getCity());
//        weatherRecord.setTemperature(weatherRecordDTO.getTemperature());
//        weatherRecord.setWeatherCondition(weatherRecordDTO.getWeatherCondition());
//        return weatherRecord;
//    }
//    @Transactional
//    public WeatherRecord addWeather(WeatherRequestDTO weatherRecordDTO){
//        weatherValidator.validateWeatherCondition(weatherRecordDTO);
//
//        WeatherRecord weatherRecord = convertToEntity(weatherRecordDTO);
//        return weatherRepository.save(weatherRecord);
//    }
//    public void deleteWeather(Long id){
//        weatherRepository.deleteById(id);
//    }
//    public WeatherRecord updateWeather(WeatherRecord weatherRecord){
//        return weatherRepository.save(weatherRecord);
//    }
//
//}