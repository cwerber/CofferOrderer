package ftry.backand.first_comp.cofferorderer.GUI.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

import ftry.backand.first_comp.cofferorderer.BL.LogicActivator;
import ftry.backand.first_comp.cofferorderer.Common.IHiddable;
import ftry.backand.first_comp.cofferorderer.Common.Order;
import ftry.backand.first_comp.cofferorderer.Common.RoomChooserOnClick;
import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncInitiater;
import ftry.backand.first_comp.cofferorderer.GUI.DialogHidder;
import ftry.backand.first_comp.cofferorderer.R;


public class MainActivity extends Activity implements View.OnClickListener,Serializable {

    private Button btnMaker;
    private Button btnWaitter;
    private LogicActivator blInstance;
    private SyncInitiater intiator;
    private ArrayList<Order> data;
    Dialog find_room_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        data=new ArrayList<Order>();
        intiator=new SyncInitiater();

        blInstance=new LogicActivator(this, intiator, data);


        btnMaker = (Button) findViewById(R.id.btnEnterMaker);
        btnMaker.setOnClickListener(this);

        btnWaitter = (Button) findViewById(R.id.btnEnterAsAWaiter);
        btnWaitter.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnterMaker: {
                Intent intent = new Intent(this, RoomChooseActivity.class);
                intent.putExtra("blInstance", blInstance);
                intent.putExtra("intiator",intiator);
                intent.putExtra("data",data);
                startActivity(intent);
                break;
            }
            case R.id.btnEnterAsAWaiter: {
                Intent intent = new Intent(this, CoffeMakerActivity.class);

                intent.putExtra("blInstance",blInstance);
                intent.putExtra("intiator",intiator);
                intent.putExtra("data",data);




                find_room_dialog=new Dialog(this);
                find_room_dialog.setContentView(R.layout.find_room_layout);
                find_room_dialog.setTitle("Find my room");
                find_room_dialog.show();
                Button ChooseButton=(Button)find_room_dialog.findViewById(R.id.btnFindRoom);
                EditText etName=(EditText)find_room_dialog.findViewById(R.id.etFinderRoomName);
                EditText etPassword=(EditText)find_room_dialog.findViewById(R.id.etFinderPassword);
                IHiddable hidable=new DialogHidder(find_room_dialog);
                RoomChooserOnClick mRoomChooser = new RoomChooserOnClick(etName,etPassword,blInstance.InatlizeValidator(),hidable,intent,this);

                ChooseButton.setOnClickListener(mRoomChooser);


                break;
            }

        }

    }


}
