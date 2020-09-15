package com.xtm.email.jsoup;

import com.xtm.email.HttpClientDownPage;
import com.xtm.email.entity.MailDO;
import com.xtm.email.entity.OneData;
import com.xtm.email.entity.WeatherData;
import com.xtm.email.util.CalDay;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:藏剑
 * @date:2019/12/3 10:30
 */
@Component
public class MojiJsoup {

   public static Map<String,Object> getMojiWeather(String url){
        //根据网页标签解析源码
       Map<String,Object> map=new HashMap<>();
       List<WeatherData> weatherDataList=new ArrayList<WeatherData>();
       Document document= HttpClientDownPage.sendGetPage(url);
       //天气提示
       map.put("weatherTip",document.select(".wea_tips em").text());
       //获取三天天气
       Elements threeData = document.select(".forecast .days li");
       int i=0;
       WeatherData aDayWeatherData = new WeatherData();
       for(Element dayData:threeData){
           if(i==0||i==5||i==10){//今天 明天 后天
               aDayWeatherData.setDay(dayData.select("a").text());
           }
           if(i==1||i==6||i==11){ //晴 图片
               aDayWeatherData.setWeatherImgUrl(dayData.select("span img").attr("src"));
               aDayWeatherData.setWeatherText(dayData.text());
           }
           if(i==2||i==7||i==12){  //温度
               aDayWeatherData.setTemperature(dayData.text());
           }
           if(i==3||i==8||i==13){//风力
               aDayWeatherData.setWind(dayData.text());
           }
           if(i==4||i==9||i==14) {
               weatherDataList.add(aDayWeatherData);
               aDayWeatherData=new WeatherData();
           }
           i++;
       }
       map.put("threeDaysData",weatherDataList);

       return map;
    }

}

