package com.xtm.email.entity;

/**
 * @author:藏剑
 * @date:2019/12/3 16:42
 */
public class OneData {
    private String todayDate;

    private String imgUrl;

    private String type;

    private String text;

    @Override
    public String toString() {
        return "OneData{" +
                "todayDate='" + todayDate + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
