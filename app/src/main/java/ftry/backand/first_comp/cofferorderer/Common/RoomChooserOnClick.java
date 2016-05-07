package ftry.backand.first_comp.cofferorderer.Common;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by User on 4/25/2016.
 */
public class RoomChooserOnClick implements IOnClickChooser,Serializable {

    private EditText mETName;
    private EditText mETPassword;
    private IRoomValidator mValidator;
    private IHiddable mHideAtFinish;
    private Intent mIntent;
    private Context mContext;

    public RoomChooserOnClick(EditText etName,EditText etPassword,IRoomValidator validator,IHiddable hideAtFinish,Intent startingIntent,Context context)
    {
        mETName=etName;
        mETPassword=etPassword;
        mValidator=validator;
        mHideAtFinish=hideAtFinish;
        mIntent=startingIntent;
        mContext=context;


    }


    @Override
    public void onClick(View v) {
        mValidator.ValidateRoom(mETName.getText().toString(),mETPassword.getText().toString(),mIntent,mContext);
    }
}
