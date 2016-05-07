package ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners;

import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.Common.Order;
import ftry.backand.first_comp.cofferorderer.DAL.JSONToOrderParser;
import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncInitiater;

/**
 * Created by User on 4/8/2016.
 */
public class SuccessListener implements Response.Listener<String>,Serializable
{

    private SyncInitiater intiate;
    private ArrayList<Order> mCurrentOrders;
    private JSONToOrderParser mParser;

    public SuccessListener(SyncInitiater intiate, ArrayList<Order> currentOrders)
    {
        this.intiate=intiate;
        mCurrentOrders=currentOrders;
        mParser=new JSONToOrderParser();

    }



    public void onResponse(String response)
    {

        try {
            copyValues(mCurrentOrders,mParser.Parse(response));

            }
              catch (JSONException e)
        {
            e.printStackTrace();
        }
        intiate.Sync();

    }
    private void copyValues( ArrayList<Order> oldOrders, ArrayList<Order> newOrders)
    {
        oldOrders.clear();
        for (Order order:newOrders)
        {
            oldOrders.add(order);
        }
    }
}
