package com.example.akshaykumar.learnandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.akshaykumar.learnandroid.adapter.AkhadaasInformationListAdapter;
import com.example.akshaykumar.learnandroid.adapter.DrawerItemCustomAdapter;
import com.example.akshaykumar.learnandroid.app.AppController;
import com.example.akshaykumar.learnandroid.data.AkhadaasInformation;
import com.example.akshaykumar.learnandroid.data.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class InformationActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    public static final String[] IMAGE_NAME = {"banner1", "banner2", "banner3", "banner4", "banner5", "banner6",};

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private AkhadaasInformationListAdapter listAdapter;
    private List<AkhadaasInformation> akhadaasInformationList;
    private String URL_FEED = "http://52.37.171.220:8080/sangam/rest/retrieve_akhadaas_information";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();


        DataModel[] drawerItem = new DataModel[10];

        drawerItem[0] = new DataModel(R.drawable.house, "Home");
        drawerItem[1] = new DataModel(R.drawable.placeholder, "Mela Map");
        drawerItem[2] = new DataModel(R.drawable.chat, "Guru Details");
        drawerItem[3] = new DataModel(R.drawable.glasses, "Mela Videos");
        drawerItem[4] = new DataModel(R.drawable.magnifying, "Mela Events");
        drawerItem[5] = new DataModel(R.drawable.piggy, "Food");
        drawerItem[6] = new DataModel(R.drawable.heart, "Health");
        drawerItem[7] = new DataModel(R.drawable.phone, "Emergency");
        drawerItem[8] = new DataModel(R.drawable.settings, "Settings");
        drawerItem[9] = new DataModel(R.drawable.paperplane, "About Us");

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);


        LayoutInflater layoutinflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) layoutinflater.inflate(R.layout.nav_header_image, mDrawerList, false);
        mDrawerList.addHeaderView(header);

        mDrawerList.setItemChecked(0, true);
        mDrawerList.setSelection(0);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();



        listView = (ListView) findViewById(R.id.list);

        akhadaasInformationList = new ArrayList<AkhadaasInformation>();

        listAdapter = new AkhadaasInformationListAdapter(this, akhadaasInformationList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent i = new Intent(InformationActivity.this, AkharaaDetailsActivity.class);
                i.putExtra("AkhadaasInformation", akhadaasInformationList.get(position));
                startActivity(i);
            }
        });

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
                    URL_FEED, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }


    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray akhadaasInformationArray = response.getJSONArray("akhadaasInformationList");

            for (int i = 0; i < akhadaasInformationArray.length(); i++) {
                JSONObject akhadaasInformationObj = (JSONObject) akhadaasInformationArray.get(i);


                AkhadaasInformation akhadaasInformation = new AkhadaasInformation();
                akhadaasInformation.setId(akhadaasInformationObj.getInt("id"));
                akhadaasInformation.setName(akhadaasInformationObj.getString("name"));
                akhadaasInformation.setDetails(akhadaasInformationObj.getString("details"));
                akhadaasInformation.setImage(akhadaasInformationObj.getString("image"));
                akhadaasInformation.setCommunity(akhadaasInformationObj.getString("community"));
                akhadaasInformation.setContact(akhadaasInformationObj.getString("contact"));

                akhadaasInformationList.add(akhadaasInformation);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(mNavigationDrawerItemTitles[position - 1]);
        mDrawerLayout.closeDrawers();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

}