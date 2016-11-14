package com.cse535.jerry.project_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    private String account;
    private String password;
    private String local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        final Button loginBtn = (Button)findViewById(R.id.login_btn_1);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurLogin();
//                JSONObject login_json = goWCF.login(account, password, local);
//                try {
//                    boolean msg = (boolean) login_json.get("_loginSuc");
//                    if(msg == true){
                        Intent intent = new Intent(login_page.this, MyAcountActivity.class);
                        startActivity(intent);
//                    }else{
//                        Toast.makeText(login_page.this, "login info are wrong", Toast.LENGTH_LONG);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }
        });
    }

    private void getCurLogin(){
        TextView accountView = (EditText)findViewById(R.id.name_1);
        TextView pwdView = (EditText)findViewById(R.id.pwd_1);
        account = accountView.getText().toString();
        password = pwdView.getText().toString();
        local = "Tempe";
    }

    public void registerEvent(View view){
//        getCurLogin();
//        if(account.length() == 0 || password.length() == 0){
//            Toast.makeText(login_page.this,"register Error", Toast.LENGTH_LONG ).show();
//        }else{
//            boolean rej = goWCF.rejester(account, password, local);
//            if( rej == false ) {
//                Toast.makeText(login_page.this, "account already existed", Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(login_page.this,"register Success", Toast.LENGTH_LONG ).show();
//            }
//        }
//        test GPS
        GPSTracker  gps = new GPSTracker(this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            String addr = gps.getAddress();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            if (addr != null){
                Log.d("local", addr);
            }else{
                Log.d("local", "error Local   error Local error Local error Local");
            }

            // \n is for new line
//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude +"\nAddr:"+ addr, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }



}


