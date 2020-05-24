package com.example.covid19.districtwise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class datalist extends AppCompatActivity {
    TextView stateupdate;
    ListView listview;
    ArrayList<DataDistrict> Datadistrictlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist);
        stateupdate=findViewById(R.id.stateUpdated);
        listview =(ListView)findViewById(R.id.listViewdist);
        Bundle bundle=getIntent().getExtras();
        if(bundle !=null){

            stateupdate.setText(bundle.getString("statenamekey"));
            districtdatafeath();
        }
    }

    private void districtdatafeath() {
        String url="https://api.covid19india.org/state_district_wise.json";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject data = jsonObject.getJSONObject(stateupdate.getText().toString());
                            JSONObject districtData = data.getJSONObject("districtData");
                            Iterator<String> keysStr = districtData.keys();
                            while(keysStr.hasNext()) {
                                String key = keysStr.next();
                                JSONObject value = (JSONObject) districtData.get(key);
                                String stated,confirmedstated,deathstated,recoverd;
                                stated = key;
                                confirmedstated = value.getString("confirmed");
                                deathstated =value.getString("deceased");
                                recoverd=value.getString("recovered");
                                DataDistrict dataAdddistrict=new DataDistrict(stated,confirmedstated,recoverd,deathstated);
                                Datadistrictlist.add(dataAdddistrict);
                            }

//                            Log.d("loc"," "+state);

                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        }

                        DatalistAdapter datalistAdapter = new DatalistAdapter(datalist.this,R.layout.list_itemdatalist,Datadistrictlist);
                        listview.setAdapter(datalistAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error :"+error,Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }
}
