package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {
    Button b;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //download
        b = findViewById(R.id.id_button);
        editText = findViewById(R.id.id_editText);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AsyncTaskDownloaderClassThing asyncTaskDownloaderClassThing = new AsyncTaskDownloaderClassThing();
                new AsyncTaskDownloaderClassThing().execute();
            }
        });

    }

    public class AsyncTaskDownloaderClassThing extends AsyncTask<Void, Void, String> {

        TextView textView;
        EditText editText;
        String zip;
        Button button;
        TextView textView2;
        TextView textView3;
        JSONObject jsonObject;

        @Override
        protected String doInBackground(Void... voids) {
            Log.d("ABDCEFGH", "STSRTED DO IN BAKCGROUND");
            textView = findViewById(R.id.id_textview);
            editText = findViewById(R.id.id_editText);
            button = findViewById(R.id.id_button);
            textView2 = findViewById(R.id.id_textview1);
            textView3 = findViewById(R.id.id_textview2);
            String link = "https://stackoverflow.com/";
            link = "https://api.openweathermap.org/geo/1.0/zip?zip=" + editText.getText().toString() + "&appid=eedeecbab82acdedd822beb41d9df392";
            URL url;
            Log.d("ABCDEFGH", link);
            String line = "";
            try {
                url = new URL(link);
                URLConnection urlConnection = url.openConnection();
                InputStream stream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                line = bufferedReader.readLine();
                Log.d("ABCDEFGH", line);
            } catch (IOException e) {
                Log.d("Tag", "error");
                e.printStackTrace();
                Context context = getApplicationContext();
                //Toast.makeText(context,"Error try again",Toast.LENGTH_LONG).show();
            }
            try {
                jsonObject = new JSONObject(line);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("ABCDEFGH", "JSON");
            }
            return line;
        }

        @Override
        protected void onPostExecute(String line) {
            super.onPostExecute(line);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    zip = editText.getText().toString();
                    editText.setText("ZipCode");
                }
            });
            try {
                textView.setText(jsonObject.getString("name") + ", US");
                textView2.setText("Lat" + jsonObject.getString("lat"));
                textView3.setText("Lon" + jsonObject.getString("lon"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //syncTaskDownloaderClassThing2 asyncTaskDownloaderClassThing2 = new AsyncTaskDownloaderClassThing2();
            new AsyncTaskDownloaderClassThing2().execute();
        }
    }

    public class AsyncTaskDownloaderClassThing2 extends AsyncTask<Void, Void, String> {

        TextView textView2;
        TextView textView3;
        JSONObject jsonObject;
        TextView cloud1;
        TextView cloud2;
        TextView cloud4;
        TextView cloud3;
        TextView time3;
        TextView time2;
        TextView time1;
        TextView time4;
        TextView temp1;
        TextView temp2;
        TextView temp3;
        TextView temp4;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        JSONArray k;
        JSONObject object;
        JSONObject object1;
        ArrayList<String> cloud;
        long unixTime1;
        long unixTime2;
        long unixTime3;
        long unixTime4;
        DateTimeFormatter formatter;
        Instant t1;
        String t2;
        String t3;
        String t4;
        Date d1;
        Date d2;
        Date d3;
        Date d4;
        String s1;
        String s2;
        String s3;
        String s4;
        SimpleDateFormat sdf;


        @Override
        protected String doInBackground(Void... voids) {
            textView2 = findViewById(R.id.id_textview1);
            textView3 = findViewById(R.id.id_textview2);
            cloud1 = findViewById(R.id.cloud2);
            cloud2 = findViewById(R.id.cloud3);
            cloud3 = findViewById(R.id.cloud1);
            cloud4 = findViewById(R.id.cloud4);
            temp1 = findViewById(R.id.temp2);
            temp2 = findViewById(R.id.temp3);
            temp3 = findViewById(R.id.temp1);
            temp4 = findViewById(R.id.temp4);
            time1 = findViewById(R.id.time2);
            time2 = findViewById(R.id.time3);
            time3 = findViewById(R.id.time1);
            time4 = findViewById(R.id.time4);
            imageView1 = findViewById(R.id.id_imageview2);
            imageView2 = findViewById(R.id.id_imageview3);
            imageView3 = findViewById(R.id.id_imageview1);
            imageView4 = findViewById(R.id.id_imageview4);


            String link = "https://stackoverflow.com/";
            link = "https://api.openweathermap.org/data/2.5/onecall?lat=" + textView2.getText().toString().substring(3) + "&lon=" + textView3.getText().toString().substring(3) + "&units=imperial&exclude=daily,minutely,alerts&appid=eedeecbab82acdedd822beb41d9df392";
            URL url;
            String line = "";
            try {
                url = new URL(link);
                URLConnection urlConnection = url.openConnection();
                InputStream stream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                line = bufferedReader.readLine();
                Log.d("sf", line);
                Log.d("link", link);
            } catch (IOException e) {
                Log.d("Tag", "error");
                e.printStackTrace();
                //Context context = getApplicationContext();
                //Toast.makeText(context,"Error try again",Toast.LENGTH_LONG).show();
            }
            try {
                jsonObject = new JSONObject(line);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return line;
        }

        @Override
        protected void onPostExecute(String line) {
            super.onPostExecute(line);
            cloud2.setText("f");
            cloud = new ArrayList<String>();
            formatter = DateTimeFormatter.ofPattern("HH:mm");
            try {
                k = jsonObject.getJSONArray("hourly");

                cloud1.setText(k.getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("description").toString());
                cloud2.setText(k.getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("description").toString());
                cloud3.setText(k.getJSONObject(2).getJSONArray("weather").getJSONObject(0).get("description").toString());
                cloud4.setText(k.getJSONObject(3).getJSONArray("weather").getJSONObject(0).get("description").toString());
                temp4.setText(((k.getJSONObject(3).get("temp")).toString().substring(0, 2) + " °F"));
                temp3.setText(((k.getJSONObject(2).get("temp")).toString().substring(0, 2) + " °F"));
                temp2.setText(((k.getJSONObject(1).get("temp")).toString().substring(0, 2) + " °F"));
                temp1.setText(((k.getJSONObject(0).get("temp")).toString().substring(0, 2) + " °F"));
                if (k.getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("clouds")) {
                    imageView1.setImageResource(R.drawable.cloud);
                } else if (k.getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("snow")) {
                    imageView1.setImageResource(R.drawable.snow);
                } else if (k.getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("rain")) {
                    imageView1.setImageResource(R.drawable.rain);
                } else {
                    imageView1.setImageResource(R.drawable.sun);
                }
                //image2
                if (k.getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("clouds")) {
                    imageView2.setImageResource(R.drawable.cloud);
                } else if (k.getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("snow")) {
                    imageView2.setImageResource(R.drawable.snow);
                } else if (k.getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("rain")) {
                    imageView2.setImageResource(R.drawable.rain);
                } else {
                    imageView2.setImageResource(R.drawable.sun);
                }
                //image3
                if (k.getJSONObject(2).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("clouds")) {
                    imageView3.setImageResource(R.drawable.cloud);
                } else if (k.getJSONObject(2).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("snow")) {
                    imageView3.setImageResource(R.drawable.snow);
                } else if (k.getJSONObject(2).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("rain")) {
                    imageView3.setImageResource(R.drawable.rain);
                } else {
                    imageView3.setImageResource(R.drawable.sun);
                }
                //image4
                if (k.getJSONObject(3).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("clear"))
                {
                    imageView4.setImageResource(R.drawable.sun);
                }
                else if (k.getJSONObject(3).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("snow")) {
                    imageView4.setImageResource(R.drawable.snow);
                }
                if (k.getJSONObject(3).getJSONArray("weather").getJSONObject(0).get("main").toString().equalsIgnoreCase("rain")) {
                    imageView4.setImageResource(R.drawable.rain);
                } else {
                    imageView4.setImageResource(R.drawable.cloud);
                }
                s1 = k.getJSONObject(0).get("dt").toString();
                s2 = k.getJSONObject(1).get("dt").toString();
                s3 = k.getJSONObject(2).get("dt").toString();
                s4 = k.getJSONObject(3).get("dt").toString();
                unixTime1 = Long.parseLong(s1);
                unixTime2 = Long.parseLong(s2);
                unixTime3 = Long.parseLong(s3);
                unixTime4 = Long.parseLong(s4);
                d1 = new java.util.Date(unixTime1*1000L);
                d2 = new java.util.Date(unixTime2*1000L);
                d3 = new java.util.Date(unixTime3*1000L);
                d4 = new java.util.Date(unixTime4*1000L);
                sdf = new java.text.SimpleDateFormat("HH:mm");
                Log.d("P","PRETIMEZONE");
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("EST"));
                Log.d("P","POSTTIMEZONE");
                s1 = sdf.format(d1)+"AM";
                s2 = sdf.format(d2)+"AM";
                s3 = sdf.format(d3)+"AM";
                s4 = sdf.format(d4)+"AM";
                if(Integer.valueOf(s1.substring(0,2))>12)
                {
                    s1 = String.valueOf(Integer.valueOf(s1.substring(0,2))-12) + ":00PM";
                }
                else if(Integer.valueOf(s1.substring(0,2))==00)
                {
                    s1 = "12:00AM";
                }
                if(Integer.valueOf(s2.substring(0,2))>12)
                {
                    s2 = String.valueOf(Integer.valueOf(s2.substring(0,2))-12) + ":00PM";
                }
                else if(Integer.valueOf(s2.substring(0,2))==00)
                {
                    s2 = "12:00AM";
                }
                if(Integer.valueOf(s3.substring(0,2))>12)
                {
                    s3 = String.valueOf(Integer.valueOf(s3.substring(0,2))-12) + ":00PM";
                }
                else if(Integer.valueOf(s3.substring(0,2))==00)
                {
                    s3 = "12:00AM";
                }
                if(Integer.valueOf(s4.substring(0,2))>12)
                {
                    s4 = String.valueOf(Integer.valueOf(s4.substring(0,2))-12) + ":00PM";
                }
                else if(Integer.valueOf(s4.substring(0,2))==00)
                {
                    s4 = "12:00AM";
                }
                time1.setText(s1);
                time2.setText(s2);
                time3.setText(s3);
                time4.setText(s4);
                Log.d("P",s1);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("fs", "error");
            }

        }
    }
}