package com.neo.spsvegetables;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.neo.spsvegetables.Adapter.CirculerNewsAdapter;
import com.neo.spsvegetables.HttpConnection.HttpHandler;
import com.neo.spsvegetables.Model.Newscirculer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Circulurnews  extends AppCompatActivity{
    RecyclerView recyclerView;
    //a list to store all the products
    List<Newscirculer> newsList;


    String id,na,pa;

    String student_id;
    String student_name;
    String class_sec;
    ArrayList<HashMap<String, Object>> studentlist;
    private DrawerLayout mdrawerlayout;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulurnews);
        //        dbHelper=new DBHelper(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        student_id = intent.getStringExtra("student_id");

/*        Cursor res = dbHelper.GetSQLiteDatabaseRecords();
        while (res.moveToNext()) {
            id = res.getString(0);
            na = res.getString(1);
            pa = res.getString(2);
        }

        Log.e("id",""+id);
        Log.e("employee user",""+na);
        Log.e("employee id",""+pa);*/
        studentlist = new ArrayList<>();
//        lv = (ListView) findViewById(R.id.list);


//        new Getcirculer().execute();



        //initializing the productlist






        //adding some items to our list
/*        newsList.add(
                new Newscirculer(
                        "11.08.2019",
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        "4.3",
                        "https:\\/\\/thenavodayaacademy.edu.in\\/admins\\/news\\/118DSCN1207.JPG",R.drawable.eye
                     ));

        newsList.add(
                new Newscirculer(
                        "13.08.2019",
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 inch, Gray, 1.659 kg",
                        "4.3",
                        "https:\\/\\/thenavodayaacademy.edu.in\\/admins\\/news\\/118DSCN1207.JPG",R.drawable.eye
                        ));

        newsList.add(
                new Newscirculer(
                        "15.08.2019",
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        "4.3",
                        "https:\\/\\/thenavodayaacademy.edu.in\\/admins\\/news\\/118DSCN1207.JPG",R.drawable.eye));*/

 /*       //creating recyclerview adapter
        CirculerNewsAdapter adapter = new CirculerNewsAdapter(this, newsList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);*/




        new Getcirculer().execute();
    }



    private class Getcirculer extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsList = new ArrayList<>();
            progressBar.setVisibility(View.VISIBLE);
//            Toast.makeText(Student_details.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Api api=new Api();
            String urlstring=""+api.NotificationEndpoints();
            // Making a request to url and getting response
            String url = ""+urlstring;
            String jsonStr = sh.makeServiceCall(url);

            Log.e("", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray JA = new JSONArray(jsonStr);

                    // Getting JSON Array node
//                    JSONArray studentsjson = jsonObj.getJSONArray("circular");

                    // looping through All Contacts
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject c = JA.getJSONObject(i);
                        String id = c.getString("id");
                        String color = c.getString("color");
                        String html_link = c.getString("html_link");
                        String date = c.getString("Date");
                        String title = c.getString("title");
                        String circular_message = c.getString("description");
                        String html_title = c.getString("html_title");
                        String image = c.getString("photo");

                        newsList.add(
                                new Newscirculer(
                                        ""+date,
                                        ""+title,
                                        ""+circular_message,
                                        ""+image,
                                        ""+color,
                                        ""+html_title,
                                        ""+html_link,
                                        ""+id
                                ));

                    }
                } catch (final JSONException e) {
                    Log.e("", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                          /*  Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();*/
                        }
                    });

                }

            } else {
                Log.e("", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            CirculerNewsAdapter adapter = new CirculerNewsAdapter(getApplicationContext(),newsList);

            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);

        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true ;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if(mToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//
//
//
//        return super.onOptionsItemSelected(item);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Take appropriate action for each action item click
    switch (item.getItemId()) {
        case R.id.action_refresh:
            new Getcirculer().execute();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}

}