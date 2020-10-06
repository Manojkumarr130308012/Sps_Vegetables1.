package com.neo.spsvegetables;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.neo.spsvegetables.Adapter.RatesAdapter;
import com.neo.spsvegetables.HttpConnection.HttpHandler;
import com.neo.spsvegetables.Model.Newscirculer;
import com.neo.spsvegetables.Model.Ratesmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rates extends AppCompatActivity implements RecyclerView.OnItemTouchListener{
   /* private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mToggle;*/
    private RatesAdapter adapter;
    private List<Ratesmodel> ratesmodels;
    View customLayout;
    RecyclerView recyclerView;
//    List<Newscirculer> newsList;
String queery;
    TextView date;

    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> Timetablelist;
    ArrayList<HashMap<String, String>> search_result_arraylist;
TableLayout stk;

    String dateupdate;
     String keyword;
    private ProgressBar progressBar;
 ImageView reload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        Timetablelist=new ArrayList<>();
        search_result_arraylist = new ArrayList<>();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        //        dbHelper=new DBHelper(this);
     /*   mdrawerlayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mToggle=new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();*/

    /*    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);*/
//        fillExampleList();
//        setUpRecyclerView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        date=findViewById(R.id.date);


        new getdate().execute();
        new getCommity().execute();

/*
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getdate().execute();
                new getCommity().execute();
            }
        });
*/


    }




    private class getdate extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ratesmodels = new ArrayList<>();
            progressBar.setVisibility(View.VISIBLE);
//            Toast.makeText(Student_details.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Api api=new Api();
            String urlstring=""+api.updatedate();
            // Making a request to url and getting response
            String url = ""+urlstring;
            String jsonStr = sh.makeServiceCall(url);

            Log.e("", "Response from url: " + jsonStr);
            if (jsonStr != null) {


                    dateupdate=jsonStr;


            } else {
                Log.e("", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                     /*   Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();*/
                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            date.setText(""+dateupdate);

        }
    }




    private class getCommity extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Timetablelist=new ArrayList<>();
            Timetablelist.clear();
//            Toast.makeText(Student_details.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Api api=new Api();
            String urlstring=""+api.Rates();
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
                        String product_name = c.getString("product_name");
                        String code = c.getString("code");
                        String rate = c.getString("rate");
                        String market_price = c.getString("market_price");
                      hashMap=new HashMap<String, String>();
                        hashMap.put("product_name",""+product_name);
                        hashMap.put("code",""+code);
                        hashMap.put("rate",""+rate);
                        hashMap.put("market_price",""+market_price);

                        Timetablelist.add(hashMap);


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

                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            Log.e("fffffffffffffffff","fffffffffffffff");

            stk = findViewById(R.id.table_main);
            // Clear Previous Data
            stk.removeAllViewsInLayout();


            final TableRow tbrow0 = new TableRow(getApplicationContext());


            final TextView tv1 = new TextView(getApplicationContext());
            tv1.setText("           Items          ");
            tv1.setTypeface(null, Typeface.BOLD);
            tv1.setPadding(5, 5, 5, 5);
            tv1.setGravity(Gravity.CENTER);
            tv1.setBackgroundResource(R.drawable.cell_shape);
//            tv1.setBackgroundColor(getResources().getColor(R.color.md_green_900));
            tv1.setTextColor(getResources().getColor(R.color.black));
            tbrow0.addView(tv1);


            final TextView tv2 = new TextView(getApplicationContext());
            tv2.setText("        Rate / Unit        ");
            tv2.setTypeface(null, Typeface.BOLD);
            tv2.setPadding(5, 5, 5, 5);
            tv2.setGravity(Gravity.CENTER);
            tv2.setBackgroundResource(R.drawable.cell_shape);
//            tv2.setBackgroundColor(getResources().getColor(R.color.red_dark));
            tv2.setTextColor(getResources().getColor(R.color.black));
            tbrow0.addView(tv2);


            final TextView tv4 = new TextView(getApplicationContext());
            tv4.setText("        Market Price         ");
            tv4.setTypeface(null, Typeface.BOLD);
            tv4.setPadding(5, 5, 5, 5);
            tv4.setGravity(Gravity.CENTER);
            tv4.setBackgroundResource(R.drawable.cell_shape);
