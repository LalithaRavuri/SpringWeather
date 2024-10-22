package com.example;

import jakarta.persistence.*;

@Entity
public class WeatherRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the ID
    private Long id; // Add an ID field to uniquely identify each record

    private String city;
    private double temperature;
    private String condition;

    public WeatherRecord() {
        // Default constructor required by JPA
    }

    public WeatherRecord(String city, double temperature, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
    }

    public Long getId() { return id; } // Getter for the ID field
    public void setId(Long id) { this.id = id; } // Setter for the ID field
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
}























//import jakarta.persistence.*;
//
//@Entity
//@Table(name="weather_records")
//public class WeatherRecord {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false,unique = true)
//    private String city;
//    @Column(nullable = false)
//    private int temperature;
//    @Column(nullable = false)
//    private String weatherCondition;
//
//    public WeatherRecord(){
//
//    }
//
//    public void intialize(String city, int temperature){
//        this.city = city;
//        this.temperature = temperature;
//    }
//
//    public Long getId(){
//        return id;
//    }   
//    public void setId(Long id){
//        this.id = id;
//    }
//
//    public String getCity(){
//        return city;
//    }
//
//    public String getWeatherCondition(){
//        return weatherCondition;
//    }
//    public void setCity(String city){
//        this.city = city;
//    }
//    public void setWeatherCondition(String weatherCondition){
//        this.weatherCondition = weatherCondition;
//    }
//    public void setTemperature(int temperature){
//        this.temperature = temperature;
//    }
//    public int getTemperature(){
//        return temperature;
//    }
//}
