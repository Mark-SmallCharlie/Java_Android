package com.example.loginview.model;

public class Equipment {
    private long id;
    private String name;
    private String code;
    private String category;
    private String damageLevel;
    private int usageHours;
    private int imageResId;
    private double marketPrice;

    public Equipment() {
    }

    public Equipment(String name, String code, String category, String damageLevel,
                     int usageHours, int imageResId, double marketPrice) {
        this.name = name;
        this.code = code;
        this.category = category;
        this.damageLevel = damageLevel;
        this.usageHours = usageHours;
        this.imageResId = imageResId;
        this.marketPrice = marketPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(String damageLevel) {
        this.damageLevel = damageLevel;
    }

    public int getUsageHours() {
        return usageHours;
    }

    public void setUsageHours(int usageHours) {
        this.usageHours = usageHours;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }
}
