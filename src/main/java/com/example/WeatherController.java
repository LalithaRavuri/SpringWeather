package com.example;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/api/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;
    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public List<WeatherRecord> getAllWeather(){
        return weatherService.getAllWeather();
    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherRecord> getWeatherByCity(@PathVariable @NotBlank String city) {
        WeatherRecord weather = weatherService.getWeatherByCity(city);
        return weather != null ? ResponseEntity.ok(weather) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public WeatherRecord addWeather(@RequestBody WeatherRecord weatherRecord){
         return weatherService.addWeather(weatherRecord);
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WeatherRecord> updateWeather(@PathVariable Long id, @RequestBody WeatherRecord weatherRecord){
        if(!weatherService.getAllWeather().stream().anyMatch(weather -> weather.getId().equals(id))){
            return ResponseEntity.notFound().build();
        }
        weatherRecord.setId(id);
        return ResponseEntity.ok(weatherService.updateWeather(weatherRecord));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable Long id){
        if(!weatherService.getAllWeather().stream().anyMatch(weather -> weather.getId().equals(id))){
            return ResponseEntity.notFound().build();
        }
        weatherService.deleteWeather(id);
        return ResponseEntity.noContent().build();
    }
    
}
