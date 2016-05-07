package ftry.backand.first_comp.cofferorderer.BL.Rutiner;

import android.content.Context;
import android.preference.PreferenceManager;

import ftry.backand.first_comp.cofferorderer.DAL.HttpRequestor;

/**
 * Created by User on 4/15/2016.
 */
public class RutineRunner implements Runnable
{
    private HttpRequestor mRequestor;
    private Context mContext;

    public RutineRunner( HttpRequestor requestor,Context context)
    {
        mRequestor=requestor;
        mContext=context;
    }



    public void run() {
        int id= PreferenceManager.getDefaultSharedPreferences(mContext).getInt("id",-1);
        mRequestor.request(id);
    }

}
