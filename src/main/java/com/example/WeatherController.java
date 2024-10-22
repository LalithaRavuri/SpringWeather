package com.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {
 private final WeatherService weatherService;              // Singleton
 private final WeatherRequestLogger requestLogger;         // Request Scope
 private final UserWeatherPreferences userPreferences;     // Session Scope
 private final ApplicationContext applicationContext;      // For Prototype

 public WeatherController(
         WeatherService weatherService,
         WeatherRequestLogger requestLogger,
         UserWeatherPreferences userPreferences,
         ApplicationContext applicationContext) {
     this.weatherService = weatherService;
     this.requestLogger = requestLogger;
     this.userPreferences = userPreferences;
     this.applicationContext = applicationContext;
 }

 @GetMapping("/{city}")
 public Map<String, Object> getWeather(@PathVariable String city) {
     // Request scope - log the request
     requestLogger.logRequest(city);

     // Session scope - add to recent searches
     userPreferences.addSearch(city);

     // Prototype scope - create new query
     WeatherQuery query = applicationContext.getBean(WeatherQuery.class);

     // Singleton - get weather data
     WeatherRecord weather = weatherService.getWeather(city);

     Map<String, Object> response = new HashMap<>();
     response.put("weather", weather);
     response.put("requestInfo", requestLogger.getRequestInfo());
     response.put("recentSearches", userPreferences.getRecentSearches());
     response.put("queryInfo", Map.of(
         "queryId", query.getQueryId(),
         "queryTime", query.getQueryTime()
     ));
     response.put("totalRequests", weatherService.getTotalRequests());

     return response;
 }

 @PostMapping("/{city}")
 public Map<String, Object> updateWeather(
         @PathVariable String city,
         @RequestParam double temperature,
         @RequestParam String condition) {
     weatherService.updateWeather(city, temperature, condition);
     return getWeather(city);
 }

 @PostMapping("/preferences/unit")
 public Map<String, String> setUnit(@RequestParam String unit) {
     userPreferences.setTemperatureUnit(unit);
     return Map.of(
         "sessionId", userPreferences.getSessionId(),
         "unit", userPreferences.getTemperatureUnit()
     );
 }
}




























//import org.springframework.web.bind.annotation.RestController;
//import java.util.Map;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import javax.validation.constraints.NotBlank;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import java.util.List;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.http.HttpStatus;
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/weather")
//@Validated
//public class WeatherController {
//
//    private final WeatherService weatherService;
//    @Autowired
//    public WeatherController(WeatherService weatherService){
//        this.weatherService = weatherService;
//    }
//
//    @GetMapping("/all")
//    public List<WeatherRecord> getAllWeather(){
//        return weatherService.getAllWeather();
//    }
//
//    @GetMapping("/{city}")
//    public ResponseEntity<WeatherRecord> getWeatherByCity(@PathVariable @NotBlank String city) {
//        WeatherRecord weather = weatherService.getWeatherByCity(city);
//        return weather != null ? ResponseEntity.ok(weather) : ResponseEntity.notFound().build();
//    }
//
//    @PostMapping("/create")
//    public WeatherRecord addWeather(@Valid @RequestBody WeatherRequestDTO weatherRequestDTO){
//         return weatherService.addWeather(weatherRequestDTO);
//        
//    }
//    
//     
//    @PutMapping("/{id}")
//    public ResponseEntity<WeatherRecord> updateWeather(@PathVariable Long id, @RequestBody WeatherRecord weatherRecord){
//        if(!weatherService.getAllWeather().stream().anyMatch(weather -> weather.getId().equals(id))){
//            return ResponseEntity.notFound().build();
//        }
//        weatherRecord.setId(id);
//        return ResponseEntity.ok(weatherService.updateWeather(weatherRecord));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteWeather(@PathVariable Long id){
//        if(!weatherService.getAllWeather().stream().anyMatch(weather -> weather.getId().equals(id))){
//            return ResponseEntity.notFound().build();
//        }
//        weatherService.deleteWeather(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}