package ftry.backand.first_comp.cofferorderer.BL.Rutiner;

import android.content.Context;

import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ftry.backand.first_comp.cofferorderer.DAL.HttpRequestor;

/**
 * Created by User on 4/15/2016.
 */
public class RutineUpdater implements Serializable
{
    private RutineRunner mRutinner;
    public void StartRutine(HttpRequestor requestor,Context context)
    {
        mRutinner=new RutineRunner(requestor,context);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(mRutinner, 0, 3, TimeUnit.SECONDS);

    }




}
