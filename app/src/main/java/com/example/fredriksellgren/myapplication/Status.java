package com.example.fredriksellgren.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Status extends AppCompatActivity {
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status2);

        TextView textView =(TextView)findViewById(R.id.textView);
    }

    public void Done(View view) {

        Status.BackGround b = new Status.BackGround();

        b.execute(" ", " "," ");

    }


    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String id = "1";
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.43.145/refresh.php");

                String urlParams = "&id="+id;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();



                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView =(TextView)findViewById(R.id.textView);
            String allt[] = result.split(" ");
            String timeDown = result.substring(9,15);
            String timeUp = result.substring(18,23);


            textView.setText(allt[1] +" Percent" +"\n" +timeDown+ " Time when curtain goes lowers" + "\n" + timeUp+ " Time when curtain goes raises");




        }
    }

}
