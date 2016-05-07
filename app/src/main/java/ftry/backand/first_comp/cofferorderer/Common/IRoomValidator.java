package ftry.backand.first_comp.cofferorderer.Common;

import android.content.Context;
import android.content.Intent;

/**
 * Created by User on 4/30/2016.
 */
public interface IRoomValidator
{

    public void ValidateRoom(String roomName, String Password,Intent startingIntent,Context context);
}
