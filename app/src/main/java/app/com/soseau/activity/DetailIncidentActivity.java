package app.com.soseau.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.commons.collections4.IteratorUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Utils.Config;
import Utils.Util;
import app.com.soseau.R;
import app.com.soseau.model.CustomItem;
import app.com.soseau.model.Incident;
import app.com.soseau.model.Media;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailIncidentActivity extends BaseActivity {

    private Incident incident;

    private Media[] media;

    @BindView(R.id.mImageViewPhoto)
    ImageView mImageViewPhoto;

    @BindView(R.id.mTextViewTitle)
    TextView mTextViewTitle;

    @BindView(R.id.mTextViewDate)
    TextView mTextViewDate;

    @BindView(R.id.mTextViewLocation)
    TextView mTextViewLocation;

    @BindView(R.id.mTextViewDescription)
    TextView mTextViewDescription;

    @BindView(R.id.customFields_layout)
    LinearLayout customFields_layout;

    @BindView(R.id.photoGallery_layout)
    LinearLayout photoGallery_layout;

    @BindView(R.id.photoGallery_view)
    CardView photoGallery_view;

    @BindView(R.id.video_view)
    CardView video_view;

    private List<CustomItem> customFields;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_incident);
        ButterKnife.bind(this);

        incident = (Incident) getIntent().getSerializableExtra("item");
        mTextViewTitle.setText(incident.getIncidenttitle());
        mTextViewDate.setText(incident.getIncidentdate().split("\\s+")[0]);
        mTextViewLocation.setText(incident.getLocationname());
        mTextViewDescription.setText(noHTMLString(incident.getIncidentdescription()));

        media = (Media[]) getIntent().getSerializableExtra("media");
        for(int index = 0;index < media.length;index++) {
            Log.i("LinkID", media.length+"::"+media[index].getId());

        }
            if(media.length > 0)
        {
            for(int index = 0;index < media.length;index++)
            {
                if (media[index].getLink_url() != null && !media[index].getLink_url().isEmpty())
                {
                    Glide.with(this).load(media[index].getLink_url()).into(mImageViewPhoto);
                    break;
                }
            }

        }

        getData();

        if(media.length > 0)
        {
            showIncidentImages();
        }
    }

    private void showIncidentImages() {


        for(int index =0 ; index < media.length;index++)
        {
            if(media[index].getLink_url() != null && !media[index].getLink_url().isEmpty())
            {
                View item_image_view = getLayoutInflater().inflate(R.layout.item_photo_gallery, null);
                ImageView mImageViewPhoto = (ImageView) item_image_view.findViewById(R.id.mImageViewPhoto);
                Glide.with(this).load(media[index].getLink_url()).into(mImageViewPhoto);
                mImageViewPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle extrasList = new Bundle();
                        extrasList.putString("title",incident.getIncidenttitle());
                        extrasList.putSerializable("media",media);
                        //start detail activity
                        Util.intentActivity(DetailIncidentActivity.this,DetailIncidentActivity.this,GalleryActivity.class,extrasList);
                    }
                });
                photoGallery_layout.addView(item_image_view);
            }
        }

        if(photoGallery_layout.getChildCount() == 0)
        {
            photoGallery_view.setVisibility(View.GONE);
        }


    }

    private void getData()
    {
        // Show progress layout, hide all main views
//        progressLayout.showLoading(skipIds);
        makeJsonObjReq(Config.WS_INCIDENT_BY_ID+"&by=incidentid&id="+incident.getIncidentid(), null,"details_json_obj","");

    }

    @Override
    public void onData(JSONObject jsonObject) {
        super.onData(jsonObject);

//        progressLayout.showContent();
        customFields = new ArrayList<CustomItem>();
        try
        {
            JSONObject parentObj = jsonObject.getJSONObject("payload");
            JSONObject items = parentObj. getJSONArray("incidents").getJSONObject(0).getJSONObject("customfields");


            Iterator<String> iterator = items.keys();
            List<String> listKeys = IteratorUtils.toList(iterator);
            Collections.sort(listKeys);

            for (String key:listKeys)
            {
                JSONObject object = items.getJSONObject(key);
                if(!object.getString("field_response").isEmpty())
                {
                    LinearLayout item_custom_field = (LinearLayout) getLayoutInflater().inflate(R.layout.item_custom_field, null);
                    ((TextView) item_custom_field.findViewById(R.id.mTextViewTitle)).setText(object.getString("field_name"));
                    ((TextView) item_custom_field.findViewById(R.id.mTextViewValue)).setText(object.getString("field_response"));
                    customFields_layout.addView(item_custom_field);
                }

            }

        } catch (JSONException e) {
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


    @OnClick(R.id.video_view)
    void setOnClickVideo(){
        for(int index = 0;index < media.length;index++)
        {
            if(media[index].getLink() != null && media[index].getLink().contains("youtube")) {
                Log.i("Link",media[index].getLink());
                watchYoutubeVideo(media[index].getLink());
                //break;
            }
        }

    }

    @OnClick(R.id.share)
    void setOnClickShare(){
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            share.putExtra(Intent.EXTRA_TEXT, "http://www.soseau.net/reports/view/" +incident.getIncidentid());
            startActivity(Intent.createChooser(share, "Partagez..."));


    }

    public void watchYoutubeVideo(String url){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Util.intentActivity(DetailIncidentActivity.this,intent);

    }
}
