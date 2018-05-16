package com.example.fredriksellgren.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        result = getIntent().getExtras().getString("result");

        TextView textView =(TextView)findViewById(R.id.textView);
        textView.setText("Schema: "+" "+result);
    }
}
