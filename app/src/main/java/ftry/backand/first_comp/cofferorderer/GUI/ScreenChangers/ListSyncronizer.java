package ftry.backand.first_comp.cofferorderer.GUI.ScreenChangers;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.Common.Order;
import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncListener;

/**
 * Created by User on 4/15/2016.
 */
public class ListSyncronizer implements SyncListener
{

    private ArrayList<Order> CurrentOrders;
    private OrderArrayAdapter adapter;
    private ArrayList<Order> OldOrders;


    public ListSyncronizer( ArrayList<Order> currentOrders, OrderArrayAdapter adapter) {
        CurrentOrders = currentOrders;
        this.adapter = adapter;
        OldOrders= ( ArrayList<Order>) CurrentOrders.clone();
    }



    @Override
    public void SyncList() {
        ArrayList<Order> forAdd=new ArrayList<>();
        ArrayList<Order> forDelete=new ArrayList<>();

        for (Order order:CurrentOrders)
        {
         if(!OldOrders.contains(order))
         {
             forAdd.add(order);
         }
        }

        for (Order order:OldOrders)
        {
            if(!CurrentOrders.contains(order))
            {
                forDelete.remove(order);
            }
        }
        for (Order order:forAdd )
        {
            adapter.add(order);
        }
        for (Order order:forDelete )
        {
            adapter.remove(order);
        }

        OldOrders= ( ArrayList<Order>) CurrentOrders.clone();


    }
}
