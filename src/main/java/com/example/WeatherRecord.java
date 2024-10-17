package com.example;

import jakarta.persistence.*;

@Entity
@Table(name="weather_records")
public class WeatherRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String city;
    @Column(nullable = false)
    private int temperature;
    @Column(nullable = false)
    private String weatherCondition;

    public WeatherRecord(){

    }

    public void intialize(String city, int temperature){
        this.city = city;
        this.temperature = temperature;
    }

    public Long getId(){
        return id;
    }   
    public void setId(Long id){
        this.id = id;
    }

    public String getCity(){
        return city;
    }

    public String getWeatherCondition(){
        return weatherCondition;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setWeatherCondition(String weatherCondition){
        this.weatherCondition = weatherCondition;
    }
    public void setTemperature(int temperature){
        this.temperature = temperature;
    }
    public int getTemperature(){
        return temperature;
    }
}
