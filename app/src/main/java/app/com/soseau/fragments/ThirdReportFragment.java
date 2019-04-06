package app.com.soseau.fragments;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.Util;
import app.AppController;
import app.com.soseau.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jazz on 05/01/2017.
 */
public class ThirdReportFragment extends SlideFragment {

    @BindView(R.id.mTextViewLocalite)
    EditText mTextViewLocalite;

    @BindView(R.id.mTextViewPresseLink)
    EditText mTextViewPresseLink;

    @BindView(R.id.mTextViewVideoLink)
    EditText mTextViewVideoLink;

    @BindView(R.id.mImageButtonLocalite)
    ImageButton mImageButtonLocalite;

    @BindView(R.id.info_sup)
    TextView info_sup;

    private String lat;
    private String lng;


    public static ThirdReportFragment newInstance() {
        return new ThirdReportFragment();
    }

    private static ThirdReportFragment mInstance;

    public static ThirdReportFragment get()
    {
        return mInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.report_form_3, container, false);
        ButterKnife.bind(this, root);

        mTextViewPresseLink.setText("http://news.com/that-cat-was-so-badass");
        mTextViewVideoLink.setText("http://www.youtube.com/watch?v=08pVpBq706k");
        /*

        if(BuildConfig.DEBUG)
        {
            lat = "48.856638";
            lng = "2.352241";

            mTextViewPresseLink.setText("http://news.com/that-cat-was-so-badass");
            mTextViewVideoLink.setText("http://www.youtube.com/watch?v=08pVpBq706k");
            mTextViewLocalite.setText("Gabes");
        }
*/
        return root;
    }

    @OnClick(R.id.mImageButtonLocalite)
    public void setmImageButtonLocalite() {
        if (mTextViewLocalite.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(getView(), getString(R.string.msg_erreur_gps), Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else
        {
            makeJsonObjReq("https://maps.googleapis.com/maps/api/geocode/json?address=" + mTextViewLocalite.getText().toString() + "&key=" + getString(R.string.google_geo_api_key));

        }
    }

    @Override
    public boolean canGoForward() {

        if(mTextViewVideoLink != null && !Util.getContent(mTextViewVideoLink).isEmpty() && !isValidYoutubeUrl(Util.getContent(mTextViewVideoLink)))
        {
            Snackbar snackbar = Snackbar
                    .make(getView(), "le lien de la vidéo est invalide", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();

            return false;
        }
        else if(mTextViewPresseLink != null && !Util.getContent(mTextViewPresseLink).isEmpty() && !URLUtil.isValidUrl(Util.getContent(mTextViewPresseLink)))
        {
            Snackbar snackbar = Snackbar
                    .make(getView(), "le lien de sources de presse est invalide", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return false;
        }
        else if(mTextViewLocalite != null && !Util.getContent(mTextViewLocalite).isEmpty() &&
                lat != null && lng != null && !lng.isEmpty() && !lng.isEmpty())
        {
            return true;
        }

        else
        {
            return false;
        }
    }
    /**
     * Making json object request
     * */
    public void makeJsonObjReq(String ws_url) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                ws_url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            //Log.i("response",response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat"));
                            lat = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat");
                            lng = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lng");

                            info_sup.setText(lat+", "+lng);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                //Log.i("response",error.getMessage());

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, "geolocation");

    }

    public EditText getmTextViewLocalite() {
        return mTextViewLocalite;
    }

    public EditText getmTextViewPresseLink() {
        return mTextViewPresseLink;
    }

    public EditText getmTextViewVideoLink() {
        return mTextViewVideoLink;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    private boolean isValidYoutubeUrl(String url) {

        if (url == null) {
            return false;
        }
        if (URLUtil.isValidUrl(url)) {
            // Check host of url if youtube exists
            Uri uri = Uri.parse(url);
            if ("www.youtube.com".equals(uri.getHost())) {
                return true;
            }
            // Other way You can check into url also like
            //if (url.startsWith("https://www.youtube.com/")) {
            //return true;
            //}
        }
        // In other any case
        return false;
    }
}
