package ftry.backand.first_comp.cofferorderer.DAL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.Common.Order;

/**
 * Created by User on 4/15/2016.
 */
public class JSONToOrderParser implements Serializable
{

    public ArrayList<Order> Parse(String response) throws JSONException
    {
        ArrayList<Order> updatedOrders=new  ArrayList<Order>();

        JSONArray jsonOrders = new JSONArray(response);

        for(int i=0;i<jsonOrders.length();i++)
        {
            JSONObject JOrder=jsonOrders.getJSONObject(i);
            int id=JOrder.getInt("id");
            String coffeeType=JOrder.getString("CoffeeType");
            String sugar=JOrder.getString("Sugar");
            String freeText=JOrder.getString("FreeText");
            updatedOrders.add(new Order(id,coffeeType,sugar,freeText));
        }

        return updatedOrders;
    }

}
