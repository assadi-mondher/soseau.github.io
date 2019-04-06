package app.com.soseau.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.com.soseau.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;
import views.HackyViewPager;

public class PhotoViewerActivity extends AppCompatActivity {

    private ArrayList<String> imageUrls;
    private int current_index;

    @BindView(R.id.view_pager)
    HackyViewPager view_pager;

    @BindView(R.id.share)
    FloatingActionButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.hide();


        Bundle bundle = getIntent().getExtras();
        imageUrls = getIntent().getStringArrayListExtra("photos");
        current_index = getIntent().getExtras().getInt("current_index");
        view_pager.setAdapter(new ImagePagerAdapter(imageUrls, this));
        view_pager.setCurrentItem(current_index);

    }

    static class ImagePagerAdapter extends PagerAdapter {

        private ArrayList<String> images;
        private Context context;

        ImagePagerAdapter(ArrayList<String>  images, Context context) {
            this.images = images;
            this.context = context;

        }

        @Override
        public int getCount()
        {
            return images.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(context).load(images.get(position)).into(photoView);
            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);



            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
    @OnClick(R.id.share)
    void setOnClickShare(){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_TEXT, imageUrls.get(view_pager.getCurrentItem()));
        startActivity(Intent.createChooser(share, "Partagez..."));


    }

}
