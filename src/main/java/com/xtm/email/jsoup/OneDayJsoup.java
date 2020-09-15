package com.xtm.email.jsoup;

import com.xtm.email.HttpClientDownPage;
import com.xtm.email.entity.OneData;
import org.apache.tomcat.jni.Local;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:藏剑
 * @date:2019/12/3 18:56
 */
public class OneDayJsoup {
    public static Map<String,Object> getOneDay(String url){
        Map<String, Object> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Document document= HttpClientDownPage.sendGetPage("http://wufazhuce.com/");
        OneData todayOneData = new OneData();
        Elements manyDaysData = document.select(".carousel-inner .item");
        for(Element oneDaysData:manyDaysData){
            todayOneData.setImgUrl(oneDaysData.select(".fp-one-imagen").attr("src"));
            todayOneData.setType(oneDaysData.select(".fp-one-imagen-footer").text());
            todayOneData.setText(oneDaysData.select(".fp-one-cita").text());
            todayOneData.setTodayDate(LocalDate.now().toString());
            break;
        }
        map.put("todayOneData",todayOneData);
        return map;
    }
}
