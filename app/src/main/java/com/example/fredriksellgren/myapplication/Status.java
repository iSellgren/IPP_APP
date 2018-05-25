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

        TextView textView = (TextView) findViewById(R.id.textView);


        Status.BackGround b = new Status.BackGround();

        b.execute(" ", " ", " ");

        Status.BackGrounds a = new Status.BackGrounds();

        a.execute(" ", " ", " ");

        Status.BackGroundsd c = new Status.BackGroundsd();

        c.execute(" ", " ", " ");
    }





    //Hämtar data från databasen
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String id = "3"; // Sätter id till 1
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.43.145/refresh.php"); // ansluter till databasens URL.

                String urlParams = "&id="+id;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // kollar om anslutningen finns.
                httpURLConnection.setDoOutput(true); // DoOutput är satt till true  för att använda URL som output.
                httpURLConnection.setRequestMethod("POST");
                OutputStream os = httpURLConnection.getOutputStream(); // kastar en exception om URL inte supportar output
                os.write(urlParams.getBytes()); //Denna Skriver data till URL.
                os.flush(); // Flushar strömmen
                os.close(); // stänger strömmen
                InputStream is = httpURLConnection.getInputStream(); // Kollar vad vi får tillbaka
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
        protected void onPostExecute(String result) { // Datan vi får tillbaka placeras i strängen result
            TextView textView =(TextView)findViewById(R.id.textView); // Datan skrivs ut i en textView
            String allt[] = result.split(" ");
            String timeDown = allt[2].substring(3);
            String timeUp = allt[3].substring(3);


            textView.setText(allt[1] +" Percent" +"\n" +timeDown+ " Time when curtain lowers" + "\n" + timeUp+ " Time when curtain raises");

        }
    }


    class BackGrounds extends AsyncTask<String, String, String> {

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
            TextView textView =(TextView)findViewById(R.id.textView1);
            String allt[] = result.split(" ");
            String timeDown = allt[2].substring(3);
            String timeUp = allt[3].substring(3);


            textView.setText(allt[1] +" Percent" +"\n" +timeDown+ " Time when curtain lowers" + "\n" + timeUp+ " Time when curtain raises");




        }
    }
    class BackGroundsd extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String id = "2";
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
            TextView textView =(TextView)findViewById(R.id.textView2);
            String allt[] = result.split(" ");
            String timeDown = allt[2].substring(3);
            String timeUp = allt[3].substring(3);


            textView.setText(allt[1] +" Percent" +"\n" +timeDown+ " Time when curtain lowers" + "\n" + timeUp+ " Time when curtain raises");




        }
    }


}
