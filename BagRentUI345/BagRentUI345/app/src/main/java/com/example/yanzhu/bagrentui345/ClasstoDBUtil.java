package com.example.yanzhu.bagrentui345;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yanzhu on 2016/11/15.
 */

//
public class ClasstoDBUtil {
    Bag mBag;

    public String getTableColumninString(){
        String tableColumninString  = "";

        return tableColumninString;
    }

    public List<String> getTableColumninNameList(){
        List<String> getTableColumninStringList = new ArrayList<String>();
        getTableColumninStringList.add(Integer.toString(mBag.bagid));
        getTableColumninStringList.add(mBag.title);
        getTableColumninStringList.add(Float.toString(mBag.price));


        return getTableColumninStringList;

    }



}
