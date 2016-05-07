package ftry.backand.first_comp.cofferorderer.GUI.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ftry.backand.first_comp.cofferorderer.BL.LogicActivator;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickDeleter;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickEditer;
import ftry.backand.first_comp.cofferorderer.Common.IValuePoster;
import ftry.backand.first_comp.cofferorderer.Common.Order;
import ftry.backand.first_comp.cofferorderer.GUI.ScreenChangers.ListSyncronizer;
import ftry.backand.first_comp.cofferorderer.GUI.ScreenChangers.OrderArrayAdapter;
import ftry.backand.first_comp.cofferorderer.R;
import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncInitiater;

/**
 * Created by User on 4/15/2016.
 */
public class CoffeMakerActivity extends AppCompatActivity implements  View.OnClickListener {

    private IValuePoster poster;
    private Button btnOrder;
    private Button addButton;
    private EditText etCoffeeType;
    private EditText etSugar;
    private EditText etFreeTest;
    private ListView list;
    private OrderArrayAdapter adapter;
    private Dialog order_dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffe_maker_layout);

        addButton=(Button)findViewById(R.id.addBtn);
        addButton.setOnClickListener(this);


        LogicActivator blInstance=(LogicActivator)getIntent().getSerializableExtra("blInstance");
        SyncInitiater intiator=(SyncInitiater)getIntent().getSerializableExtra("intiator");
        ArrayList<Order> data=(ArrayList<Order>)getIntent().getSerializableExtra("data");
        IOnClickDeleter deleter=blInstance.IntalizeDeleter();
        IOnClickEditer editter=blInstance.IntalizeEditter();
        poster=blInstance.IntalizePoster();

        adapter=new OrderArrayAdapter(this,R.layout.order_item_maker,data,deleter,editter);

        ListSyncronizer syncronizer=new ListSyncronizer(data,adapter);
        intiator.addListener(syncronizer);

        list=(ListView)findViewById(R.id.listMaker);
        list.setAdapter(adapter);



    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.addBtn:
            {

                order_dialog=new Dialog(this);
                order_dialog.setContentView(R.layout.order_dialog);
                order_dialog.setTitle("Enter The Order");
                order_dialog.show();
                btnOrder=(Button)order_dialog.findViewById(R.id.btnOrder);
                btnOrder.setOnClickListener(this);
                etCoffeeType=(EditText)order_dialog.findViewById(R.id.etCoffeeType);
                etSugar=(EditText)order_dialog.findViewById(R.id.etSugar);
                etFreeTest=(EditText)order_dialog.findViewById(R.id.etFreeText);


                break;
            }
            case R.id.btnOrder:
            {
                int id=PreferenceManager.getDefaultSharedPreferences(this).getInt("id",-1);
                String coffeeType=etCoffeeType.getText().toString();
                String sugar=etSugar.getText().toString();
                String freeText=etFreeTest.getText().toString();
                order_dialog.hide();
                poster.Post(coffeeType,sugar,freeText,id);

            }
        }
    }
}
