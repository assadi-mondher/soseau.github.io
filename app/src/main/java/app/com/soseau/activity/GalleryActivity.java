package app.com.soseau.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import app.com.soseau.R;
import app.com.soseau.model.Media;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import views.HackyViewPager;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    HackyViewPager view_pager;

    private Media[] media;
    private ArrayList<Media> listImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        Intent intent = getIntent();
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(intent.getStringExtra("title"));
        media = (Media[]) intent.getSerializableExtra("media");
        listImages = new ArrayList<Media>();

        listImages = new ArrayList<Media>(Arrays.asList(media));
        view_pager.setAdapter(new ImagePagerAdapter(listImages, GalleryActivity.this));
    }


    static class ImagePagerAdapter extends PagerAdapter {

        private ArrayList<Media> images;
        private Context context;

        ImagePagerAdapter(ArrayList<Media>  images, Context context) {
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
            Glide.with(context).load(images.get(position).getLink_url()).into(photoView);
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


}



