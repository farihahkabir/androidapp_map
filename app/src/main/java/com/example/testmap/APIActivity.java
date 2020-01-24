package com.example.testmap;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIActivity extends AsyncTask<String, Void, String> {
    String result;

    @Override
    protected String doInBackground(String... urls) {
        result = "";
        URL link;
        HttpURLConnection myConnection = null;

        try{
            link = new  URL(urls[0]);
            myConnection = (HttpURLConnection)link.openConnection();
            InputStream in = myConnection.getInputStream();
            InputStreamReader myStreamReader = new  InputStreamReader(in);
            int data = myStreamReader.read();
            while(data != - 1){
                char current = (char)data;
                result += current;
                data = myStreamReader.read();
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
            //Retrieve JSON Objects
            JSONObject myObject = new JSONObject(result);
            JSONObject data = new JSONObject(myObject.getString("data"));
            JSONObject city = new JSONObject(data.getString("city"));
            JSONArray geo = city.getJSONArray("geo");
//            JSONObject lat = geo.getJSONObject(0);
//            JSONObject longitude = geo.getJSONObject(1);
            JSONObject time = new JSONObject(data.getString("time"));
            JSONObject iaqi = new JSONObject(data.getString("iaqi"));
            JSONObject co = new JSONObject(iaqi.getString("co"));
            JSONObject no2 = new JSONObject(iaqi.getString("no2"));
            JSONObject o3 = new JSONObject(iaqi.getString("o3"));
            JSONObject so2 = new JSONObject(iaqi.getString("so2"));

            String aqi = data.getString("aqi");
            String name = city.getString("name");
            String gmt = time.getString("s");
            String lat = geo.getString(0);
            String longitude = geo.getString(1);
            String vco = co.getString("v");
            String vno2 = no2.getString("v");
            String vo3 = o3.getString("v");
            String vso2 = so2.getString("v");

            //Set displays
            DataActivity.aqi.setText("AQI: " + aqi);
            DataActivity.name.setText("Name: " + name);
            DataActivity.gmt.setText("GMT: " + gmt);
            DataActivity.lat.setText("Latitude: " + lat);
            DataActivity.longitude.setText("Longitude: " + longitude);
            DataActivity.co.setText("Carbon Monoxide: " + vco);
            DataActivity.no2.setText("NO2: " + vno2);
            DataActivity.o3.setText("Ozone: " + vo3);
            DataActivity.so2.setText("SO2: " + vso2);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
