package com.example.covid19.maharastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid19.MainActivity;
import com.example.covid19.R;
import com.example.covid19.Symptomhelper.Symptoms;

public class MahaActivity extends AppCompatActivity {
TextView textView,totalcases,recover,death,travelpass;
ImageView backmaha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maha);
        totalcases=findViewById(R.id.total_maha);
        recover=findViewById(R.id.recover_maha);
        death=findViewById(R.id.death_maha);
        travelpass=findViewById(R.id.travel_pass);
        backmaha=findViewById(R.id.back_maha);
textView=findViewById(R.id.relif_fund);
textView.setSelected(true);
        travelpass.setSelected(true);
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
        totalcases.setText(bundle.getString("confirm"));
        recover.setText(bundle.getString("recover"));
        death.setText(bundle.getString("death"));
    }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cmrf.maharashtra.gov.in/CMRFCitizen/DonationOnlineForm.action"));
                startActivity(browserIntent);
            }
        });
        travelpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.mhpolice.in/registration?__cf_chl_jschl_tk__=3389213dfe5089402f231b6d805be50d83c02a53-1590230986-0-Aee16WqtH3oDRcJs67HGX8xA8JZmN-0ksH_LmXuxrKKCuzeNF8o5tl_AHG8Y-btJnP5TeT9RXiKHCVTGwJXT7h3DWB0sfbnPjW9XFPQjIopx0J4yRPqAbkjILfnevRR_tlaWg6mHgPPncokqpwmp9aEwxK5VTJi66Q5R67TXcD2pUg2beo1sWpHqJrWi_uCgZ-Na5ce0Mfm2eA2A6pTQ59rOdel7YtfkcwP8wvxvxoL6Gq0sXnoi-uTR1cCB07a52GMBORECJBDOHaj78EXa6iG-_BELcGE9RIGsVk1c0tD-"));
                startActivity(browserIntent);
            }
        });
        backmaha.setClickable(true);
        backmaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MahaActivity.this, MainActivity.class);
                finish();
            }
        });
    }
}
