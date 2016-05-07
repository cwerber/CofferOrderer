package ftry.backand.first_comp.cofferorderer.DAL;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 4/8/2016.
 */
public class CoffeeRequest extends StringRequest implements Serializable {
    private Map<String, String> body;


    public CoffeeRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(method, url, listener, errorListener);
        body=new HashMap<>();
    }

    public CoffeeRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> mBody)
    {
        super(method, url, listener, errorListener);
        body=mBody;
    }



    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
       return body;
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> params = new HashMap<>();

        params.put("AnonymousToken","4bc7322e-c7c6-488b-80c1-efb9d2db63f8");
        params.put("Content-Type","application/json");


        return params;
    }
}
