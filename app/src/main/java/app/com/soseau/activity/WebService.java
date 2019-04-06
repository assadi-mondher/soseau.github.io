package app.com.soseau.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.request.SimpleMultiPartRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Utils.Config;
import Utils.Util;
import app.AppController;
import app.com.soseau.activity.RequestManager;
import app.com.soseau.fragments.FirstReportFragment;
import app.com.soseau.fragments.FourthReportFragment;
import app.com.soseau.fragments.SecondPrimeReportFragment;
import app.com.soseau.fragments.SecondReportFragment;
import app.com.soseau.fragments.ThirdReportFragment;

import static android.icu.lang.UCharacter.LineBreak.LINE_FEED;

/**
 * Created by jazz on 30/01/2017.
 */

public class WebService {

    private RequestQueue mRequestQueue;
    private static WebService apiRequests = null;
    private String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;

    public static WebService getInstance() {
        if (apiRequests == null) {
            apiRequests = new WebService();
            return apiRequests;
        }
        return apiRequests;
    }

    public void updateProfile(Context context, FirstReportFragment mFirstReportFragment, SecondReportFragment mSecondReportFragment, SecondPrimeReportFragment mSecondPrimeReportFragment, ThirdReportFragment mThirdReportFragment, FourthReportFragment mFourthReportFragment, ArrayList<String> imageUrls, Response.Listener<String> listener, Response.ErrorListener errorListener) {

        SimpleMultiPartRequest request = new SimpleMultiPartRequest(Request.Method.POST, Config.WS_SEND_REPORT, listener, errorListener);
        mRequestQueue = RequestManager.getnstance(context);




        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        params.put("Content-Type", "image/png");
        params.put("Content-Disposition","form-data");
        request.setHeaders(params);
                String[] mHour = mFirstReportFragment.getmHour().split(":");
                request.addMultipartParam("incident_title","text", Util.getContent(mFirstReportFragment.getmTextViewTitle()));
                request.addMultipartParam("incident_description","text", Util.getContent(mFirstReportFragment.getmTextViewDescription()));
                request.addMultipartParam("incident_date","text", mFirstReportFragment.getmDate());

                request.addMultipartParam("incident_hour","text",  mHour[0]);
                request.addMultipartParam("incident_minute","text",mHour[1].split("\\s+")[0]);
                request.addMultipartParam("incident_ampm", "text", mHour[1].split("\\s+")[1].toLowerCase());

                request.addMultipartParam("incident_category","text",Util.getRadioCheckedTag(mFirstReportFragment.getmRadioGroupTypePb()));
                request.addMultipartParam("location_name","text", Util.getContent(mThirdReportFragment.getmTextViewLocalite()));
                request.addMultipartParam("task","text","report");

                request.addMultipartParam("custom_field[1]","text",Util.getRadioCheckedLabel(mSecondReportFragment.getmRadioGrouptypeEau()));//type d'eau
                request.addMultipartParam("custom_field[2]", "text",Util.getRadioCheckedLabel(mSecondReportFragment.getmRadioGroupImpacte()));//impacte

                request.addMultipartParam("custom_field[4]","text",Util.getRadioCheckedLabel(mSecondPrimeReportFragment.getmRadioGroupEtat()));//Etat
                request.addMultipartParam("custom_field[5]", "text",Util.getRadioCheckedLabel(mSecondPrimeReportFragment.getmRadioGroupUrgence()));//urgence
                request.addMultipartParam("custom_field[6]","text",Util.getRadioCheckedLabel(mSecondPrimeReportFragment.getmRadioGroupNatureProbleme()));//nature de problème

                request.addMultipartParam("incident_video[1]","text",Util.getContent(mThirdReportFragment.getmTextViewVideoLink()));//video
                request.addMultipartParam("incident_news[1]","text", Util.getContent(mThirdReportFragment.getmTextViewPresseLink()));//presse

                request.addMultipartParam("latitude","text",  mThirdReportFragment.getLat());//lat
                request.addMultipartParam("longitude","text", mThirdReportFragment.getLng());//lng
                request.addMultipartParam("person_first", "text", mFourthReportFragment.getmTextViewNomFamille().getText().toString());//name
                request.addMultipartParam("person_last", "text",  mFourthReportFragment.getmTextViewPrenom().getText().toString());// last name
                request.addMultipartParam("person_email","text",  mFourthReportFragment.getmTextViewEmail().getText().toString());//email
                request.addMultipartParam("photo_id","text",  String.valueOf(imageUrls.size()));//email

                int index = 0;
                for(String uri:imageUrls)
                {
                    request.addFile("incident_photo["+index+"]",uri);
                    index++;
                }
        request.setFixedStreamingMode(true);
        mRequestQueue.add(request);
        mRequestQueue.start();
    }

}


