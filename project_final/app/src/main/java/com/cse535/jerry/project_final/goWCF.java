package com.cse535.jerry.project_final;

/**
 * Created by jerry on 2016/11/13.
 */

import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.util.Base64.DEFAULT;
import static java.lang.String.valueOf;


public class goWCF {

    public static boolean register( String account, String pwd, String local){
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "register";
        // EndPoint
        String endPoint = "http://40.77.101.210/OrderService.svc";
        // SOAP Action
        String soapAction = "http://tempuri.org/IOrderService/"+ methodName;
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("name", account);
        rpc.addProperty("password", pwd);
        rpc.addProperty("location",local);
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        Boolean result = null;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                result = Boolean.valueOf(valueOf(envelope.getResponse()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("result", valueOf(result));
        return result;
    }

    public static boolean login( String account, String pwd, String local){
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "log_in";
        // EndPoint
        String endPoint = "http://40.77.101.210/OrderService.svc";
        // SOAP Action
        String soapAction = "http://tempuri.org/IOrderService/"+ methodName;
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("name", account);
        rpc.addProperty("password", pwd);
        rpc.addProperty("location",local);
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        Boolean result = null;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                System.out.println(envelope.getResponse());
                SoapObject response = (SoapObject) envelope.getResponse();
//                Log.i("result", valueOf(envelope.getResponse()));
                String resOflogin = valueOf( response.getProperty("LoginSuc") );
//                Log.i("RES", resOflogin);
//                res = new JSONObject(new String(buffer));
                result = Boolean.valueOf(resOflogin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean publish(String methodName, String account, byte[] pic, String title, float price, String description, String location) throws IOException {
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
//        String methodName = "log_in";
        // EndPoint
        String endPoint = "http://40.77.101.210/OrderService.svc";
        // SOAP Action
        String soapAction = "http://tempuri.org/IOrderService/"+ methodName;
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        Log.i("info",methodName+"____"+account+"___"+title+"___"+ valueOf(price)+"___"+description+"___"+pic.toString());
        Log.i("propertyCount pervious", valueOf(rpc.getPropertyCount()) );
        PropertyInfo pi;
        if(description != null){
            pi = new PropertyInfo();
            pi.setName("description");
            pi.setValue(description);
            pi.setType(description.getClass());
            rpc.addProperty(pi);
//            rpc.addProperty("description",description);
        }
        if(location != null){
            pi = new PropertyInfo();
            pi.setName("location");
            pi.setValue(location);
            pi.setType(location.getClass());
            rpc.addProperty(pi);
//            rpc.addProperty("location",location);
        }
        if( account != null){
            pi = new PropertyInfo();
            pi.setName("name");
            pi.setValue(account);
            pi.setType(account.getClass());
            rpc.addProperty(pi);
//            rpc.addProperty("name", account);
        }
        if(price != 0){
            pi = new PropertyInfo();
            pi.setName("price");
            pi.setValue( String.valueOf(price) );
            pi.setType(String.valueOf(price).getClass());
            rpc.addProperty(pi);
//            rpc.addProperty("price", price);
        }
        if(pic != null){
            String pic_string = Base64.encodeToString(pic, Base64.DEFAULT);
//            pi = new PropertyInfo();
//            pi.setName("pic");
//            pi.setValue(pic_string);
//            pi.setType(pic_string.getClass());
//            rpc.addProperty(pi);
//
//            rpc.addProperty(pi);
            rpc.addProperty("pic",pic_string);
            String test_img = (String)rpc.getProperty(4);
            byte[] data = Base64.decode(test_img, Base64.DEFAULT);

//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            ObjectOutputStream os = new ObjectOutputStream(out);
//            os.writeObject(img);
//            byte[]  image =  out.toByteArray();
//
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/folder");
            myDir.mkdirs();
            String fname = "image.jpg";
            File file = new File (myDir, fname);
            if (!file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();

//            Log.i("hah","!-----------------------------compare byte array  "  + Arrays.equals(pic,image));

        }
        if( title != null){
//            pi = new PropertyInfo();
//            pi.setName("title");
//            pi.setValue(title);
//            pi.setType(title.getClass());
//            rpc.addProperty(pi);
            rpc.addProperty("title", title);
        }
        Log.i("propertyCount after", valueOf(rpc.getPropertyCount()) );
        Log.i("info",rpc.getProperty(0).toString()+"__"+rpc.getProperty(1).toString());
        Log.i("info",rpc.getProperty(2).toString()+"__"+rpc.getProperty(3).toString());
        Log.i("info",rpc.getProperty(4).toString()+"__"+rpc.getProperty(5).toString());

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        envelope.setAddAdornments(false);
        envelope.implicitTypes = true;
        (new MarshalBase64()).register(envelope);
        new MarshalFloat().register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        Boolean result = null;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                result = Boolean.valueOf(valueOf(envelope.getResponse()));
            }else
                result=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Bag> bagList( String location ){
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "request_baglist";
        // EndPoint
        String endPoint = "http://40.77.101.210/OrderService.svc";
        // SOAP Action
        String soapAction = "http://tempuri.org/IOrderService/"+ methodName;
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("location",location);

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        System.out.println("#######################");
        System.out.println("###############"+location+"############");
        System.out.println("#######################");
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        List<Bag> bags = new ArrayList<Bag>();
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                System.out.println("###############requetst__baglist##################");
                System.out.println(envelope.getResponse());
                System.out.println("###############requetst__baglist##################");
                SoapObject response = (SoapObject) envelope.getResponse();
//
                for(int i = 0; i<((SoapObject) envelope.getResponse()).getPropertyCount();i++){
                    Object data = ((SoapObject) envelope.getResponse()).getProperty(i);
//                    Log.i("result data", valueOf(data));
//                    ObjectInputStream obj = new ObjectInputStream(data);
                    SoapObject s= (SoapObject)data;
//                    Log.i("result sss", valueOf(s));
                    Bag bag = transfer(s);
                    Log.i("name", bag.getAccount());
                    Log.i("id", ((Integer)bag.getBagid()).toString());
                    Log.i("price",((Float)bag.getPrice()).toString());
//        Log.i("location",);
                    Log.i("descript", bag.getDescription());
                    bags.add(transfer(s));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bags;
    }


    public static List<Bag> mybag( String name ){
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "request_my_bags";
        // EndPoint
        String endPoint = "http://40.77.101.210/OrderService.svc";
        // SOAP Action
        String soapAction = "http://tempuri.org/IOrderService/"+ methodName;
        System.out.println("#######################");
        System.out.println("###############"+name+"############");
        System.out.println("#######################");
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("name",name);
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        List<Bag> bags = new ArrayList<Bag>();
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                System.out.println("###############requetst__mybag##################");
                System.out.println(envelope.getResponse());
                System.out.println("###############requetst__mybag##################");
                SoapObject response = (SoapObject) envelope.getResponse();
                for(int i = 0; i<((SoapObject) envelope.getResponse()).getPropertyCount();i++){
                    Object data = ((SoapObject) envelope.getResponse()).getProperty(i);
                    SoapObject s= (SoapObject)data;
                    Bag bag = transfer(s);
//                    System.out.println("#########activity_list_interface##############");
//                    System.out.println(bag.getByteArray());
//                    bag.bytes2bitmap(bag.getByteArray());
//                    System.out.println(bag.getBitmap());
//                    System.out.println("#########activity_list_interface##############");
//                    Log.i("name", bag.getAccount());
//                    Log.i("id", ((Integer)bag.getBagid()).toString());
//                    Log.i("price",((Float)bag.getPrice()).toString());
//                    Log.i("descript", bag.getDescription());
                    bags.add(transfer(s));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bags;
    }

    public static void delete_bag(int BagID){
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "delete_bag";
        // EndPoint
        String endPoint = "http://40.77.101.210/OrderService.svc";
        // SOAP Action
        String soapAction = "http://tempuri.org/IOrderService/"+ methodName;
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        System.out.print("the BagID number:"+ BagID);
        rpc.addProperty("bag_id",BagID);
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        (new MarshalFloat()).register(envelope);
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                System.out.print("################## delete event1 #####################");
                System.out.println(envelope.getResponse());
                System.out.print("delete finish");
                System.out.print("################## delete event1 #####################");
//                SoapObject response = (SoapObject) envelope.getResponse();
            }else{
                System.out.print("################## delete event2 #####################");
                System.out.print("delete finish");
                System.out.print("################## delete event2 #####################");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Review> reviewList(SoapObject datalist, int n){
        List<Review> res = null;
        for(int i=0 ; i<n ; i++){
            SoapObject data = (SoapObject)datalist.getProperty(i);
            String buyer = (String)data.getProperty("buyername");
            String description = (String)data.getProperty("description");
            Review re = new Review(buyer, description);
            res.add(re);
        }
        return res;
    }

    public static Bag transfer(SoapObject data){
        String name = valueOf( data.getProperty("Account") );
        Integer BagID = Integer.valueOf( (String) (((SoapPrimitive)data.getProperty("BadID")).getValue()) );
        System.out.println("#########transfer()1##############");
        SoapPrimitive imgData = (SoapPrimitive) data.getProperty("Pic");
        System.out.println(imgData.toString());
        String imgStr = imgData.toString();
        byte[] img = Base64.decode(imgData.toString(), DEFAULT);
        System.out.println(img.toString());
        System.out.println("#########transfer()2##############");
//        byte[] pic =   (((String) (((SoapPrimitive)data.getProperty("Pic")).getValue()) ).getBytes());
        String title = valueOf( data.getProperty("Title"));
        Float price = Float.valueOf( (String) (((SoapPrimitive)data.getProperty("Price")).getValue()) );
        if( price<1.0 ){
            price = random_price();
        }
//        Integer location_count = ((SoapObject)data.getProperty("Location")).getPropertyCount();
//        Integer descript_count = ((SoapObject)data.getProperty("Description")).getPropertyCount();
        String local = "Tempe,85281";
        local = valueOf( data.getProperty("Location") );
        String description = " That is a really good bag. ";
        description = valueOf( data.getProperty("Description") );
        Bag bag = new Bag(BagID, title, description, name, img, price);
        return bag;
    }

    public static float random_price(){
        double max = 120.0;
        double min = 10.0;
        Random random = new Random();
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        DecimalFormat format = new DecimalFormat("#.##");
        String val = format.format(shifted);
        return (float)Float.valueOf(val); // == (rand.nextDouble() * (max-min)) + min;
    }

//  
}
