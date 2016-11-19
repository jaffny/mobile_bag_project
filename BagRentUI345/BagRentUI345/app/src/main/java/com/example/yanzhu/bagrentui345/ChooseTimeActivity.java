package com.example.yanzhu.bagrentui345;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by Yanzhu on 2016/11/14.
 */

public class ChooseTimeActivity extends Activity {

    private TimePicker tp;
    String tp_content;

    protected void onCreate(Bundle savedInstanceState) {
        ThemeChangeUtil.changeTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timepick);

        tp = (TimePicker)findViewById(R.id.tp_gettp_10);

        tp.setIs24HourView(true);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay,int minute) {
                tp_content = Integer.toString(hourOfDay)+ ":" + Integer.toString(minute);
                Toast.makeText(ChooseTimeActivity.this, tp_content, Toast.LENGTH_SHORT).show();
                }
        });
    }

    public void confirm(View view){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("timepick",tp_content);
        setResult(4321,returnIntent);
        finish();
    }
}
