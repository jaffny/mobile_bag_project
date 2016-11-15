package com.cse535.jerry.project_final;

/**
 * Created by jerry on 2016/11/13.
 */

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.stream.Stream;

import static com.cse535.jerry.project_final.R.id.pwd;


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
                result = Boolean.valueOf(String.valueOf(envelope.getResponse()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("result", String.valueOf(result));
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
                Log.i("result", String.valueOf(envelope.getResponse()));
                String resOflogin = String.valueOf( response.getProperty("LoginSuc") );
                Log.i("RES", resOflogin);
//                res = new JSONObject(new String(buffer));
                result = Boolean.valueOf(resOflogin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void publish(String methodName, String account, Stream pic, String title, float price, String description, String location){
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
        rpc.addProperty("name", account);
        rpc.addProperty("pic", pwd);
        rpc.addProperty("title",title);
        rpc.addProperty("price",price);
        rpc.addProperty("description",description);
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
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                System.out.println(envelope.getResponse());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static JSONObject jsonFromString(String jsonObjectStr) {
//
//        JsonReader jsonReader = JSONObject.createReader(new StringReader(jsonObjectStr));
//        JsonObject object = jsonReader.readObject();
//        jsonReader.close();
//
//        return object;
//    }
}
