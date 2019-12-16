package com.dingtao.common.bean.video;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class VideovolBean {

    public int id;
    public int categoryId;
    public int price;
    public int duration;
    public int whetherCollection;
    public int whetherBuy;
    public int buyNum;
    public String title;
    public String shearUrl;
    public String abstracts;
    public String originalUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWhetherCollection() {
        return whetherCollection;
    }

    public void setWhetherCollection(int whetherCollection) {
        this.whetherCollection = whetherCollection;
    }

    public int getWhetherBuy() {
        return whetherBuy;
    }

    public void setWhetherBuy(int whetherBuy) {
        this.whetherBuy = whetherBuy;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShearUrl() {
        return shearUrl;
    }

    public void setShearUrl(String shearUrl) {
        this.shearUrl = shearUrl;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
