package com.example.akshaykumar.learnandroid.adapter;



import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.akshaykumar.learnandroid.FeedImageView;
import com.example.akshaykumar.learnandroid.R;
import com.example.akshaykumar.learnandroid.VideoImageView;
import com.example.akshaykumar.learnandroid.app.AppController;
import com.example.akshaykumar.learnandroid.data.MelaVideos;

public class MelaVideosListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MelaVideos> melaVideos;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MelaVideosListAdapter(Activity activity, List<MelaVideos> melaVideos) {
        this.activity = activity;
        this.melaVideos = melaVideos;
    }

    @Override
    public int getCount() {
        return melaVideos.size();
    }

    @Override
    public Object getItem(int location) {
        return melaVideos.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.mela_videos_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
//        TextView statusMsg = (TextView) convertView
//                .findViewById(R.id.txtStatusMsg);

        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        VideoImageView feedImageView = (VideoImageView) convertView
                .findViewById(R.id.feedImage1);

        MelaVideos melaVideo = melaVideos.get(position);

        name.setText(melaVideo.getHeading());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(melaVideo.getTimePosted()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

//        // Chcek for empty status message
//        if (!TextUtils.isEmpty(melaVideo.getDescription())) {
//            statusMsg.setText(melaVideo.getDescription());
//            statusMsg.setVisibility(View.VISIBLE);
//        } else {
//            // status is empty, remove from view
//            statusMsg.setVisibility(View.GONE);
//        }

        // user profile pic
        profilePic.setImageUrl(melaVideo.getImageUrl(), imageLoader);

        // Feed image
        if (melaVideo.getVideoUrl() != null) {
            feedImageView.setImageUrl("http://img.youtube.com/vi/"+melaVideo.getVideoUrl()+"/0.jpg", imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new VideoImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }

        return convertView;
    }

}