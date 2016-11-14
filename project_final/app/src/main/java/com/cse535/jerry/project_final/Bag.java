package com.cse535.jerry.project_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * Created by Yanzhu on 2016/11/13.
 */

public class Bag {
    public int bagid;
    public String title;
    public String description;
    public String account;
    public Bitmap bitmap;
    public byte[] byteArray;
    public float price;
    public List< Review> lReview;

    public Bag(int bagid, String title, String description, String account, byte[] byteArray, float price, List< Review> lReview) {
        this.bagid = bagid;
        this.title = title;
        this.description = description;
        this.account = account;
        this.byteArray = byteArray;
        this.price = price;
        this.lReview = lReview;
    }

    public int getBagid() {
        return bagid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAccount() {
        return account;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getPrice() {
        return price;
    }

    public List< Review> getlReview() {
        return lReview;
    }

    public void setBagid(int bagid) {
        this.bagid = bagid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setlReview(List< Review> lReview) {
        this.lReview = lReview;
    }

    public void bitmap2bytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        this.byteArray = stream.toByteArray();
    }

    public void bytes2bitmap(byte[] byteArray){
        this.bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
