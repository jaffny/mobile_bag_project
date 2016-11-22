package com.cse535.jerry.project_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class list_interface extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int REQUEST_CODE_CAPTURE = 1;
    public  ImageView imgView;
    public int Interface_switch = 0;
    public String name;
    public List<Bag> bags = new ArrayList<Bag>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_interface);
        getUserInfo();
        AsyncBagHist task = new AsyncBagHist();
        task.execute();
        Drawable img = null;
        String title = "test";
        Calendar cur = Calendar.getInstance();
//      temporarily
        Calendar lease = Calendar.getInstance();
        lease.add(Calendar.DAY_OF_MONTH, 2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Interface_switch = extras.getInt("ListInterface");
        }
        bags = (List<Bag>) extras.get("bags");
        setInterface(bags);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void getUserInfo(){
        SharedPreferences user = getSharedPreferences("user", 0);
        name = user.getString("name", "AccountName");
    }

    public void setInterface(List<Bag> bags){
        int i = 0;
        for( Bag bag : bags ){
//      temporarily
            Calendar cur = Calendar.getInstance();
            Calendar lease = Calendar.getInstance();
            lease.add(Calendar.DAY_OF_MONTH, 2);
            System.out.println("#########activity_list_interface##############");
            System.out.println(bag.getByteArray());
            bag.bytes2bitmap(bag.getByteArray());
            System.out.println(bag.bytes2bitmap(bag.getByteArray()));
            System.out.println("#########activity_list_interface##############");
            if (Interface_switch == 1) {
                setRow_myBag(i, bag,  cur, lease);
            } else if (Interface_switch == 0){
                setRow_myHist(i, bag , cur, lease);
            }
            i++;
        }
    }

    public void setRow_myHist(int i, Bag bag, Calendar cur, Calendar lease) {
        //Inflater service
//        testing
        int[] imgID = {
                R.drawable.bag0,
                R.drawable.bag1,
                R.drawable.bag2,
                R.drawable.bag3,
                R.drawable.bag4,
        };
//        for(int i=0; i< 5; i++){
            LayoutInflater layoutInfralte=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableLayout tableLayout = (TableLayout) findViewById(R.id.table_lay_7);
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
            TableRow row = new TableRow(this);
            row.setLayoutParams(params);

            View view;
            view = layoutInfralte.inflate(R.layout.content_row_rental_history, null);
            view.setLayoutParams(params);
            TextView Title = (TextView)view.findViewById(R.id.title_name);
            //Testing use
//            String bag = "my bag" + Integer.toString(i);
//            imgView.setImageResource(imgID[i]);
            ImageView imgView = (ImageView)view.findViewById(R.id.picbag_7);
            imgView.setImageBitmap(bag.bytes2bitmap(bag.getByteArray()));
            Title.setText(bag.getTitle());
            TextView LeaseTime = (TextView)view.findViewById(R.id.lease_time);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            cur.add(Calendar.DAY_OF_MONTH,2);
            String str = df.format(cur.getTime());
            LeaseTime.setText(str);
            Button leaseBtn = (Button)view.findViewById(R.id.lease_btn);
            setLeaseClickEvent(leaseBtn, cur, LeaseTime);
            Button reviewBtn = (Button)view.findViewById(R.id.review_btn);
            setReviewClickEvent(reviewBtn);
            row.addView(view);
            tableLayout.addView(row);
//            RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.rowLayout);
            if(i%2 == 0){
                row.setBackgroundColor(Color.parseColor("#58ACFA"));
            }else{
                row.setBackgroundColor(Color.parseColor("#81F7F3"));
            }
//        }

    }

    public void setRow_myBag(int i, Bag bag, Calendar cur, Calendar lease){
        //        testing
        int[] imgID = {
                R.drawable.bag0,
                R.drawable.bag1,
                R.drawable.bag2,
                R.drawable.bag3,
                R.drawable.bag4,
        };
//        for(int i=0; i< 5; i++){
            LayoutInflater layoutInfralte=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableLayout tableLayout = (TableLayout) findViewById(R.id.table_lay_7);
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
            TableRow row = new TableRow(this);
            row.setLayoutParams(params);

            View view;
            view = layoutInfralte.inflate(R.layout.content_row_my_bag, null);
            view.setLayoutParams(params);
            TextView Title = (TextView)view.findViewById(R.id.title_name_8);
            ImageView imgView = (ImageView)view.findViewById(R.id.picbag_8);
            imgView.setImageBitmap(bag.bytes2bitmap(bag.getByteArray()));
            Title.setText(bag.getTitle());
            TextView Price = (TextView)view.findViewById(R.id.price_val_8);
            Price.setText(String.valueOf(bag.getPrice()));
            Button editBtn = (Button)view.findViewById(R.id.edit_btn);
            setEditClickEvent(i, editBtn, bag);
            Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
            setDeleteClickEvent(deleteBtn);
        row.addView(view);
        tableLayout.addView(row);
        if(i%2 == 0){
            row.setBackgroundColor(Color.parseColor("#FFE7A728"));
        }else{
            row.setBackgroundColor(Color.parseColor("#efdd1e"));
        }
//        }
    }
    public void setEditClickEvent(final int i, Button edit, final Bag bag){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(list_interface.this, Publish_interface.class);
                intent.putExtra("PubInterface", 2);
                intent.putExtra("baglist_index", i);
                intent.putExtra("bag", bag);
                startActivity(intent);
            }
        });
    }

    public void setDeleteClickEvent(Button delete){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               delete fuction aysncTask
            }
        });
    }

    public void setReviewClickEvent(Button review){
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(list_interface.this, review_bag.class);
                startActivity(intent);
            }
        });
    }

    public void setLeaseClickEvent(Button lease, final Calendar c, final TextView time){
        lease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(list_interface.this,"prolong lease 7 days",Toast.LENGTH_LONG).show();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                c.add(Calendar.DAY_OF_MONTH,7);
                String str = df.format(c.getTime());
                time.setText(str);
            }
        });
    }

    private class AsyncBagHist extends AsyncTask<Void, Void, Void> {
//        Boolean res;
        List<Bag> res ;
        @Override
        protected Void doInBackground(Void... params) {
            String method;

//            res = goWCF.bagList( name );
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

}
