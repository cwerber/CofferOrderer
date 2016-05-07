package ftry.backand.first_comp.cofferorderer.Common;

import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncInitiater;

/**
 * Created by User on 4/15/2016.
 */
public class DeleterOnClick implements View.OnClickListener,IOnClickDeleter,Serializable {

    private IValueDeleter mDeleter;
    private SyncInitiater mInitiater;
    private ArrayList<Order> mCurrentOrders;

    public DeleterOnClick(IValueDeleter deleter,SyncInitiater initator, ArrayList<Order> currentOrders)
    {
        mDeleter=deleter;
        mInitiater=initator;
        mCurrentOrders=currentOrders;
    }

    @Override
    public void onClick(View v) {
        Button b=(Button)v;
        int id=Integer.parseInt(b.getHint().toString());
        mCurrentOrders.remove(new Order(id,"","",""));


        mDeleter.Delete(id);
        
    }
}
