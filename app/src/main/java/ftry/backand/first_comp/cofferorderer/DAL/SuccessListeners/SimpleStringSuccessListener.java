package ftry.backand.first_comp.cofferorderer.DAL.SuccessListeners;

import com.android.volley.Response;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by User on 4/16/2016.
 */
public class SimpleStringSuccessListener  implements Response.Listener<String>,Serializable {
    @Override
    public void onResponse(String response) {
        int i=5;
    }
}
