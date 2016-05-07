package ftry.backand.first_comp.cofferorderer.BL;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ftry.backand.first_comp.cofferorderer.BL.Rutiner.RutineUpdater;
import ftry.backand.first_comp.cofferorderer.Common.DeleterOnClick;
import ftry.backand.first_comp.cofferorderer.Common.EditerOnClick;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickDeleter;
import ftry.backand.first_comp.cofferorderer.Common.IOnClickEditer;
import ftry.backand.first_comp.cofferorderer.Common.IRoomCreator;
import ftry.backand.first_comp.cofferorderer.Common.IRoomValidator;
import ftry.backand.first_comp.cofferorderer.Common.IValuePoster;
import ftry.backand.first_comp.cofferorderer.Common.IValuePutter;
import ftry.backand.first_comp.cofferorderer.Common.Order;
import ftry.backand.first_comp.cofferorderer.Common.Syncronize.SyncInitiater;
import ftry.backand.first_comp.cofferorderer.DAL.HttpRequestor;

/**
 * Created by User on 4/17/2016.
 */
public class LogicActivator implements Serializable
{
    private HttpRequestor requestor;
    private static RutineUpdater updator;
    private DeleterOnClick deleter;
    private EditerOnClick editer;


    public LogicActivator(Context context,SyncInitiater intiator,ArrayList<Order> data)
    {
        requestor=new HttpRequestor(context,intiator,data);
        updator=new RutineUpdater();
        updator.StartRutine(requestor,context);
        deleter=new DeleterOnClick(requestor,intiator,data);
        editer=new EditerOnClick(requestor,intiator,data);
    }


    public IOnClickDeleter IntalizeDeleter()
    {
        return deleter;
    }

    public IValuePoster IntalizePoster()
    {
        return requestor;
    }

    public IOnClickEditer IntalizeEditter()
    {
        return editer;

    }

    public IRoomCreator IntalizeRoomCreator()
    {
        return requestor;

    }

    public IRoomValidator InatlizeValidator()
    {
        return requestor;
    }




}
