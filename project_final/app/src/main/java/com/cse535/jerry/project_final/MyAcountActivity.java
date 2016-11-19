package com.cse535.jerry.project_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyAcountActivity extends AppCompatActivity {
    String name;
    String location;
    List<Bag> bags = new ArrayList<Bag>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_acount);
        getUserInfo();
        AsyncMyAccount task = new AsyncMyAccount();
        task.execute();
        Button publishBag = (Button) findViewById(R.id.pub_bag);
        Button publishReq = (Button) findViewById(R.id.pub_req);
        TextView myBag = (TextView)findViewById(R.id.my_bag);
        TextView rentalHistory = (TextView) findViewById(R.id.rental_hist);
        publishBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAcountActivity.this, Publish_interface.class);
                intent.putExtra("PubInterface", 0);
                startActivity(intent);
            }
        });

        publishReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAcountActivity.this, Publish_interface.class);
                intent.putExtra("PubInterface", 1);
                startActivity(intent);
            }
        });

//        rentalHistory or my Order
        rentalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAcountActivity.this, rental_history_interface.class);
                intent.putExtra("ListInterface",0);
                intent.putExtra("bags", (Serializable) bags);
                startActivity(intent);
            }
        });
//        my bag
        myBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAcountActivity.this, rental_history_interface.class);
                intent.putExtra("ListInterface",1);
                startActivity(intent);
            }
        });
    }

//    get session user info
    protected void getUserInfo(){
        SharedPreferences user = getSharedPreferences("user", 0);
        String user_name = user.getString("name", "AccountName");
        location = user.getString("local", "Tempe,85281");
        Boolean show_detail = user.getBoolean("show", true);
        if(show_detail == true){
            SharedPreferences.Editor PE = user.edit();
            PE.putBoolean("show_detail", false);
            String adds_detail = user.getString("local_detail", " 699 S Mill Ave #108\n Tempe AZ 85281\n USA");
            Toast.makeText(this,adds_detail, Toast.LENGTH_LONG).show();
        }
        TextView view_name = (TextView)findViewById(R.id.account_name);
        view_name.setText( user_name );
    }

    private void bag_list_test(){
        int[] bagID = {
                R.drawable.bag0,
                R.drawable.bag1,
                R.drawable.bag2,
                R.drawable.bag3,
                R.drawable.bag4,
        };
        List<Bag> mybags;
        for( int i=0; i<=bagID.length; i++) {
            Bag mybag = new Bag(i + 1, "bag" + String.valueOf(i + 1), "This is a good bag!!!", name, pic2byteArray(bagID[i]), (float) randomInRange(10.0, 50.9));
        }

    }

    private byte[] pic2byteArray( int draw ){
        Resources res = getResources();
        Drawable drawable = res.getDrawable(draw);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        return bitMapData;
    }

    public static double randomInRange(double min, double max) {
        Random random = new Random();
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }

    private class AsyncMyAccount extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
//            String method;
            bags = goWCF.bagList( name );
            return null;
        }

        @Override
        protected void onPostExecute(Integer result){
//            SharedPreferences user = getSharedPreferences("user", 0);
//            SharedPreferences.Editor PE = user.edit();
//            PE.put("bags", bags.toString());

//            if( res == true){
//                Toast.makeText(Publish_interface.this, "publish success", Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(Publish_interface.this, "publish fail", Toast.LENGTH_LONG).show();
//            }


        }
    }

}





