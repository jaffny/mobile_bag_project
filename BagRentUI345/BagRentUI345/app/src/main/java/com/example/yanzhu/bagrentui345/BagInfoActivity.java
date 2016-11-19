package com.example.yanzhu.bagrentui345;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Yanzhu on 2016/11/13.
 */

public class BagInfoActivity extends Activity {

    String bagID;
    ImageView iv_bag;
    TextView tv_titlecontent;
    TextView tv_pricecontent;
    TextView tv_rentercontent;
    TextView tv_descontent;
    TextView tv_reviewcontent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChangeUtil.changeTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_baginfo);

        Intent intent = getIntent();
        if(intent != null){
            bagID = intent.getStringExtra("ID");
        }

        iv_bag = (ImageView)findViewById(R.id.iv_bag_3);
        tv_titlecontent = (TextView)findViewById(R.id.tv_titlecontent_3);
        tv_pricecontent = (TextView)findViewById(R.id.tv_pricecontent_3);
        tv_rentercontent = (TextView)findViewById(R.id.tv_rentercontent_3);
        tv_descontent = (TextView)findViewById(R.id.tv_descontent_3);
        tv_reviewcontent = (TextView)findViewById(R.id.tv_reviewcontent_3);

        int id = Integer.valueOf(bagID) - 1;

        iv_bag.setImageResource(TestBagInfo.imageaddress[id]);
        tv_titlecontent.setText(TestBagInfo.bagtitle[id]);
        tv_pricecontent.setText(TestBagInfo.bagprice[id]);
        tv_rentercontent.setText(TestBagInfo.renter[id]);
        tv_descontent.setText(TestBagInfo.bagdescription[id]);
        tv_reviewcontent.setText(TestBagInfo.review[id]);

    }

    public void getIt(View view){
        Intent intent_orderbag = new Intent(BagInfoActivity.this,PlaceOrderActivity.class);
        intent_orderbag.putExtra("ID",bagID);
        startActivity(intent_orderbag);
    }


}
