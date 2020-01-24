package com.example.testmap;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    static TextView aqi;
    static TextView name;
    static TextView gmt;
    static TextView lat;
    static TextView longitude;
    static TextView co;
    static TextView no2;
    static TextView o3;
    static TextView so2;
    static Spinner spinner;
    static String cityMenu;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        cityMenu = "Beijing";

        aqi = (TextView) findViewById(R.id.aqi);
        name = (TextView) findViewById(R.id.nameField);
        aqi = (TextView) findViewById(R.id.aqi);
        gmt = (TextView) findViewById(R.id.gmt);
        lat = (TextView) findViewById(R.id.lat);
        longitude = (TextView) findViewById(R.id.longitude);
        co = (TextView) findViewById(R.id.co);
        no2 = (TextView) findViewById(R.id.no2);
        o3 = (TextView) findViewById(R.id.o3);
        so2 = (TextView) findViewById(R.id.so2);

        //drop down menu is called spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DataActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cities));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //to allow adapter to show our spinner
        spinner.setAdapter(myAdapter);
//        spinner.setOnItemClickListener(this);

        APIActivity getData = new APIActivity();
        getData.execute("https://api.waqi.info/feed/Beijing" + "/?token=dde58930fe41788aa121c1a177000a9b49af7ed9");

        final DatabaseHelper helper = new DatabaseHelper(this);
        final ArrayList array_list = helper.getAllInfo();
//        listView = findViewById(R.id.listView);

//        final ArrayAdapter arrayAdapter = new ArrayAdapter(DataActivity.this, android.R.layout.simple_list_item_1, array_list);
//        listView.setAdapter(arrayAdapter);
//
//        findViewById(R.id.viewAllBtn).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                array_list.clear();
//                array_list.addAll(helper.getAllInfo());
//                arrayAdapter.notifyDataSetChanged();
//                listView.invalidateViews();
//                listView.refreshDrawableState();
//            }
//        });
//
//        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if(!name.getText().toString().isEmpty()  && !aqi.getText().toString().isEmpty()){
//                    if (helper.insert(name.getText().toString(), aqi.getText().toString())){
//                        Toast.makeText(DataActivity.this, "Inserted", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(DataActivity.this, "Not Inserted",  Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    name.setError("Enter  Name");
//                    aqi.setError("Enter AQI");
//                }
//            }
//        });
    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////        cityMenu = spinner.getSelectedItem().toString();
////        if(cityMenu.contains("Beijing")){
//////            APIActivity getData = new APIActivity();
////            getData.execute("https://api.waqi.info/feed/Beijing" + "/?token=dde58930fe41788aa121c1a177000a9b49af7ed9");
////        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        cityMenu = spinner.getSelectedItem().toString();
//        if(cityMenu.contains("Beijing")){
////            APIActivity getData = new APIActivity();
//            getData.execute("https://api.waqi.info/feed/Beijing" + "/?token=dde58930fe41788aa121c1a177000a9b49af7ed9");
//        }
////        switch (cityName) {
////            case "Beijing":
////                cityMenu = "Beijing";
////                APIActivity getData = new APIActivity();
////                getData.execute("https://api.waqi.info/feed/Beijing" + "/?token=dde58930fe41788aa121c1a177000a9b49af7ed9");
////                break;
////
////        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_common, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.Map:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                return true;
            case R.id.Data:
                intent = new Intent(this, DataActivity.class);
                startActivity(intent);
                return true;
            case R.id.AboutUs:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                return true;
            case R.id.ContactUs:
                intent = new Intent(this, ContactUsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickButton(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
