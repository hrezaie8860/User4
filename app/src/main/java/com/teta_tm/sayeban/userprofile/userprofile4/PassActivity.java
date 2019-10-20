package com.teta_tm.sayeban.userprofile.userprofile4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PassActivity extends AppCompatActivity {
    ImageView img_chng_pass;
    Button btn_set_chng_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        inIt();

        img_chng_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
    public void inIt(){
        img_chng_pass= findViewById(R.id.img_chng_pass);
        btn_set_chng_pass= findViewById(R.id.btn_set_chng_pass);
    }
}

