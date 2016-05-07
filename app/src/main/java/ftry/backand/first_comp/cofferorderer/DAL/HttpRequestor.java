package ftry.backand.first_comp.cofferorderer.DAL;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.Common.IRoomCreator;
import ftry.backand.first_comp.cofferorderer.Common.IRoomValidator;
import ftry.backand.first_comp.cofferorderer.Common.IValuePoster;
import ftry.backand.first_comp.cofferorderer.Common.IValuePutter;
import ftry.backand.first_comp.cofferorderer.Common.Order;
import ftry.backand.first_comp.cofferorderer.DAL.ErrorListeners.ErrorListener;
import ftry.backand.first_comp.cofferorderer.Common.IValueDeleter;
import ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners.CreateRoomSuccess;
import ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners.SimpleStringSuccessListener;
import ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners.SimpleSuccessListener;
import ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners.SuccessListener;
import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncInitiater;
import ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners.ValidateRoomSuccessListener;

/**
 * Created by User on 4/8/2016.
 */

public class HttpRequestor implements IValueDeleter,IValuePoster,IValuePutter,IRoomCreator,IRoomValidator,Serializable
{

    public static RequestQueue queue;

    private SuccessListener SListener;
    private SimpleSuccessListener simple;
    private ErrorListener EListener;
    private UrlBuilder mBuilder;



    public HttpRequestor(Context context,SyncInitiater initiater, ArrayList<Order> currentOrders)
    {
        queue= Volley.newRequestQueue(context);
        SListener =new SuccessListener(initiater,currentOrders);
        EListener =new ErrorListener(context);
        simple=new SimpleSuccessListener();
        mBuilder=new UrlBuilder();

    }



    public void request(int roomId)
    {
        String url =mBuilder.BuildGetForRoom(roomId);
        CoffeeRequest stringRequest=new CoffeeRequest(Request.Method.GET, url,SListener,EListener);
        queue.add(stringRequest);
    }

    public void Post(String coffeyType,String sugar,String freeText,int roomId)
    {
        String url=mBuilder.BuildInsert(coffeyType, sugar, freeText,roomId);
        CoffeeRequest stringRequest=new CoffeeRequest(Request.Method.GET, url,SListener,EListener);

        queue.add(stringRequest);




    }


    @Override
    public void Delete(int id) {
        String url =String.format("https://api.backand.com:443/1/objects/CoffeeOrder/%d",id) ;
        CoffeeRequest stringRequest=new CoffeeRequest(Request.Method.DELETE, url,new SimpleStringSuccessListener(),EListener);

        queue.add(stringRequest);
    }

    @Override
    public void Put(Order order) {
        Map<String,String> params = new HashMap<>();
        params.put("CoffeeType",order.getCoffeType());
        params.put("Sugar",order.getSugar());
        params.put("FreeText",order.getFreeText());
        String url = String.format("https://api.backand.com:443/1/objects/CoffeeOrder/%d",order.getID()) ;
        JSONObject jsonBody=new JSONObject(params);

        JsonObjectRequest putRequest=new JsonObjectRequest(Request.Method.PUT,url,jsonBody,simple,EListener)
        {
            public Map<String, String> getHeaders()
            {
                Map<String,String> params= new HashMap<>();
                try {
                    if (!super.getHeaders().isEmpty())
                    {
                        Map<String,String> t=super.getHeaders();
                        params =t;
                    }


                } catch (AuthFailureError authFailureError) {
                    authFailureError.printStackTrace();
                }


                params.put("AnonymousToken","4bc7322e-c7c6-488b-80c1-efb9d2db63f8");
                params.put("Content-Type", "application/json; charset=utf-8");


                return params;
            }
        };
        queue.add(putRequest);

    }


    @Override
    public void CreateRoom(String roomName, String Password,Intent startingIntent,Context context) {
        CreateRoomSuccess success =new CreateRoomSuccess(context,startingIntent);
        Map<String,String> params = new HashMap<>();
        params.put("RoomName",roomName);
        params.put("Room_Password",Password);
        String url = "https://api.backand.com:443/1/objects/Room";

        JSONObject jsonBody=new JSONObject(params);

        JsonObjectRequest postRequest=new JsonObjectRequest(Request.Method.POST,url,jsonBody,success,EListener)
        {
            public Map<String, String> getHeaders()
            {
                Map<String,String> params= new HashMap<>();
                try {
                    if (!super.getHeaders().isEmpty())
                    {
                        Map<String,String> t=super.getHeaders();
                        params =t;
                    }


                } catch (AuthFailureError authFailureError) {
                    authFailureError.printStackTrace();
                }


                params.put("AnonymousToken","4bc7322e-c7c6-488b-80c1-efb9d2db63f8");
                params.put("Content-Type", "application/json; charset=utf-8");


                return params;
            }
        };

        queue.add(postRequest);




    }

    @Override
    public void ValidateRoom(String roomName, String Password, Intent startingIntent, Context context)
    {
        String url=mBuilder.BuildValidateRoom(roomName,Password);
        ValidateRoomSuccessListener successListener=new ValidateRoomSuccessListener(context,startingIntent);
        CoffeeRequest stringRequest=new CoffeeRequest(Request.Method.GET, url,successListener,EListener);
        queue.add(stringRequest);
    }
}
