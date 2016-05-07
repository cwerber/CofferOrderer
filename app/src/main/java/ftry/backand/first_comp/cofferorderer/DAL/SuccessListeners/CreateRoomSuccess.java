package ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import ftry.backand.first_comp.cofferorderer.GUI.Activities.CoffeMakerActivity;

/**
 * Created by User on 4/25/2016.
 */
public class CreateRoomSuccess  implements Response.Listener<JSONObject>,Serializable {

    private Context mContext;
    private Intent mStartingIntent;

    public CreateRoomSuccess(Context context,Intent startingIntent)
    {
        mContext=context;
        mStartingIntent=startingIntent;
    }



    public void onResponse(JSONObject response)
    {
        int id = 0;
        try {
            id  = Integer.parseInt((String) ((JSONObject) response.get("__metadata")).get("id"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor= PreferenceManager.getDefaultSharedPreferences(mContext).edit();
        editor.putInt("id",id);
        editor.commit();


        Intent intent = new Intent(mContext, CoffeMakerActivity.class);
        intent.putExtras(mStartingIntent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);



    }
}
