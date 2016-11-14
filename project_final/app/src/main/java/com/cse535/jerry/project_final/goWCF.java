package com.cse535.jerry.project_final;

/**
 * Created by jerry on 2016/11/13.
 */

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.InputStream;
import java.io.InputStreamReader;


public class goWCF {

    public static JSONObject login( String account, String pwd, String local){
        JSONObject res = null;
        String url=""+account+"/"+pwd+"/"+local;
        try {
            // yeu cau server
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);

            // nhan lai doi tuong json
            HttpEntity responseEntity = response.getEntity();
            char[] buffer = new char[(int)responseEntity.getContentLength()];
            InputStream stream = responseEntity.getContent();
            InputStreamReader reader = new InputStreamReader(stream);
            reader.read(buffer);
            stream.close();
            res = new JSONObject(new String(buffer));
        } catch (Exception e) {
            android.util.Log.i("Loi roi : ",e.toString());
        }
        return res;
    }

    public static boolean rejester( String account, String pwd, String local){
        String url=""+account+"/"+pwd+"/"+local;
        boolean res = false;
        try {
            // yeu cau server
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);

            // nhan lai doi tuong json
            HttpEntity responseEntity = response.getEntity();
            char[] buffer = new char[(int)responseEntity.getContentLength()];
            InputStream stream = responseEntity.getContent();
            InputStreamReader reader = new InputStreamReader(stream);
            reader.read(buffer);
            stream.close();
            res = Boolean.parseBoolean(new String(buffer));
        } catch (Exception e) {
            android.util.Log.i("Loi roi : ",e.toString());
        }
        return res;
    }

    public static JSONObject login2( String account, String pwd, String local){
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "log_in";
        // EndPoint
        String endPoint = "http://40.77.101.210/Service1.svc";
        // SOAP Action
        String soapAction = "http://tempuri.org/IService1/"+ methodName;
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
        JSONObject result = null;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                System.out.println(envelope.getResponse());
//                res = new JSONObject(new String(buffer));
                result = new JSONObject((String)envelope.getResponse());
                Log.d("res", (String)envelope.getResponse());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}
