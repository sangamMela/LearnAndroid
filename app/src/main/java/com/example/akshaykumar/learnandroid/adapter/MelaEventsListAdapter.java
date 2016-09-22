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
import com.example.akshaykumar.learnandroid.app.AppController;
import com.example.akshaykumar.learnandroid.data.MelaEvents;

public class MelaEventsListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MelaEvents> melaEvents;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MelaEventsListAdapter(Activity activity, List<MelaEvents> melaEvents) {
        this.activity = activity;
        this.melaEvents = melaEvents;
    }

    @Override
    public int getCount() {
        return melaEvents.size();
    }

    @Override
    public Object getItem(int location) {
        return melaEvents.get(location);
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
            convertView = inflater.inflate(R.layout.mela_events_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);
        TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);

        MelaEvents melaEvent = melaEvents.get(position);

        name.setText(melaEvent.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(melaEvent.getTimePosted()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(melaEvent.getDescription())) {
            statusMsg.setText(melaEvent.getDescription());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // user profile pic
        profilePic.setImageUrl(melaEvent.getUserImage(), imageLoader);

        // Feed image
        if (melaEvent.getEventImage() != null) {
            feedImageView.setImageUrl(melaEvent.getEventImage(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
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