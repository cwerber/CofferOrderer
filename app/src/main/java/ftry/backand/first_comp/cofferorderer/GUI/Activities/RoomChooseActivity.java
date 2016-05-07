package ftry.backand.first_comp.cofferorderer.GUI.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ftry.backand.first_comp.cofferorderer.BL.LogicActivator;
import ftry.backand.first_comp.cofferorderer.Common.IHiddable;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickChooser;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickDeleter;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickEditer;
import ftry.backand.first_comp.cofferorderer.Common.IRoomCreator;
import ftry.backand.first_comp.cofferorderer.Common.RoomChooserOnClick;
import ftry.backand.first_comp.cofferorderer.GUI.DialogHidder;
import ftry.backand.first_comp.cofferorderer.R;

/**
 * Created by User on 4/24/2016.
 */
public class RoomChooseActivity extends Activity implements View.OnClickListener
{

        Button btnHave;
        Button btnCreate;
        Button btnCloseDialog;
        EditText etRoomName;
        EditText etRoomPassword;
        Dialog create_room_dialog;
        Dialog find_room_dialog;
        IRoomCreator roomCreator;
        private IOnClickChooser mRoomChooser;
        private LogicActivator blInstance;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.choose_room_layout);

            blInstance=(LogicActivator)getIntent().getSerializableExtra("blInstance");
            btnHave = (Button) findViewById(R.id.btnEnterHaveRoom);
            btnHave.setOnClickListener(this);

            btnCreate = (Button) findViewById(R.id.btnEnterCreateRoom);
            btnCreate.setOnClickListener(this);
            roomCreator=blInstance.IntalizeRoomCreator();

        }


    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnEnterHaveRoom: {

                    find_room_dialog=new Dialog(this);
                    find_room_dialog.setContentView(R.layout.find_room_layout);
                    find_room_dialog.setTitle("Find my room");
                    find_room_dialog.show();
                    Button ChooseButton=(Button)find_room_dialog.findViewById(R.id.btnFindRoom);
                    EditText etName=(EditText)find_room_dialog.findViewById(R.id.etFinderRoomName);
                    EditText etPassword=(EditText)find_room_dialog.findViewById(R.id.etFinderPassword);
                    IHiddable hidable=new DialogHidder(find_room_dialog);
                    mRoomChooser = new RoomChooserOnClick(etName,etPassword,blInstance.InatlizeValidator(),hidable,this.getIntent(),this);

                    ChooseButton.setOnClickListener(mRoomChooser);
                    break;
                }
                case R.id.btnEnterCreateRoom: {
                    create_room_dialog=new Dialog(this);
                    create_room_dialog.setContentView(R.layout.create_room_dialog);
                    create_room_dialog.setTitle("Creating new room");
                    create_room_dialog.show();
                    btnCloseDialog=(Button)create_room_dialog.findViewById(R.id.btnCreateRoom);
                    btnCloseDialog.setOnClickListener(this);
                    etRoomName=(EditText)create_room_dialog.findViewById(R.id.etCreateRoomName);
                    etRoomPassword=(EditText)create_room_dialog.findViewById(R.id.etCreatePassword);
                    break;
                }
                case R.id.btnCreateRoom:
                {
                    String roomName=etRoomName.getText().toString();
                    String roomPassword=etRoomPassword.getText().toString();

                    create_room_dialog.hide();
                    roomCreator.CreateRoom(roomName,roomPassword,getIntent(),getApplicationContext());
                    break;
                }

            }

        }

}
