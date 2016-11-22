package com.cse535.jerry.project_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


//import io.valuesfeng.picker.engine.GlideEngine;

@SuppressWarnings("ResourceAsColor")
public class Publish_interface extends AppCompatActivity {
    public static final int REQUEST_CODE_PICK_IMAGE = 1;
    public int Interface_switch = 0;
    public String name;
//    public InputStream pic;
    public byte[] pic = null;
    public String title ="";
    public float price = 0;
    public String description="";
    public String location="";
    public Bag bag;
    public int bagID;
    public int baglist_index;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_interface);
        setUserInfo();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Interface_switch = extras.getInt("PubInterface");
        }
        if (Interface_switch == 0) {
            RelativeLayout base = (RelativeLayout) findViewById(R.id.activity_publish_interface);
            Toast.makeText(this,"Bag", Toast.LENGTH_SHORT).show();
            base.setBackgroundColor(Color.parseColor("#ffe48d"));
        } else if (Interface_switch == 2){
            RelativeLayout base = (RelativeLayout) findViewById(R.id.activity_publish_interface);
            Toast.makeText(this,"Edit", Toast.LENGTH_SHORT).show();
            base.setBackgroundColor(Color.parseColor("#2df9ae"));
            baglist_index = (int)extras.get("baglist_index");
            bag = (Bag)extras.get("bag");
            setEdit_Interface(bag);
        }else{
            RelativeLayout base = (RelativeLayout) findViewById(R.id.activity_publish_interface);
            Toast.makeText(this,"Request", Toast.LENGTH_SHORT).show();
            base.setBackgroundColor(Color.parseColor("#399ef5"));
        }

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void setUserInfo() {
        SharedPreferences user = getSharedPreferences("user", 0);
        name = user.getString("name", "AccountName");
        location = user.getString("local", "Tempe,85281");
    }

    public void setEdit_Interface(Bag bag){
        ImageView Img = (ImageView)findViewById(R.id.image_6);
        EditText Title = (EditText)findViewById(R.id.key_title);
        EditText Price = (EditText)findViewById(R.id.key_price);
        EditText Description = (EditText)findViewById(R.id.key_descript);
        Img.setImageBitmap(bag.bytes2bitmap(bag.getByteArray()));
        Title.setText(bag.getTitle());
        Price.setText(String.valueOf(bag.getPrice()));
        Description.setText(bag.getDescription());
        bagID = bag.getBagid();
        description = bag.getDescription();
        title = bag.getTitle();
        price = bag.getPrice();
        pic = bag.getByteArray();
    }
    public void PhotoTaken(View view) {
        Config config = new Config();
        config.setCameraHeight(R.dimen.camera_height);
        config.setToolbarTitleRes(R.string.toolbar_title);
        config.setSelectionMin(1);
        config.setSelectionLimit(1);
        ImagePickerActivity.setConfig(config);
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView view = (ImageView) findViewById(R.id.image_6);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            for (Uri uri : image_uris) {
                Uri imgUri = Uri.parse("file://"+uri.toString());
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 1, bos);
//                    view.setImageBitmap(bmp);
//                    Log.i("img size", String.valueOf( bos.size() ));
                    pic = bos.toByteArray();
                   Bitmap bit =  BitmapFactory.decodeByteArray(pic, 0, pic.length);
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    System.out.println(pic.length);
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");



                    view.setImageBitmap(bit);
//                    InputStream iStream = getContentResolver().openInputStream(imgUri);
//                    pic = getBytes(iStream);
//                    iStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        byte[] bytesResult = null;
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        bytesResult = byteBuffer.toByteArray();
        byteBuffer.close();
        return bytesResult;
    }

    public void submitEvent(View view){
        title = ((EditText)findViewById(R.id.key_title)).getText().toString();
        price = Float.valueOf( ((EditText)findViewById(R.id.key_price)).getText().toString() );
        description = ((EditText)findViewById(R.id.key_descript)).getText().toString();
        if ( pic == null || title.length() ==0 || price == 0 || description.length() ==0  ){
            Toast.makeText(Publish_interface.this, "publish/edit fail", Toast.LENGTH_LONG).show();
        }else {
            AsyncPublish task = new AsyncPublish();
            task.execute();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Publish_interface Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class AsyncPublish extends AsyncTask<String, Void, Integer> {
        Boolean res;
        @Override
        protected Integer doInBackground(String... params) {
            String method;
            if(Interface_switch == 0){
                method = "publish_bag";
            }else{
                method = "publish_req";
//                method = "ReqInfo";
            }
            try {
                res = goWCF.publish(method, name, pic, title, price, description, location);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result){
            if( res == true){
                Toast.makeText(Publish_interface.this, "publish/edit success", Toast.LENGTH_LONG).show();
                if(Interface_switch == 2){
                    bag.setByteArray(pic);
                    bag.setTitle(title);
                    bag.setPrice(price);
                    bag.setDescription(description);
                    Intent intent = new Intent(Publish_interface.this, list_interface.class);
                    intent.putExtra("ListInterface",1);
                    intent.putExtra("bag", bag);
                    intent.putExtra("baglist_index",baglist_index);
                    startActivity(intent);
                }
            }else{
                Toast.makeText(Publish_interface.this, "publish/edit fail", Toast.LENGTH_LONG).show();
            }
//                Toast.makeText(Publish_interface.this, "publish success", Toast.LENGTH_LONG).show();

        }
    }

}









