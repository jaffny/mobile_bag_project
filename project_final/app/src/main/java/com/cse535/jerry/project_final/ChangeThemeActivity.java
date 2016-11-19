package com.cse535.jerry.project_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Yanzhu on 2016/11/7.
 */

public class ChangeThemeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChangeUtil.changeTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_changetheme);
    }

    public void changeToAppTheme(View view){
        ThemeChangeUtil.themeID = 0;
        ChangeThemeActivity.this.recreate();
    }

    public void changeToDayTheme(View view){
        ThemeChangeUtil.themeID = 1;
        ChangeThemeActivity.this.recreate();
    }

    public void changeToNightTheme(View view){
        ThemeChangeUtil.themeID = 2;
        ChangeThemeActivity.this.recreate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mIntent = new Intent(this, MainListActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
        finish();
    }
}
