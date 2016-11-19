package com.cse535.jerry.project_final;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Yanzhu on 2016/11/14.
 */

public class OrderSuccessActivity extends Activity {

    String bagID;
    String location;
    String dp;
    String tp;
    Context context;
    File dir;
    File file;
    int image;

    private static final String AUTHORITY="com.example.yanzhu.bagrentui345";
    FileOutputStream fOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChangeUtil.changeTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ordersuccess);
        context = this.getApplicationContext();

        Intent intent = getIntent();
        if(intent != null){
            bagID = intent.getStringExtra("id");
            location = intent.getStringExtra("location");
            dp = intent.getStringExtra("datepick");
            tp = intent.getStringExtra("timepick");
            image = intent.getIntExtra("image",0);
        }



    }

    public void printconfirmation(View view){
        Document doc = new Document();

        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

           // File dir = new File(path);
            dir = context.getFilesDir();
            //File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//            if(!dir.exists()){
//                Toast.makeText(OrderSuccessActivity.this, "to create", Toast.LENGTH_SHORT).show();
//                dir.mkdirs();
//                Toast.makeText(OrderSuccessActivity.this, String.valueOf(dir.isDirectory()), Toast.LENGTH_SHORT).show();
//            }

            file = new File(dir, "Confirmation.pdf");

            Toast.makeText(OrderSuccessActivity.this, String.valueOf(file.isFile())+file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);



            //open the document
            doc.open();


            Paragraph p1 = new Paragraph("Your bagID is " + bagID);
            Font paraFont= new Font(Font.FontFamily.COURIER);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setFont(paraFont);

            //add paragraph to document
            doc.add(p1);

            Paragraph p2 = new Paragraph("You will pick it up on " + dp+" at "+tp+" in "+location);
            Font paraFont2= new Font(Font.FontFamily.COURIER,14.0f, Color.GREEN);
            p2.setAlignment(Paragraph.ALIGN_CENTER);
            p2.setFont(paraFont2);

            doc.add(p2);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
            Image myImg = Image.getInstance(stream.toByteArray());
            myImg.setAlignment(Image.MIDDLE);

            //add image to document
            doc.add(myImg);




        } catch (DocumentException de) {

        } catch (IOException e) {

        }
        finally
        {
            doc.close();
            //Toast.makeText(OrderSuccessActivity.this, "Generated", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

            Intent i= new Intent(Intent.ACTION_VIEW, FileProvider.getUriForFile(this, AUTHORITY, file));
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(i);
        }
    }

}
