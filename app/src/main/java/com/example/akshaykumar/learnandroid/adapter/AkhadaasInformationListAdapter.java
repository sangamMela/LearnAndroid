
package com.example.akshaykumar.learnandroid.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.akshaykumar.learnandroid.R;
import com.example.akshaykumar.learnandroid.app.AppController;
import com.example.akshaykumar.learnandroid.data.AkhadaasInformation;

import java.util.List;

public class AkhadaasInformationListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<AkhadaasInformation> akhadaasInformation;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AkhadaasInformationListAdapter(Activity activity, List<AkhadaasInformation> akhadaasInformation) {
        this.activity = activity;
        this.akhadaasInformation = akhadaasInformation;
    }

    @Override
    public int getCount() {
        return akhadaasInformation.size();
    }

    @Override
    public Object getItem(int location) {
        return akhadaasInformation.get(location);
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
            convertView = inflater.inflate(R.layout.akharaas_information_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.akharaaName);
        TextView details = (TextView) convertView.findViewById(R.id.akharaaDetails);

        NetworkImageView akharaaPic = (NetworkImageView) convertView
                .findViewById(R.id.akharaaPic);

        AkhadaasInformation information = akhadaasInformation.get(position);

        name.setText(information.getName());
        details.setText(information.getDetails());
        akharaaPic.setImageUrl(information.getImage(), imageLoader);

        return convertView;
    }

}