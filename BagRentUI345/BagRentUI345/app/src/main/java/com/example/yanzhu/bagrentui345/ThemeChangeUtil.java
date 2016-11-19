package com.example.yanzhu.bagrentui345;

import android.app.Activity;

/**
 * Created by Yanzhu on 2016/11/7.
 */

public class ThemeChangeUtil {
    public  static int themeID = 0; //0:default,1:day,2:night
    public static void changeTheme(Activity activity){
        if(themeID == 0){
            activity.setTheme(R.style.AppTheme);
        }else if(themeID == 1){
            activity.setTheme(R.style.DayTheme);
        }else if(themeID == 2){
            activity.setTheme(R.style.NightTheme);
        }
    }
}