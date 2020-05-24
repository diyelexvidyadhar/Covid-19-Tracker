package com.example.covid19.Symptomhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid19.MainActivity;
import com.example.covid19.R;

public class Selftest extends AppCompatActivity {
Button button1no,button1yes,button2no,button2dry,button2wet,button3no,button3yes,button4no,button4yes,checkresult;
Dialog myDialog;
private int temp=1,coughtype=2,travelled=1,incontact=1;
ImageView backself;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest);
        button1no=findViewById(R.id.buttonq1no);
        button1yes=findViewById(R.id.buttonq1yes);
        checkresult=findViewById(R.id.check_result);
        backself=findViewById(R.id.back_selftest);
myDialog=new Dialog(this);
        button2no=findViewById(R.id.buttonq2no);
        button2wet=findViewById(R.id.buttonq2WET);
        button2dry=findViewById(R.id.buttonq2DRY);
        button3no=findViewById(R.id.buttonq3no);
        button3yes=findViewById(R.id.buttonq3yes);
        button4no=findViewById(R.id.buttonq4no);
        button4yes=findViewById(R.id.buttonq4yes);

        button1no.setBackgroundColor(getColor(R.color.pressed));
        button1yes.setBackgroundColor(getColor(R.color.notpressed));
        button2no.setBackgroundColor(getColor(R.color.pressed));
        button2wet.setBackgroundColor(getColor(R.color.notpressed));
        button2dry.setBackgroundColor(getColor(R.color.notpressed));
        button3yes.setBackgroundColor(getColor(R.color.notpressed));
        button3no.setBackgroundColor(getColor(R.color.pressed));
        button4yes.setBackgroundColor(getColor(R.color.notpressed));
        button4no.setBackgroundColor(getColor(R.color.pressed));

        button1no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1no.setBackgroundColor(getColor(R.color.pressed));
                button1yes.setBackgroundColor(getColor(R.color.notpressed));
                temp=1;
            }
        });
button1yes.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        button1no.setBackgroundColor(getColor(R.color.notpressed));
        button1yes.setBackgroundColor(getColor(R.color.pressed));
        temp=10;
    }
});
        backself.setClickable(true);
        backself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Selftest.this, Symptoms.class);
                finish();
            }
        });
coughbuttonlistener();
travelzonelistener();
diagnosed();
checkResult();
    }

    private void checkResult() {
        checkresult.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                ImageView closapopup;
                TextView resultanalyzed,tag;
                myDialog.setContentView(R.layout.pop_up);
                tag=myDialog.findViewById(R.id.tag);
                resultanalyzed=myDialog.findViewById(R.id.resultanalized);
                int totalcal=temp+coughtype+incontact+travelled;
                String calculated=Integer.toString(totalcal);
                resultanalyzed.setText(calculated+" %");
                closapopup=myDialog.findViewById(R.id.closepopup);
                if(totalcal <= 40){
                    tag.setText("low infection probablity");
                    tag.setTextColor(getColor(R.color.low_probablity));
                }else if(totalcal <=70){
                    tag.setText("medium infection probablity");
                    tag.setTextColor(getColor(R.color.medium_probablity));
                }else{
                    tag.setText("high infection probablity");
                    tag.setTextColor(getColor(R.color.high_probablity));
                }
                closapopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });
    }

    private void diagnosed() {
        button4no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button4yes.setBackgroundColor(getColor(R.color.notpressed));
                button4no.setBackgroundColor(getColor(R.color.pressed));
                incontact=1;
            }
        });
        button4yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button4yes.setBackgroundColor(getColor(R.color.pressed));
                button4no.setBackgroundColor(getColor(R.color.notpressed));
                incontact=50;
            }
        });
    }

    private void travelzonelistener() {
        button3no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3yes.setBackgroundColor(getColor(R.color.notpressed));
                button3no.setBackgroundColor(getColor(R.color.pressed));
                travelled=1;
            }
        });
        button3yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3yes.setBackgroundColor(getColor(R.color.pressed));
                button3no.setBackgroundColor(getColor(R.color.notpressed));
                travelled=20;
            }
        });
    }

    private void coughbuttonlistener() {
        button2no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2no.setBackgroundColor(getColor(R.color.pressed));
                button2wet.setBackgroundColor(getColor(R.color.notpressed));
                button2dry.setBackgroundColor(getColor(R.color.notpressed));
                coughtype=2;
            }
        });
        button2dry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2no.setBackgroundColor(getColor(R.color.notpressed));
                button2wet.setBackgroundColor(getColor(R.color.notpressed));
                button2dry.setBackgroundColor(getColor(R.color.pressed));
                coughtype=15;
            }
        });
        button2wet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2no.setBackgroundColor(getColor(R.color.notpressed));
                button2wet.setBackgroundColor(getColor(R.color.pressed));
                button2dry.setBackgroundColor(getColor(R.color.notpressed));
                coughtype=10;
            }
        });
    }

}
