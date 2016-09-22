package com.example.akshaykumar.learnandroid;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.akshaykumar.learnandroid.app.AppController;
import com.example.akshaykumar.learnandroid.data.AkhadaasInformation;

public class AkharaaDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akharaa_details);

        TextView nameText = (TextView) findViewById(R.id.akharaaName);
        TextView detailsText = (TextView) findViewById(R.id.akharaaDetails);
        TextView communityText = (TextView) findViewById(R.id.akhaaraCommunity);
        TextView contactText = (TextView) findViewById(R.id.akharaaContact);
        NetworkImageView akharaaPic = (NetworkImageView) findViewById(R.id.akharaaPic);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        //ImageView image = (ImageView) findViewById(R.id.akharaaPic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Akharaa Information");
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);


        AkhadaasInformation model = (AkhadaasInformation) getIntent().getSerializableExtra("AkhadaasInformation");

        akharaaPic.setImageUrl(model.getImage(), imageLoader);
        nameText.setText(model.getName());
        detailsText.setText(model.getDetails());
        communityText.setText(model.getCommunity());
        contactText.setText(model.getContact());


    }
}
