package com.cse535.jerry.project_final;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Yanzhu on 2016/11/13.
 */

public class MainListActivity extends Activity {


    ListView list;
    ListView list2;

    private DBUtil dbo;
    private Context context;
    EditText et_search;
    String[] title;
    BagList adapter1;
    BagList adapter2;
    String[] id1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChangeUtil.changeTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mainlist);

        context = this.getApplicationContext();
        dbo = new DBUtil(this);
        try{
            dbo.createdb();
        }catch (Exception e){
            Toast.makeText(MainListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        adapter1 = new BagList(MainListActivity.this, TestBagInfo.bagtitle, TestBagInfo.bagprice, TestBagInfo.imageaddress);
        list=(ListView)findViewById(R.id.lv_baglist_5);
        list.setAdapter(adapter1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainListActivity.this, "You Clicked at " +TestBagInfo.bagid[position], Toast.LENGTH_SHORT).show();
                Intent intent_tobaginfo = new Intent(MainListActivity.this,BagInfoActivity.class);
                intent_tobaginfo.putExtra("ID",TestBagInfo.bagid[position]);
                startActivity(intent_tobaginfo);
            }
        });

    }

    public void isearch(View view){
        Toast.makeText(MainListActivity.this, "Test", Toast.LENGTH_SHORT).show();
        et_search = (EditText)findViewById(R.id.ll_searchtext_5);
        String searchtext = et_search.getText().toString().trim();

        int count = 0;
        for(int i=0;i<TestBagInfo.bagtitle.length;i++){
            if(TestBagInfo.bagtitle[i].contains(searchtext)){
                count++;
            }
        }

        title = new String[count];
        String[] price = new String[count];
        Integer[] image = new Integer[count];
        id1 = new String[count];
        int trackfilling = 0;

        for(int i=0;i<TestBagInfo.bagtitle.length;i++){
            if(TestBagInfo.bagtitle[i].contains(searchtext)){
                title[trackfilling] = TestBagInfo.bagtitle[i];
                price[trackfilling] = TestBagInfo.bagprice[i];
                image[trackfilling] = TestBagInfo.imageaddress[i];
                id1[trackfilling] = TestBagInfo.bagid[i];
                trackfilling++;
            }
        }

        list.setVisibility(View.INVISIBLE);
        adapter2 = new BagList(MainListActivity.this, title, price, image);
        list2=(ListView)findViewById(R.id.lv_baglist2_5);
        list2.setAdapter(adapter2);
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainListActivity.this, "You Clicked at " +title[+ position], Toast.LENGTH_SHORT).show();
                Intent intent_tobaginfo = new Intent(MainListActivity.this,BagInfoActivity.class);
                intent_tobaginfo.putExtra("ID",id1[position]);
                startActivity(intent_tobaginfo);
            }
        });

    }

    public void tChange(View view){
        MainListActivity.this.startActivity(new Intent(MainListActivity.this,ChangeThemeActivity.class));
    }

    public void newBagNoti(View view){
        Bitmap btm = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        Intent intent = new Intent(MainListActivity.this, MainListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainListActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification noti = new NotificationCompat.Builder(MainListActivity.this)
                .setSmallIcon(R.drawable.image1)
                .setLargeIcon(btm)
                .setNumber(13)
                .setContentIntent(pendingIntent)
                .setStyle(
                        new NotificationCompat.InboxStyle()
                                .addLine("There are some new bags now")
                                 .addLine("Come and have a look!")
                                 .setBigContentTitle("There are new bags now!!")
                                .setSummaryText("From Bag Rent APP"))
                                .build();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, noti);
    }

    public void myAccount(View view){
        Intent intent = new Intent(MainListActivity.this, MyAcountActivity.class);
        startActivity(intent);
    }

    public void buyBagNoti(View view){
        Bitmap btm = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        Intent intent = new Intent(MainListActivity.this, MainListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainListActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification noti = new NotificationCompat.Builder(MainListActivity.this)
                .setSmallIcon(R.drawable.image1)
                .setLargeIcon(btm)
                .setNumber(13)
                .setContentIntent(pendingIntent)
                .setStyle(
                        new NotificationCompat.InboxStyle()
                                .addLine("Your bag is booked by someone")
                                .addLine("Come and have a look!")
                                .setBigContentTitle("Your bag is rent!!")
                                .setSummaryText("From Bag Rent APP"))
                .build();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, noti);
    }

}

