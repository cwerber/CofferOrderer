package ftry.backand.first_comp.cofferorderer.Common;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncInitiater;
import ftry.backand.first_comp.cofferorderer.R;

/**
 * Created by User on 4/22/2016.
 */
public class EditerOnClick implements IOnClickEditer,Serializable {
    private IValuePutter mPutter;
    private SyncInitiater mInitiater;
    private ArrayList<Order> mCurrentOrders;
    private Dialog edit_dialog;
    private EditText etCoffeeTypeEdit;
    private EditText etSugarEdit;
    private EditText etFreeTextEdit;
    private Button btnEdit;
    private int update_id;

    public EditerOnClick(IValuePutter putter,SyncInitiater initator, ArrayList<Order> currentOrders)
    {
        mPutter=putter;
        mInitiater=initator;
        mCurrentOrders=currentOrders;
    }
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnEdit:
            {
                edit_dialog=new Dialog(v.getContext());
                edit_dialog.setContentView(R.layout.edit_dialog);
                edit_dialog.setTitle("Update Your Order");
                edit_dialog.show();

                etCoffeeTypeEdit=(EditText)edit_dialog.findViewById(R.id.etCoffeeTypeEdit);
                etSugarEdit=(EditText)edit_dialog.findViewById(R.id.etSugarEdit);
                etFreeTextEdit=(EditText)edit_dialog.findViewById(R.id.etFreeTextEdit);
                btnEdit=(Button)edit_dialog.findViewById(R.id.btnEditDialog);
                Button b=(Button)v;
                update_id=Integer.parseInt(b.getHint().toString());
                Order edittedOrder=mCurrentOrders.get(mCurrentOrders.indexOf(new Order(update_id, "", "", "")));

                etCoffeeTypeEdit.setText(edittedOrder.getCoffeType());
                etSugarEdit.setText(edittedOrder.getSugar());
                etFreeTextEdit.setText(edittedOrder.getFreeText());
                btnEdit.setOnClickListener(this);



                break;
            }
            case R.id.btnEditDialog:
            {
                String coffeeType=etCoffeeTypeEdit.getText().toString();
                String sugar=etSugarEdit.getText().toString();
                String freeText=etFreeTextEdit.getText().toString();
                Order edittedOrder=mCurrentOrders.get(mCurrentOrders.indexOf(new Order(update_id, "", "", "")));
                edittedOrder.setCoffeType(coffeeType);
                edittedOrder.setSugar(sugar);
                edittedOrder.setFreeText(freeText);

                mPutter.Put(new Order(update_id, coffeeType, sugar,freeText));
                edit_dialog.hide();
                break;
            }
        }




    }
}
