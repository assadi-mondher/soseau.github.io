package app.com.soseau.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.request.StringRequest;

import java.io.IOException;
import java.util.List;

import Utils.Config;
import app.com.soseau.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import views.ClearableEditText;

/**
 * Created by jazz on 14/03/2017.
 */

public class AuthentificationActivity extends AppCompatActivity {

    @BindView(R.id.email)
    ClearableEditText email;

    @BindView(R.id.password)
    ClearableEditText password;
    StringRequest stringRequest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_authentification);

        overridePendingTransition(R.anim.slide_in_top, 0);
        ButterKnife.bind(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*
        String data = "username=assadi85@gmail.com&password=password&action=signin&remember=1";
        String login = WebService.getInstance().login( data);
        Log.i("uuuuuuuuuuuuuu",login);
        */
        MultipartUtility multipart = null;
        try {
            multipart = new MultipartUtility(Config.WS_LOGIN, "utf-8");
            // In your case you are not adding form data so ignore this
                /*This is to add parameter values */

                multipart.addFormField("username","assadi85@gmail");
                multipart.addFormField("action","signin");
                multipart.addFormField("password","password");


            List<String> response = multipart.finish();
            Log.i("response", "SERVER REPLIED:");
            for (String line : response) {
                Log.i("response","Upload Files Response:::" + line);
    }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    @OnClick(R.id.authentification_btn)
    void setAuthentificationBtn() {
        /*
        http://soseau.net/login
paramètres
action : signin
username : assadi85@gmail.com
password :  password
remember : 1
         */


    }

}
