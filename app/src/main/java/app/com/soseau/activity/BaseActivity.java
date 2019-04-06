package app.com.soseau.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;


import org.json.JSONObject;
import app.AppController;

/**
 * Created by jazz on 24/12/2016.
 */
public class BaseActivity extends AppCompatActivity
{

    private String TAG = BaseActivity.class.getSimpleName();

    public AppController application;

    @Override
    protected void onCreate(Bundle arg0)
    {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        application = AppController.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    public String getSharedParam(String key) {

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        return appSharedPrefs.getString(key, "");
    }


    public void saveParam(String name, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor e = sp.edit();
        e.putString(name, value);
        e.commit();
    }

    /**
     * Making json object request
     * */
    public void makeJsonObjReq(String ws_url, final String session, String tag_json_obj,String wait_msg) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                ws_url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        onData(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                onDataFailed();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    public void onData(JSONObject jsonObject)
    {

    }

    public void onDataFailed()
    {

    }


    public  String noHTMLString(String description)
    {

        String noHTMLString = description.replaceAll("\\<.*?>", "")
                .replaceAll("\\n", "").replaceAll("\\r", "")
                .replaceAll("&nbsp;", "");
        return noHTMLString;
    }
}
