package com.example.fredriksellgren.myapplication;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectdeviceCal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdevice);
    }

    public void kitTapButton(View view) {
        Button button = findViewById(R.id.kitcbut);


        Intent a = new Intent(SelectdeviceCal.this, KitchenActivityCal.class);
        startActivity(a);

    }

    public void bedTapButton(View view) {
        Button button = findViewById(R.id.bedbut);


        Intent b = new Intent(SelectdeviceCal.this, BedroomActivityCal.class);
        startActivity(b);
    }

    public void livTapButton(View view) {
        Button button = findViewById(R.id.livingbut);


        Intent c = new Intent(SelectdeviceCal.this, Living_RoomActivityCal.class);
        startActivity(c);
    }
}
