package ftry.backand.first_comp.cofferorderer.Common.Syncronize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/15/2016.
 */
public class SyncInitiater implements Serializable
{
    private List<SyncListener> listeners = new ArrayList<SyncListener>();

    public void addListener(SyncListener toAdd)
    {
        listeners.add(toAdd);
    }

    public void Sync()
    {


        // Notify everybody that may be interested.
        for (SyncListener syncer : listeners)
        {
            syncer.SyncList();
        }
    }

}
