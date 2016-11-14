package com.cse535.jerry.project_final;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.util.ArrayList;
//import io.valuesfeng.picker.engine.GlideEngine;

public class Publish_interface extends AppCompatActivity {
    public static final int REQUEST_CODE_PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_interface);
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

}









