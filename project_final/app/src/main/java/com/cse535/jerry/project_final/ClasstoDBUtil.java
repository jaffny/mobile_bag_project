package com.cse535.jerry.project_final;

import java.util.ArrayList;
import java.util.List;



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
