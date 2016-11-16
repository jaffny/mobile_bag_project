package com.cse535.jerry.project_final;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class rental_history_interface extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int REQUEST_CODE_CAPTURE = 1;
    public  ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_history_interface);
        Drawable img = null;
        String title = "test";
        Calendar cur = Calendar.getInstance();
//      temporarily
        Calendar lease = Calendar.getInstance();
        lease.add(Calendar.DAY_OF_MONTH, 2);
        setRow(img, title, cur, lease);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public void setRow(Drawable img, String title, Calendar cur, Calendar lease) {
        //Inflater service
//        testing
        int[] imgID = {
                R.drawable.bag0,
                R.drawable.bag1,
                R.drawable.bag2,
                R.drawable.bag3,
                R.drawable.bag4,
        };
        for(int i=0; i< 5; i++){
            LayoutInflater layoutInfralte=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableLayout tableLayout = (TableLayout) findViewById(R.id.table_lay_7);
            TableRow row = new TableRow(this);
            View view;
            view = layoutInfralte.inflate(R.layout.content_row_rental_history, null);
            TextView Title = (TextView)view.findViewById(R.id.title_name);
            String bag = "my bag" + Integer.toString(i);
            ImageView imgView = (ImageView)view.findViewById(R.id.picbag_7);
            imgView.setImageResource(imgID[i]);
            Title.setText(bag);
            TextView LeaseTime = (TextView)view.findViewById(R.id.lease_time);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            cur.add(Calendar.DAY_OF_MONTH,2);
            String str = df.format(cur.getTime());
            LeaseTime.setText(str);
            Button leaseBtn = (Button)view.findViewById(R.id.lease_btn);
            setLeaseClickEvent(leaseBtn, cur, LeaseTime);
            row.addView(view);
            tableLayout.addView(row);
//            RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.rowLayout);
            if(i%2 == 0){
                row.setBackgroundColor(Color.parseColor("#58ACFA"));
            }else{
                row.setBackgroundColor(Color.parseColor("#81F7F3"));
            }
        }

    }

//    public void setPicClickEvent(View row){
//        imgView = (ImageView)row.findViewById(R.id.picbag_7);
//        imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PhotoTaken();
//            }
//        });
//    }

    public void setLeaseClickEvent(Button lease, final Calendar c, final TextView time){
        lease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rental_history_interface.this,"prolong lease 7 days",Toast.LENGTH_LONG).show();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                c.add(Calendar.DAY_OF_MONTH,7);
                String str = df.format(c.getTime());
                time.setText(str);
            }
        });
    }

    private class AsyncBagHist extends AsyncTask<Void, Void, Void> {
        Boolean res;
        @Override
        protected Void doInBackground(Void... params) {
            String method;
//            res = goWCF.publish(method, name, pic, title, price, description, location);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
//            if( res == true){
//                Toast.makeText(Publish_interface.this, "publish success", Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(Publish_interface.this, "publish fail", Toast.LENGTH_LONG).show();
//            }
//            Toast.makeText(Publish_interface.this, "publish success", Toast.LENGTH_LONG).show();

        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("rental_history_interface Page") // TODO: Define a title for the content shown.
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
}
