package com.example.fredriksellgren.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;





    public class Living_RoomActivityCal extends AppCompatActivity implements
            View.OnClickListener {
        private static SeekBar seek_bar;
        private static TextView text_view;
        EditText up, down;
        String Up, Down;
        Context ctx = this;
        int progress_value;
        Button btnDatePicker, btnTimePicker, btnStopTimePicker;
        EditText txtDate, txtTime, txtStop;
        private int mYear, mMonth, mDay, mHour, mMinute, mEndHour, mEndMinute;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_living_room_cal);

            btnTimePicker = findViewById(R.id.btn_time);
            btnStopTimePicker = findViewById(R.id.btn_stop);

            txtTime = findViewById(R.id.in_time);
            txtStop = findViewById(R.id.in_stop);


            btnTimePicker.setOnClickListener(this);
            btnStopTimePicker.setOnClickListener(this);
            seebbar();

        }

        @Override
        public void onClick(View v) {

            if (v == btnTimePicker) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(String.format("%02d%02d", hourOfDay, minute));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
            if (v == btnStopTimePicker) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mEndHour = c.get(Calendar.HOUR_OF_DAY);
                mEndMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timeStopPickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtStop.setText(String.format("%02d%02d", hourOfDay, minute));
                            }
                        }, mEndHour, mEndMinute, true);
                timeStopPickerDialog.show();
            }
        }

        public void seebbar() {
            seek_bar = findViewById(R.id.seekBar);
            text_view = findViewById(R.id.textView);
            //infos = (TextView)findViewById(R.id.info);
            text_view.setText("Covered : " + seek_bar.getProgress());


            seek_bar.setOnSeekBarChangeListener(
                    new SeekBar.OnSeekBarChangeListener() {


                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            progress_value = progress;
                            text_view.setText("Covered : " + progress);

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            //Toast.makeText(LoginActivity.this,"SeekBar in StartTracking",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            //text_view.setText("Covered : " + progress_value + " / " +seek_bar.getMax());
                            //Toast.makeText(LoginActivity.this,"SeekBar in StopTracking",Toast.LENGTH_LONG).show();
                        }
                    }
            );

        }

        public void Done(View view) {

            BackGround b = new BackGround();

            b.execute(String.valueOf(progress_value), txtTime.getText().toString(), txtStop.getText().toString());


        }


        class BackGround extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... params) {
                String up = params[0];
                String time_up = params[1];
                String time_down = params[2];
                String id = "1";
                String data = "";
                int tmp;

                try {
                    URL url = new URL("http://192.168.43.145/time.php");

                    String urlParams = "time_up=" + up + "&time_end=" + time_down + "&time_begin=" + time_up + "&id=" + id;


                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();
                    InputStream is = httpURLConnection.getInputStream();
                    while ((tmp = is.read()) != -1) {
                        data += (char) tmp;
                    }
                    is.close();
                    httpURLConnection.disconnect();


                    return data;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Exception: " + e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception: " + e.getMessage();
                }

            }

            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(ctx, "Schedule set", Toast.LENGTH_LONG).show();
                Intent passdata_intent = new Intent(Living_RoomActivityCal.this, StatusActivity.class);
                passdata_intent.putExtra("result", result);
                startActivity(passdata_intent);

            }

        }


    }

