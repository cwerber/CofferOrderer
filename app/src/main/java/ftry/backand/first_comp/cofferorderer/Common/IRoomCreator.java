package ftry.backand.first_comp.cofferorderer.Common;

import android.content.Context;
import android.content.Intent;

/**
 * Created by User on 4/24/2016.
 */
public interface IRoomCreator {
    public void CreateRoom(String roomName,String Password,Intent StartingIntent,Context context);
}