//            tv4.setBackgroundColor(getResources().getColor(R.color.yellow));
//      tv4  .setTextColor(Color.WHITE);
            tv4.setTextColor(getResources().getColor(R.color.black));
            tbrow0.addView(tv4);
            stk.addView(tbrow0);
            Log.e("compain",""+Timetablelist.size());
            for (int i=0;i<Timetablelist.size();i++){

                TableRow tbrow = new TableRow(getApplicationContext());



                TextView t4v = new TextView(getApplicationContext());
                t4v.setText(""+Timetablelist.get(i).get("product_name"));
                t4v.setPadding(5, 5, 5, 5);
                t4v.setBackgroundResource(R.drawable.cell_shape);
                t4v.setTextColor(Color.BLACK);
                t4v.setGravity(Gravity.CENTER);
                tbrow.addView(t4v);

                TextView t2v = new TextView(getApplicationContext());
                t2v.setText(""+Timetablelist.get(i).get("rate")+"/"+Timetablelist.get(i).get("code"));
                t2v.setPadding(5, 5, 5, 5);
                t2v.setBackgroundResource(R.drawable.cell_shape);
                t2v.setTextColor(Color.BLACK);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);



                TextView t3v = new TextView(getApplicationContext());
                t3v.setText(""+Timetablelist.get(i).get("market_price")+"/"+Timetablelist.get(i).get("code"));
                t3v.setPadding(5, 5, 5, 5);
                t3v.setBackgroundResource(R.drawable.cell_shape);
                t3v.setTextColor(Color.BLACK);
                t3v.setGravity(Gravity.CENTER);
                tbrow.addView(t3v);
                stk.addView(tbrow);
                progressBar.setVisibility(View.GONE);
            }

        }
    }



    private class getsearch extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.GONE);
            Timetablelist=new ArrayList<>();
            Timetablelist.clear();
            Log.e("12345676","preeeeeeeeeee");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Api api=new Api();
            String urlstring=""+api.Ratessearch(queery);
            // Making a request to url and getting response
            String url = ""+urlstring;
            String jsonStr = sh.makeServiceCall(url);

            Log.e("", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray JA = new JSONArray(jsonStr);

                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject c = JA.getJSONObject(i);
                        String product_name = c.getString("product_name");
                        String code = c.getString("code");
                       String rate = c.getString("rate");
                      String market_price = c.getString("market_price");
                        hashMap=new HashMap<String, String>();
                        hashMap.put("product_name",""+product_name);
                        hashMap.put("code",""+code);
                        hashMap.put("rate",""+rate);
                        hashMap.put("market_price",""+market_price);
                        Timetablelist.add(hashMap);

                    }

                    Log.e("sssssearchsssssssss",""+Timetablelist.size());
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

                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tablelayout();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setFocusable(false);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.equals("")){
                    new getCommity().execute();
                }else{
                    Timetablelist.clear();
                    queery=query;
                    new getsearch().execute();
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
/*
                Log.e("keyword",""+newText);
                queery=newText;
                new getsearch().execute();*/

                return false;
            }
        });
        return true;
    }



    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public void tablelayout(){

        Log.e("fffffffffffffffff","fffffffffffffff");

        stk = findViewById(R.id.table_main);
        // Clear Previous Data
        stk.removeAllViewsInLayout();


        final TableRow tbrow0 = new TableRow(getApplicationContext());


        final TextView tv1 = new TextView(getApplicationContext());
        tv1.setText("           Items          ");
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(Gravity.CENTER);
        tv1.setBackgroundResource(R.drawable.cell_shape);
//            tv1.setBackgroundColor(getResources().getColor(R.color.md_green_900));
        tv1.setTextColor(getResources().getColor(R.color.black));
        tbrow0.addView(tv1);


        final TextView tv2 = new TextView(getApplicationContext());
        tv2.setText("        Rate / Unit        ");
        tv2.setTypeface(null, Typeface.BOLD);
        tv2.setPadding(5, 5, 5, 5);
        tv2.setGravity(Gravity.CENTER);
        tv2.setBackgroundResource(R.drawable.cell_shape);
//            tv2.setBackgroundColor(getResources().getColor(R.color.red_dark));
        tv2.setTextColor(getResources().getColor(R.color.black));
        tbrow0.addView(tv2);


        final TextView tv4 = new TextView(getApplicationContext());
        tv4.setText("        Market Price         ");
        tv4.setTypeface(null, Typeface.BOLD);
        tv4.setPadding(5, 5, 5, 5);
        tv4.setGravity(Gravity.CENTER);
        tv4.setBackgroundResource(R.drawable.cell_shape);
//            tv4.setBackgroundColor(getResources().getColor(R.color.yellow));
//      tv4  .setTextColor(Color.WHITE);
        tv4.setTextColor(getResources().getColor(R.color.black));
        tbrow0.addView(tv4);
        stk.addView(tbrow0);
        Log.e("compain",""+Timetablelist.size());
        for (int i=0;i<Timetablelist.size();i++){

            TableRow tbrow = new TableRow(getApplicationContext());



            TextView t4v = new TextView(getApplicationContext());
            t4v.setText(""+Timetablelist.get(i).get("product_name"));
            t4v.setPadding(5, 5, 5, 5);
            t4v.setBackgroundResource(R.drawable.cell_shape);
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);

            TextView t2v = new TextView(getApplicationContext());
            t2v.setText(""+Timetablelist.get(i).get("rate")+"/"+Timetablelist.get(i).get("code"));
            t2v.setPadding(5, 5, 5, 5);
            t2v.setBackgroundResource(R.drawable.cell_shape);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);



            TextView t3v = new TextView(getApplicationContext());
            t3v.setText(""+Timetablelist.get(i).get("market_price")+"/"+Timetablelist.get(i).get("code"));
            t3v.setPadding(5, 5, 5, 5);
            t3v.setBackgroundResource(R.drawable.cell_shape);
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            stk.addView(tbrow);
            progressBar.setVisibility(View.GONE);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_refresh:
                new getdate().execute();
                new getCommity().execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}




