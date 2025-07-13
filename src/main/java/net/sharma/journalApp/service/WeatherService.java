package net.sharma.journalApp.service;

import net.sharma.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilityService utilityService;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            String finalUrl = utilityService.appCache.get("WEATHER_API").replace("APIKEY", apiKey).replace("CITY", city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
            weatherResponse = response.getBody();
            if(weatherResponse != null){
                redisService.set("weather_of_"+city, weatherResponse, 300l);
            }
            return weatherResponse;
        }
    }
}
