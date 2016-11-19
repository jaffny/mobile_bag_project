package com.cse535.jerry.project_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    private String account;
    private String password;
    private String local;
    private String long_local;
    private int choose = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        final Button loginBtn = (Button)findViewById(R.id.login_btn_1);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurLogin();
                if(account.length() == 0 || password.length() == 0){
                    Toast.makeText(login_page.this,"login Error", Toast.LENGTH_LONG ).show();
                }else{
                    choose = 0;
                    AsyncLogin task = new AsyncLogin();
                    task.execute();
                }

            }
        });
    }

    private void getCurLogin(){
        TextView accountView = (EditText)findViewById(R.id.name_1);
        TextView pwdView = (EditText)findViewById(R.id.pwd_1);
        account = accountView.getText().toString();
        password = pwdView.getText().toString();
        String[] ads = curlocal();
        local = ads[0];
        long_local = ads[1];
    }

    public void registerEvent(View view){
        getCurLogin();
        if(account.length() == 0 || password.length() == 0){
            Toast.makeText(login_page.this,"register Error", Toast.LENGTH_LONG ).show();
        }else{
            choose = 1;
            AsyncLogin task = new AsyncLogin();
            task.execute();
        }
    }

    protected void onPause(){
        super.onPause();
        SharedPreferences user = getSharedPreferences("user", 0);
        SharedPreferences.Editor PE = user.edit();
        PE.putString("name",account);
        PE.putString("local", local);
        PE.putBoolean("show_detail", true);
        PE.putString("local_detail", long_local);
        PE.commit();
    }

    private String[] curlocal(){
        GPSTracker  gps = new GPSTracker(this);
        String[] addr = null;
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            addr = gps.getAddress();


//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude +"\nlocality:" + addr, Toast.LENGTH_LONG).show();
        }else{
            gps.showSettingsAlert();
        }
        return addr;
    }

    private class AsyncLogin extends AsyncTask<String, Void, Integer>{
        Boolean res;

        @Override
        protected Integer doInBackground(String... params) {
            if(choose == 1){
                res = goWCF.register(account, password, local);
            }else{
                res = goWCF.login(account, password, local);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result){
            if(choose == 1){
                if( res == false ) {
                    Toast.makeText(login_page.this, "account already existed", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(login_page.this,"register Success", Toast.LENGTH_LONG ).show();
                }
            }else{
                if(res == true){
                    Intent intent = new Intent(login_page.this, MainListActivity.class);
                    onPause();
                    startActivity(intent);
                }else{
                    Toast.makeText(login_page.this, "login info are wrong", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


}


