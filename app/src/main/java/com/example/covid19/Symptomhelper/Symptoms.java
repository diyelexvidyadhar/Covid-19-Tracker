package com.example.covid19.Symptomhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid19.MainActivity;
import com.example.covid19.R;
import com.example.covid19.Symptomhelper.preventionhelper.PreventionAdapter;
import com.example.covid19.Symptomhelper.preventionhelper.PreventionHelper;

import java.util.ArrayList;

public class Symptoms extends AppCompatActivity {
    Button selftest,call;
    ImageView back;
RecyclerView symptom,prevention;
 SymptomAdapter adapter;
 PreventionAdapter padapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        symptom =findViewById(R.id.symptoms_recycler);
        prevention=findViewById(R.id.prevention_recycler);
        selftest=findViewById(R.id.selftest_button);
        call=findViewById(R.id.call);
        back=findViewById(R.id.back_symptom);
        symptomRecycler();
        preventionRecycler();
        selftest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selfTest();
            }
        });

        back.setClickable(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Symptoms.this, MainActivity.class);
                finish();
            }
        });
call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"call clicked",Toast.LENGTH_SHORT).show();
    }
});
    }

    private void selfTest() {
        Intent intent=new Intent(Symptoms.this,Selftest.class);
        startActivity(intent);
    }

    private void preventionRecycler() {
        prevention.setHasFixedSize(true);
        prevention.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<PreventionHelper> preventionHelpers=new ArrayList<>();
        preventionHelpers.add(new PreventionHelper(R.drawable.usemask_icon,"Use Mask","Always wear mask whenever you go outside"));
        preventionHelpers.add(new PreventionHelper(R.drawable.donttouchface_icon,"Don't touch your face","Avoid touching your face, mouth and nose"));
        preventionHelpers.add(new PreventionHelper(R.drawable.cleansurface_icon,"Clean the surface","Disinfecting kills germs on surfaces"));
        preventionHelpers.add(new PreventionHelper(R.drawable.washhand_icon,"Wash hands","Always wash immediately after contact with a person who is sick"));
        preventionHelpers.add(new PreventionHelper(R.drawable.avoidcontact_icon,"Avoid contact","keeping space between yourself and other people outside of your home"));
        preventionHelpers.add(new PreventionHelper(R.drawable.covermouth_icon,"Cover mouth","Remember to always cover your mouth and nose while sneezing"));
        padapter=new PreventionAdapter(preventionHelpers);
        prevention.setAdapter(padapter);
    }

    private void symptomRecycler() {
        symptom.setHasFixedSize(true);
        symptom.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<SymptomHelper> symptomHelpers=new ArrayList<>();
        symptomHelpers.add(new SymptomHelper(R.drawable.cough_icon,"Cough"));
        symptomHelpers.add(new SymptomHelper(R.drawable.chestpain_icon,"Chest Pain"));
        symptomHelpers.add(new SymptomHelper(R.drawable.headache_icon,"Headache"));
        symptomHelpers.add(new SymptomHelper(R.drawable.runnynose_icon,"Runny Nose"));
        symptomHelpers.add(new SymptomHelper(R.drawable.musclepain_icon,"Muscle Pain"));
adapter=new SymptomAdapter(symptomHelpers);
symptom.setAdapter(adapter);
    }
}
