package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19.Symptomhelper.Symptoms;
import com.example.covid19.districtwise.datalist;
import com.example.covid19.maharastra.MahaActivity;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView confirm,active,recover,death,lastUpdated;
    static final float END_SCALE=0.7f;
    SwipeRefreshLayout refreshLayout;
ListView listView;
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
    Intent intent;
ArrayList<StateData> stateDatalist=new ArrayList<>();
ConstraintLayout contentview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
drawerLayout=findViewById(R.id.drawer_layout);
///textView initialization///////
        refreshLayout=findViewById(R.id.refresh);
          confirm=findViewById(R.id.confirm_total);
        active=findViewById(R.id.active_total);
        recover=findViewById(R.id.recover_total);
        death=findViewById(R.id.death_total);
        lastUpdated=findViewById(R.id.last_updated);
        listView=findViewById(R.id.listView);
        contentview=findViewById(R.id.content);
        ////////////////////////////////
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_navigation,R.string.open_navigation);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationdrawer();
        dataFetch();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this, datalist.class);
                intent.putExtra("statenamekey",stateDatalist.get(i).state);
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataFetch();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void navigationdrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset=slideOffset*(1-END_SCALE);
                final float offsetScale=1-diffScaledOffset;
                contentview.setScaleX(offsetScale);
                contentview.setScaleY(offsetScale);

                final float xOffset=drawerView.getWidth()*slideOffset;
                final float xOffsetDiff=contentview.getWidth()*diffScaledOffset/2;
                final float xTranslation=xOffset-xOffsetDiff;
                contentview.setTranslationX(xTranslation);

            }
        });
    }

    private void dataFetch() {
        String url="https://api.rootnet.in/covid19-in/stats/latest";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response","Value"+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject data = new JSONObject(jsonObject.getString("data"));
                            JSONObject summary = new JSONObject(data.getString("summary"));
                            JSONArray unofficial_summary = data.getJSONArray("unofficial-summary");
                            JSONArray reg = data.getJSONArray("regional");
                            lastUpdated.setText(jsonObject.getString("lastRefreshed"));
                            String unSummary = unofficial_summary.get(0).toString();
                            JSONObject unsum = new JSONObject(unSummary);
                            Log.d("Val","Real :"+unsum.get("total"));
                            confirm.setText(unsum.get("total").toString());
                            recover.setText(unsum.get("recovered").toString());
                            active.setText(unsum.get("active").toString());
                            death.setText(unsum.get("deaths").toString());
                            String state,confirmedstate,recoverstate,deathstate;
                            for(int i = 0;i<reg.length();i++) {
                                JSONObject index = reg.getJSONObject(i);
                                state = (index.getString("loc"));
                                confirmedstate = (index.getString("totalConfirmed"));
                                recoverstate=(index.getString("discharged"));
                                deathstate =(index.getString("deaths"));
                                StateData dataAdd=new StateData(state,confirmedstate,recoverstate,deathstate);
                                stateDatalist.add(dataAdd);
                            }
//                            Log.d("loc"," "+state);

                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }

                        StateDataAdapter dataAdapter = new StateDataAdapter(MainActivity.this,R.layout.listitem_state,stateDatalist);
                        listView.setAdapter(dataAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error :Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                break;
            case R.id.symptoms:
                intent=new Intent(MainActivity.this, Symptoms.class);
                startActivity(intent);
                break;
            case R.id.maha:
                intent=new Intent(MainActivity.this, MahaActivity.class);
                intent.putExtra("confirm",stateDatalist.get(19).confirmState);
                intent.putExtra("recover",stateDatalist.get(19).recoverState);
                intent.putExtra("death",stateDatalist.get(19).deathState);
                startActivity(intent);
                break;
            case R.id.world:
                intent=new Intent(MainActivity.this,world_cases.class);
                startActivity(intent);
                break;
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Covid-19 tracker app";
                String shareSub = "made by vidyadhar";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
            case R.id.yt:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC-NivZpvfxHg00lLKb9tr5g?view_as=subscriber"));
                startActivity(browserIntent);
                break;
            case R.id.insta:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/vidyadharjogi/"));
                startActivity(browserIntent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
