package ftry.backand.first_comp.cofferorderer.DAL.ErrorListeners;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.Serializable;

/**
 * Created by User on 4/8/2016.
 */
public class ErrorListener implements Response.ErrorListener,Serializable
{

    private static Context mContext;

    public ErrorListener(Context context)
    {
        mContext=context;

    }

    public void onErrorResponse(VolleyError error) {
        try{
            Toast.makeText(mContext,error.getMessage().toString(),Toast.LENGTH_LONG);
        }
        catch(Exception e)
        {
            Toast.makeText(mContext,"No available Error To show",Toast.LENGTH_LONG);
        }


    }
}
