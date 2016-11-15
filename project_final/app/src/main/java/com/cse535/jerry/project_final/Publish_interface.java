package com.cse535.jerry.project_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.util.ArrayList;
import java.util.stream.Stream;


//import io.valuesfeng.picker.engine.GlideEngine;

@SuppressWarnings("ResourceAsColor")
public class Publish_interface extends AppCompatActivity {
    public static final int REQUEST_CODE_PICK_IMAGE = 1;
    public int Interface_switch = 0;
    public String name;
    public Stream pic;
    public String title;
    public float price;
    public String decription;
    public String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_interface);
        setUserInfo();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Interface_switch = extras.getInt("PubInterface");
        }
        if(Interface_switch == 0){
            RelativeLayout base = (RelativeLayout)findViewById(R.id.activity_publish_interface);
            base.setBackgroundColor(R.color.Beige_J);
        }
    }

    protected void setUserInfo(){
        SharedPreferences user = getSharedPreferences("user", 0);
        name = user.getString("name", "AccountName");
        location = user.getString("local", "Tempe,85281");
    }

    public void PhotoTaken(View view) {
        Config config = new Config();
        config.setCameraHeight(R.dimen.camera_height);
        config.setToolbarTitleRes(R.string.toolbar_title);
        config.setSelectionMin(1);
        config.setSelectionLimit(1);
        ImagePickerActivity.setConfig(config);
        Intent intent  = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent,REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView view = (ImageView) findViewById(R.id.image_6);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode==RESULT_OK) {
//            Uri uri = data.getData();
//            view.setImageURI(uri);
            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            for(Uri uri : image_uris){
                view.setImageURI(uri);
            }

        }
    }

//    private class AsyncPublish extends AsyncTask<String, Void, Integer> {
//        Boolean res;
//        @Override
//        protected Integer doInBackground(String... params) {
//            if(Interface_switch == 0){
//                res = goWCF.publish(account, password, local);
//            }else{
//                res = goWCF.login2(account, password, local);
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Integer result){
//            if(choose == 1){
//                if( res == false ) {
//                    Toast.makeText(login_page.this, "account already existed", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(login_page.this,"register Success", Toast.LENGTH_LONG ).show();
//                }
//            }else{
//                if(res == true){
//                    Intent intent = new Intent(login_page.this, MyAcountActivity.class);
//                    onPause();
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(login_page.this, "login info are wrong", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//    }

}









