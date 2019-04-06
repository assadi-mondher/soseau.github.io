package app.com.soseau.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joaquimley.faboptions.FabOptions;
import com.nguyenhoanglam.progresslayout.ProgressLayout;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.Config;
import Utils.Util;
import app.com.soseau.R;
import app.com.soseau.model.Categories;
import app.com.soseau.model.Incident;
import app.com.soseau.model.Incidents;
import app.com.soseau.model.Media;
import app.com.soseau.model.MyPojo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener
{
    private List<Integer> skipIds;
    @BindView(R.id.progressLayout)
    ProgressLayout progressLayout;

    private String[] categoriesIds;
    private List<Incidents> incidentsList;

    @BindView(R.id.mActionButtonAll)
    FloatingActionButton mActionButtonAll;

    @BindView(R.id.mActionButtonAssainissement)
    FloatingActionButton mActionButtonAssainissement;


    @BindView(R.id.mActionButtonCoupure)
    FloatingActionButton mActionButtonCoupure;

    @BindView(R.id.mActionButtonDistributionNonEquitable)
    FloatingActionButton mActionButtonDistributionNonEquitable;

    @BindView(R.id.mActionButtonFuiteEau)
    FloatingActionButton mActionButtonFuiteEau;
    
    @BindView(R.id.mActionButtonMouvementDeProtestation)
    FloatingActionButton mActionButtonMouvementDeProtestation;
    
    @BindView(R.id.mActionButtonPasAccesHabitant)
    FloatingActionButton mActionButtonPasAccesHabitant;

    @BindView(R.id.mActionButtonPollution)
    FloatingActionButton mActionButtonPollution;

    @BindView(R.id.mActionButtonPotabilite)
    FloatingActionButton mActionButtonPotabilite;


    @BindView(R.id.mActionButtonSurexploiatation)
    FloatingActionButton mActionButtonSurexploiatation;



    @BindView(R.id.mActionButtCorruption)
    FloatingActionButton mActionButtCorruption;
    
    
    @BindView(R.id.menu)
    FloatingActionMenu menu;
    
    GoogleMap googleMap;

    private String selected_catID;
    private Media[] media;

    final private int REQUEST_CODE_ASK_PERMISSIONS_CALL = 123;
    final private int REQUEST_CODE_ASK_PERMISSIONS_SMS = 124;
    final private int REQUEST_CODE_ASK_PERMISSIONS_GPS = 125;

    private LatLngBounds.Builder builder;
    private CameraUpdate cu;

   
    private int  total_corruption = 0;
    private int total_pas_acces_hibitant = 0;
    private int total_assainissement = 0;
    private int total_autresCategories = 0;
    private int total_coupure = 0;
    private int total_distributionNonEquitable = 0;
    private int total_fuiteDistribution = 0;
    private int total_fuiteEau = 0;
    private int total_mouvementDeProtestation = 0;
    private int total_pollution = 0;
    private int total_potabilite = 0;
    private int total_surexploiatation = 0;
    private int total_sourceEau = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        skipIds = new ArrayList<>();
        skipIds.add(R.id.container);
        categoriesIds = getResources().getStringArray(R.array.categories_ids);

        FabOptions fabOptions = (FabOptions) findViewById(R.id.fab_options);
        fabOptions.setOnClickListener(this);

        // Getting reference to the SupportMapFragment of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        // Setting a custom info window adapter for the google map
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

                // Getting the position from the marker
                LatLng latLng = arg0.getPosition();

                // Getting reference to the TextView to set latitude
                TextView mTextViewTitle = (TextView) v.findViewById(R.id.title);
                mTextViewTitle.setText(arg0.getTitle());

                TextView mTextViewLocation = (TextView) v.findViewById(R.id.location);
                mTextViewLocation.setText(arg0.getSnippet());

                // Returning the view containing InfoWindow contents
                return v;

            }
        });

        /*
        // Adding and showing marker while touching the GoogleMap
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {
                // Clears any existing markers from the GoogleMap
                googleMap.clear();

                // Creating an instance of MarkerOptions to set position
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting position on the MarkerOptions
                markerOptions.position(arg0);

                // Animating to the currently touched position
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));

                // Adding marker on the GoogleMap
                Marker marker = googleMap.addMarker(markerOptions);

                // Showing InfoWindow on the GoogleMap
                marker.showInfoWindow();

            }
        });
        */

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            Incident incident = getIncident(selected_catID, String.valueOf(marker.getPosition().latitude), String.valueOf(marker.getPosition().longitude));
            Bundle extrasList = new Bundle();
            extrasList.putSerializable("item",incident);
            extrasList.putSerializable("media",media);
            //start detail activity
            Util.intentActivity(MainActivity.this,MainActivity.this,DetailIncidentActivity.class,extrasList);

        }
    });
        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);

        //zoomToCurrentLocation();
        getData();
    }

    /*
    private void zoomToCurrentLocation() {

        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSIONS_GPS);
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));

            // Zoom in the Google Map
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }

    */


    private void getData()
    {
        // Show progress layout, hide all main views
//        progressLayout.showLoading(skipIds);
        makeJsonObjReq(Config.WS_INCIDENTS, null,"items_json_obj","");

    }

    @Override
    public void onData(JSONObject jsonObject) {
        super.onData(jsonObject);

        /*
        Gson gson = new Gson();
        try {
            JSONArray array = jsonObject.getJSONObject("payload").getJSONArray("incidents");

            for(int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);
                JSONArray media = obj.getJSONArray("media");

                for(int j = 0; j < media.length(); j++){

                    JSONObject mediaItem = media.getJSONObject(j);
                    //Media countriesArray = gson.fromJson(mediaItem.toString(), Media.class);

                    Log.i("mediaK",mediaItem.getString("type") +"::"+mediaItem.getString("id"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
//        progressLayout.showContent();
        ObjectMapper objectMapper = new ObjectMapper();

        MyPojo resultList = null;
        try
        {
            resultList = objectMapper.readValue(jsonObject.toString(), MyPojo.class);
            incidentsList = Arrays.asList(resultList.getPayload().getIncidents());

            for (Incidents item : incidentsList) {
                for (Categories cat : item.getCategories()) {
                    Log.i("categories",item.getIncident().getIncidenttitle()+"::"+cat.getCategory().getTitle());
                        switch (cat.getCategory().getId()) {
                            case "12":
                                total_assainissement++;
                                break;
                            case "14":
                                total_autresCategories++;
                                break;
                            case "7":
                                total_corruption++;
                                break;
                            case "1":
                                total_coupure++;
                                break;
                            case "9":
                                total_distributionNonEquitable++;
                                break;
                            case "17":
                                total_fuiteDistribution++;
                                break;
                            case "18":
                                total_mouvementDeProtestation++;
                                break;
                            case "4":
                                total_pas_acces_hibitant++;
                                break;
                            case "6":
                                total_pollution++;
                                break;
                            case "8":
                                total_potabilite++;
                                break;
                            case "16":
                                total_sourceEau++;
                                break;
                            case "15":
                                total_surexploiatation++;
                                break;
                            case "13":
                                total_fuiteEau++;
                                break;

                        }


                }
            }

            mActionButtonAll.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(incidentsList.size()),2,"0"), 60, Color.WHITE));
            mActionButtCorruption.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_corruption),2,"0"), 60, Color.WHITE));
            mActionButtonPasAccesHabitant.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_pas_acces_hibitant),2,"0"), 60, Color.WHITE));
            mActionButtonAssainissement.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_assainissement),2,"0"), 60, Color.WHITE));
            //mActionButtonAutresCategories.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_autresCategories),2,"0"),60, Color.WHITE));
            mActionButtonCoupure.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_coupure),2,"0"), 60, Color.WHITE));

            mActionButtonDistributionNonEquitable.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_distributionNonEquitable),2,"0"), 60, Color.WHITE));
            //mActionButtonFuiteDistribution.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_fuiteDistribution),2,"0"), 60, Color.WHITE));
            mActionButtonFuiteEau.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_fuiteEau),2,"0"), 60, Color.WHITE));
            mActionButtonMouvementDeProtestation.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_mouvementDeProtestation),2,"0"), 60, Color.WHITE));
            mActionButtonPollution.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_pollution),2,"0"),60, Color.WHITE));
            mActionButtonPotabilite.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_potabilite),2,"0"), 60, Color.WHITE));

            mActionButtonSurexploiatation.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_surexploiatation),2,"0"),60, Color.WHITE));
            //mActionButtonSourceEau.setImageBitmap(textAsBitmap(StringUtils.leftPad(String.valueOf(total_sourceEau),2,"0"), 60, Color.WHITE));


            drawMarker(categoriesIds[0]);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onDataFailed()
    {
        super.onDataFailed();
        /*
        progressLayout.showError(ContextCompat.getDrawable(this, R.drawable.ic_no_connection), getString(R.string.msg_error_cnx), getString(R.string.retry), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        },skipIds);
*/
    }

    @OnClick(R.id.mActionButtonAll)
    public void setmActionButtonSurpaturage()
    {
        menu.close(true);
        drawMarker(categoriesIds[0]);
    }

    @OnClick(R.id.mActionButtonCoupure)
    public void setmActionButtonCoupure()
    {
        menu.close(true);
        drawMarker(categoriesIds[1]);
    }

    @OnClick(R.id.mActionButtonPasAccesHabitant)
    public void setmActionButtonPasAccesHabitant()
    {
        menu.close(true);
        drawMarker(categoriesIds[2]);
    }

    @OnClick(R.id.mActionButtonPollution)
    public void setmActionButtonPollution()
    {
        menu.close(true);
        drawMarker(categoriesIds[3]);
    }
    @OnClick(R.id.mActionButtCorruption)
    public void setmActionButtCorruption()
    {
        menu.close(true);
        drawMarker(categoriesIds[4]);
    }
    @OnClick(R.id.mActionButtonPotabilite)
    public void setmActionButtonPotabilite()
    {
        menu.close(true);
        drawMarker(categoriesIds[5]);
    }
    @OnClick(R.id.mActionButtonDistributionNonEquitable)
    public void setmActionButtonDistributionNonEquitable()
    {
        menu.close(true);
        drawMarker(categoriesIds[6]);
    }
    @OnClick(R.id.mActionButtonAssainissement)
    public void setmActionButtonAssainissement()
    {
        menu.close(true);
        drawMarker(categoriesIds[7]);
    }
    @OnClick(R.id.mActionButtonSurexploiatation)
    public void setmActionButtonSurexploiatation()
    {
        menu.close(true);
        drawMarker(categoriesIds[8]);
    }
    @OnClick(R.id.mActionButtonFuiteEau)
    public void setmActionButtonFuiteEau()
    {
        menu.close(true);
        drawMarker(categoriesIds[9]);
    }



    @OnClick(R.id.mActionButtonMouvementDeProtestation)
    public void setmActionButtonMouvementDeProtestation()
    {
        menu.close(true);
        drawMarker(categoriesIds[13]);
    }


    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    private void drawMarker(String categoryId)
    {
        selected_catID = categoryId;
        googleMap.clear();
        int numbreMarker = 0;

        builder = new LatLngBounds.Builder();
        for (Incidents item : incidentsList) {
            for (Categories cat : item.getCategories()) {
                if (cat.getCategory().getId().equals(categoryId) || categoryId.equals("0")) {
                    MarkerOptions markerOptions = new MarkerOptions();

                    markerOptions.position(new LatLng(Double.parseDouble(item.getIncident().getLocationlatitude()), Double.parseDouble(item.getIncident().getLocationlongitude()))).title(item.getIncident().getIncidenttitle()).snippet(item.getIncident().getLocationname());

                    // set icon marker using the id of category
                    int idMarkerRessource = getIconMarker(Integer.parseInt(cat.getCategory().getId()));
                    if( idMarkerRessource != 0)
                    {
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(idMarkerRessource));
                    }

                    else
                    {
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }

                    googleMap.addMarker(markerOptions);
                    builder.include(markerOptions.getPosition());
                    numbreMarker++;
                }
            }
        }

        if(numbreMarker > 0)
        {
            int padding = 50;
            LatLngBounds bounds = builder.build();
            cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            googleMap.animateCamera(cu);
        }

    }

    private int getIconMarker(int catID)
    {
        switch (catID)
        {
            case 12:
                //total_assainissement++;
                return R.drawable.assainissement;
            case 7:
                return R.drawable.corruption;
            case 1:
                return R.drawable.coupure_eau;
            case 9:
                return R.drawable.distribution_non_equitable;
            case 4:
                return R.drawable.pas_acces_eau;
            case 6:
                return R.drawable.pollution;
            default:
                return R.drawable.icon_default;
        }

    }

    private Incident getIncident(String categoryId,String latitude, String longitude){

        Incident incident = null;
        if(!categoryId.equals("0")) {
            for (Incidents item : incidentsList) {

                for (Categories cat : item.getCategories()) {
                    if (cat.getCategory().getId().equals(categoryId) && item.getIncident().getLocationlatitude().equals(latitude) && item.getIncident().getLocationlongitude().equals(longitude)) {
                        incident = item.getIncident();
                        media = item.getMedia();
                        break;
                    }
                }


            }
        }
        else
        {
                for (Incidents item : incidentsList) {
                    for (Categories cat : item.getCategories()) {
                        if (item.getIncident().getLocationlatitude().equals(latitude) && item.getIncident().getLocationlongitude().equals(longitude)) {
                            incident = item.getIncident();
                            media = item.getMedia();
                            break;
                        }
                    }



            }
        }

        return incident;
    }

    @Override
    public void onClick(View view) {
        menu.close(true);
        switch (view.getId())
        {
            case R.id.faboptions_addReport:
                addNewReport();
                break;
            case R.id.faboptions_textsms:
                sendSms();
                break;
            case R.id.faboptions_call:
                sendCall();
                break;
            case R.id.faboptions_share:
                shareApp();
                break;
        }


    }

    private void shareApp() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
        startActivity(Intent.createChooser(share, "Partagez..."));
    }

    private void addNewReport() {
        Intent newReportIntent = new Intent(this,ReportActivity.class);
        Util.intentActivity(MainActivity.this,newReportIntent);
    }

    private void sendCall() {
        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CODE_ASK_PERMISSIONS_CALL);
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+getString(R.string.tel_contact_chajra)));
        Util.intentActivity(MainActivity.this,callIntent);
    }


    private void sendSms() {
        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.SEND_SMS},REQUEST_CODE_ASK_PERMISSIONS_SMS);
            return;
        }
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String (getString(R.string.tel_contact_chajra)));
        smsIntent.putExtra("sms_body"  , "Entrer votre message ");
        Util.intentActivity(MainActivity.this,smsIntent);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS_SMS:
              sendSms();
            break;
            case REQUEST_CODE_ASK_PERMISSIONS_CALL:
                sendCall();
                break;
            case REQUEST_CODE_ASK_PERMISSIONS_GPS:
                //zoomToCurrentLocation();
                break;
        }

    }

    /*
        Map<String, List<String>> map = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() == null)
                continue;
            builder.append(entry.getKey())
                    .append(": ");

            List<String> headerValues = entry.getValue();
            Iterator<String> it = headerValues.iterator();
            if (it.hasNext()) {
                builder.append(it.next());

                while (it.hasNext()) {
                    builder.append(", ")
                            .append(it.next());
                }
            }

            Log.i("result", builder.append("\n").toString());

        }
     */


}
