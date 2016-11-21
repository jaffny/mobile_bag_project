package com.cse535.jerry.project_final;

/**
 * Created by jerry on 2016/11/13.
 */

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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static boolean publish(String methodName, String account, byte[] pic, String title, float price, String description, String location){
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
        Log.i("info",methodName+"____"+account+"___"+title+"___"+ valueOf(price)+"___"+description+"___"+location);
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
            pi = new PropertyInfo();
            pi.setName("pic");
            pi.setValue(pic);
            pi.setType(pic.getClass());
            rpc.addProperty(pi);
//            rpc.addProperty("pic", pic);
        }
        if( title != null){
            pi = new PropertyInfo();
            pi.setName("title");
            pi.setValue(title);
            pi.setType(title.getClass());
            rpc.addProperty(pi);
//            rpc.addProperty("title", title);
        }
        Log.i("propertyCount after", valueOf(rpc.getPropertyCount()) );
        Log.i("info",rpc.getProperty(0).toString()+"__"+rpc.getProperty(1).toString());
        Log.i("info",rpc.getProperty(2).toString()+"__"+rpc.getProperty(3).toString());
        Log.i("info",rpc.getProperty(4).toString()+"__"+rpc.getProperty(5).toString());
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
        boolean res = false;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                System.out.println(envelope.getResponse());
//                Log.i("result","test test test");
            }else{
                res = true;
                Log.i("result", "fall here?");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
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
                System.out.println(envelope.getResponse());
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
//                System.out.println(envelope.getResponse());
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
        byte[] img = Base64.decode(imgData.toString(), Base64.DEFAULT);
        System.out.println(img.toString());
        System.out.println("#########transfer()2##############");
        byte[] pic =   (((String) (((SoapPrimitive)data.getProperty("Pic")).getValue()) ).getBytes());
        String title = valueOf( data.getProperty("Title"));
        Float price = Float.valueOf( (String) (((SoapPrimitive)data.getProperty("Price")).getValue()) );
        if( price<1 ){
            price = random_price();
        }
        Integer location_count = ((SoapObject)data.getProperty("Location")).getPropertyCount();
        Integer descript_count = ((SoapObject)data.getProperty("Description")).getPropertyCount();
        String local = "Tempe,85281";
        if (location_count > 0)
            local = valueOf( ((SoapObject)data.getProperty("Location")).getProperty(0) );
        String description = " That is a really good bag. ";
        if (descript_count > 0)
            description = valueOf( ((SoapObject)data.getProperty("Description")).getProperty(0) );
        Bag bag = new Bag(BagID, title, description, name, pic, price);
//        Log.i("name", bag.getAccount());
//        Log.i("id", ((Integer)bag.getBagid()).toString());
//        Log.i("price",((Float)bag.getPrice()).toString());
////        Log.i("location",);
//        Log.i("descript", bag.getDescription());
        return bag;
    }

    public static float random_price(){
        double max = 120.0;
        double min = 10.0;
        Random random = new Random();
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        return (float) shifted; // == (rand.nextDouble() * (max-min)) + min;
    }

//  
}
