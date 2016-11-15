package com.cse535.jerry.project_final;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyAcountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_acount);
        setUserInfo();
        Button publishBag = (Button) findViewById(R.id.pub_bag);
        Button publishReq = (Button) findViewById(R.id.pub_req);

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

        rentalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAcountActivity.this, rental_history_interface.class);
                startActivity(intent);
            }
        });
    }

    protected void setUserInfo(){
        SharedPreferences user = getSharedPreferences("user", 0);
        String user_name = user.getString("name", "AccountName");
        TextView view_name = (TextView)findViewById(R.id.account_name);
        view_name.setText( user_name );
    }
}
