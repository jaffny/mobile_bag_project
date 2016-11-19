package com.cse535.jerry.project_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Yanzhu on 2016/11/14.
 */

public class ChooseDateActivity extends Activity{
    private DatePicker dp;
    private String dp_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChangeUtil.changeTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_datepick);

        dp = (DatePicker)findViewById(R.id.dp_getdp_10);


        dp.init(2016, 11, 24, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                dp_content = format.format(calendar.getTime());
                Toast.makeText(ChooseDateActivity.this, format.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void confirm(View view){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("datepick",dp_content);
        setResult(1234,returnIntent);
        finish();
    }


}
