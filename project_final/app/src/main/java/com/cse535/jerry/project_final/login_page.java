package com.cse535.jerry.project_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.SystemClock.sleep;

public class login_page extends AppCompatActivity {
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private String account;
    private String password;
    private String local;
    private String long_local;
    private int choose = 0;
    private String[] ads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        requestGPSPermission();
    }

    public void requestGPSPermission(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                System.out.println("show the ShowRequestPermissionRationale");
            } else {
                System.out.println("request Permission");
                ActivityCompat.requestPermissions( this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
            }
        }else{
            System.out.println("don't need request permission");
            ads=curlocal();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ads = curlocal();
                    while(ads == null){
                        Log.i("addr",  "deny~~~~~~~~~~~~~~");
                        sleep(1000);
                    }
                } else {
                    // permission denied, boo! Disable the
                }
            }
        }
    }

    private void getCurLogin(){
        TextView accountView = (EditText)findViewById(R.id.name_1);
        TextView pwdView = (EditText)findViewById(R.id.pwd_1);
        account = accountView.getText().toString();
        password = pwdView.getText().toString();
        local = ads[0];
        long_local = ads[1];
    }

    private String[] curlocal(){
        GPSTracker  gps = new GPSTracker(login_page.this);
        String[] addr = null;
        if(gps.canGetLocation()){
            try {
//                double latitude = gps.getLatitude();
//                double longitude = gps.getLongitude();
//                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude +"\nlocality:" + addr, Toast.LENGTH_LONG).show();
                addr = gps.getAddress();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            gps.showSettingsAlert();
        }
        return addr;
    }

    public void loginEvent(View view){
        getCurLogin();
        if(account.length() == 0 || password.length() == 0){
            Toast.makeText(login_page.this,"login Error", Toast.LENGTH_LONG ).show();
        }else{
            choose = 0;
            AsyncLogin task = new AsyncLogin();
            task.execute();
        }
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


