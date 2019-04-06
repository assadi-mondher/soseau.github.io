package app.com.soseau.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import app.com.soseau.R;
import app.com.soseau.activity.PhotoViewerActivity;


public class ImageAdapter extends BaseAdapter {

    ArrayList<String> mList;
    LayoutInflater mInflater;
    Context mContext;
    ImageLoader imageLoader;
    private DisplayImageOptions options;


    public ImageAdapter(Context context, ArrayList<String> imageList, ImageLoader imageLoader) {
        // TODO Auto-generated constructor stub
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = new ArrayList<String>();
        this.mList = imageList;
        this.imageLoader = imageLoader;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc().build();

    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_multiphoto_item, null);
        }

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);
        imageView.setTag(position);
        imageLoader.displayImage("file://" + mList.get(position), imageView, options);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int id = (Integer) arg0.getTag();

                Intent photoViewer = new Intent(mContext, PhotoViewerActivity.class);
                photoViewer.putExtra("current_index", id);
                photoViewer.putStringArrayListExtra("photos", mList);

                mContext.startActivity(photoViewer);
            }
        });


        return convertView;
    }
}