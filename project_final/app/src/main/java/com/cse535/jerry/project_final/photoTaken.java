package com.cse535.jerry.project_final;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.util.ArrayList;

/**
 * Created by jerry on 2016/11/13.
 */

public class photoTaken extends AppCompatActivity {
    Activity activity;
    View Imgview;
    int REQUEST_CODE_CAPTURE = 1;

    public photoTaken(Activity a, View view) {
        this.activity = a;
        this.Imgview = view;
        Log.d("id", String.valueOf(view.getId()));
    }

    public void photoSource(){
        Config config = new Config();
        config.setCameraHeight(R.dimen.camera_height);
        config.setToolbarTitleRes(R.string.toolbar_title);
        config.setSelectionMin(1);
        config.setSelectionLimit(1);
        ImagePickerActivity.setConfig(config);
        Intent intent  = new Intent(activity, ImagePickerActivity.class);
        activity.startActivityForResult(intent,REQUEST_CODE_CAPTURE);
    }

    public void result(ArrayList<Uri> uirs){
        ImageView view =(ImageView) this.Imgview;
        for(Uri uri : uirs){
            view.setImageURI(uri);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAPTURE && resultCode == RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            Log.d("picture", "onResult");
            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            result(image_uris);

        }
    }


//    @Override
//    protected Void doInBackground(Void... params) {
//        return null;
//    }
}


