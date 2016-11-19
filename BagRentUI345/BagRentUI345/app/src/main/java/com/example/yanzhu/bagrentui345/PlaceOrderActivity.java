package com.example.yanzhu.bagrentui345;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Yanzhu on 2016/11/13.
 */

public class PlaceOrderActivity extends Activity {

    private String bagID;
    private ImageView iv_bag;
    private TextView tv_title;
    private RadioGroup rg_location;
    private RadioButton rb_tempe;
    private RadioButton rb_phoenix;

    private String location;

    //private DatePicker dp;
    //private TimePicker tp;
    String dp_content;
    String tp_content;
    boolean if_choosedate;
    boolean if_choosetime;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChangeUtil.changeTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_placeorder);

        Intent intent = getIntent();
        if(intent != null){
            bagID = intent.getStringExtra("ID");
        }

        iv_bag = (ImageView)findViewById(R.id.iv_bag_4);
        tv_title = (TextView)findViewById(R.id.tv_title_4);
        rg_location = (RadioGroup)findViewById(R.id.rg_location_4);
        rb_tempe = (RadioButton)findViewById(R.id.tempe);
        rb_phoenix = (RadioButton)findViewById(R.id.phoenix);
        //dp = (DatePicker)findViewById(R.id.dp_getdp_4);
        //tp = (TimePicker)findViewById(R.id.tp_gettp_4);

        id = Integer.valueOf(bagID) - 1;

        iv_bag.setImageResource(TestBagInfo.imageaddress[id]);
        tv_title.setText(TestBagInfo.bagtitle[id]);

        if_choosedate = false;
        if_choosetime = false;

//        dp.init(2016, 11, 24, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(year, monthOfYear, dayOfMonth);
//                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy  HH:mm");
//                dp_content = format.format(calendar.getTime()).toString();
//                Toast.makeText(PlaceOrderActivity.this, format.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        tp.setIs24HourView(true);
//        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker view, int hourOfDay,int minute) {
//                tp_content = Integer.toString(hourOfDay)+ ":" + Integer.toString(minute);
//                }
//        });
    }

    public void chooseDate(View view){//1000 1234
        Intent intent_choosedate = new Intent(PlaceOrderActivity.this, ChooseDateActivity.class);
        startActivityForResult(intent_choosedate,1000);
    }

    public void chooseTime(View view){//9999 4321
        Intent intent_choosetime = new Intent(PlaceOrderActivity.this, ChooseTimeActivity.class);
        startActivityForResult(intent_choosetime,9999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1000 && resultCode==1234){
            if_choosedate = true;
            dp_content = data.getStringExtra("datepick");
        }else if(requestCode==9999 && resultCode==4321){
            if_choosetime = true;
            tp_content = data.getStringExtra("timepick");
        }else{

        }

    }

    public void placeOrder(View view){
        if(rb_tempe.isChecked()){
            location = "Tempe";
        }else if(rb_phoenix.isChecked()){
            location = "Phoenix";
        }else{
            location = "NULL";
        }
        if(location=="NULL"){
            Toast.makeText(PlaceOrderActivity.this, "Please choode location", Toast.LENGTH_SHORT).show();
        }else if(if_choosedate == false){
            Toast.makeText(PlaceOrderActivity.this, "Please choode date", Toast.LENGTH_SHORT).show();
        }else if(if_choosetime == false){
            Toast.makeText(PlaceOrderActivity.this, "Please choode time", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(PlaceOrderActivity.this, "You choose to pick up on "+ dp_content+" at "+tp_content +" "
                    +location, Toast.LENGTH_SHORT).show();
            Intent intent_toordersuccess = new Intent(PlaceOrderActivity.this,OrderSuccessActivity.class);
            intent_toordersuccess.putExtra("id",bagID);
            intent_toordersuccess.putExtra("location",location);
            intent_toordersuccess.putExtra("datepick",dp_content);
            intent_toordersuccess.putExtra("timepick",tp_content);
            intent_toordersuccess.putExtra("image",TestBagInfo.imageaddress[id]);
            startActivity(intent_toordersuccess);
        }


    }


}
