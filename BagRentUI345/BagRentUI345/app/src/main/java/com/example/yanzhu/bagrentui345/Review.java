package com.example.yanzhu.bagrentui345;

/**
 * Created by Yanzhu on 2016/11/13.
 */

//to be deleted

public class Review {
    public String buyerName;
    public String description;

    public Review(String buyerName, String description) {
        this.buyerName = buyerName;
        this.description = description;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getDescription() {
        return description;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
