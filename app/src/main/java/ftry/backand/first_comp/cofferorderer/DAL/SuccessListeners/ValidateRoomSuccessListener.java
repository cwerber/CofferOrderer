package ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import ftry.backand.first_comp.cofferorderer.GUI.Activities.CoffeMakerActivity;

/**
 * Created by User on 4/29/2016.
 */
public class ValidateRoomSuccessListener implements Response.Listener<String>,Serializable {


    private Context mContext;
    private Intent mStartingIntent;

    public ValidateRoomSuccessListener(Context context,Intent startingIntent)
    {
        mContext=context;
        mStartingIntent=startingIntent;
    }

    @Override
    public void onResponse(String responseAsString) {


        int id = 0;
        try {
            JSONArray response=new JSONArray(responseAsString);
            id  = (int) ((JSONObject) response.get(0)).get("id");
            SharedPreferences.Editor editor= PreferenceManager.getDefaultSharedPreferences(mContext).edit();
            editor.putInt("id", id);
            editor.commit();
            Intent intent = new Intent(mContext, CoffeMakerActivity.class);

            intent.putExtras(mStartingIntent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);

        } catch (JSONException e) {
            Toast.makeText(mContext,"No available Error To show",Toast.LENGTH_LONG);
        }
        catch (Exception e)
        {
            Toast.makeText(mContext.getApplicationContext(),"???",Toast.LENGTH_LONG);
        }






    }
}
