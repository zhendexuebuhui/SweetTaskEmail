package com.xtm.email.entity;

/**
 * @author:藏剑
 * @date:2019/12/3 16:43
 */
public class WeatherData {
    private String day;

    private String weatherImgUrl;

    private String weatherText;

    private String temperature;

    private String wind;

    @Override
    public String toString() {
        return "WeatherData{" +
                "day='" + day + '\'' +
                ", weatherImgUrl='" + weatherImgUrl + '\'' +
                ", weatherText='" + weatherText + '\'' +
                ", temperature='" + temperature + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeatherImgUrl() {
        return weatherImgUrl;
    }

    public void setWeatherImgUrl(String weatherImgUrl) {
        this.weatherImgUrl = weatherImgUrl;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
