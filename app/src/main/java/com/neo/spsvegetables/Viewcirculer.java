package com.neo.spsvegetables;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Viewcirculer extends AppCompatActivity {
    ImageView image;
    TextView date,subtitle,title,link,time,location;
    View customLayout;
    String imagestr,datestr,subtitlestr,titlestr,linkstr,timeStr,locationstr;

//    List<Newscirculer> newsList;

/*    private final String[] imageArray = {

            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3213173117,1110903080&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1781594915,1366698269&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1891652328,4280900176&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3117198308,3734342397&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1817262769,1722663763&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2226962572,1331736450&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3215007186,1730866118&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4002052629,2620520156&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2185637402,3767956099&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3193260445,3308495828&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4233973082,2791353980&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3044237662,1661917652&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3884460269,2452415434&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2459592104,964883207&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4126378158,4237107889&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1955835928,3080371141&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2243141774,3186970935&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3103859432,2822357524&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3430323032,501802113&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=398455838,1385467344&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4257076965,4140046292&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3660528120,2395718823&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1500720353,1939090614&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=120988605,2488551742&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1370761971,3887646162&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2713856587,1798489161&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2701265338,3973700775&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=982226408,3400592817&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2908505488,43474043&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2849873365,366228852&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3370231397,129028169&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3268359587,3340996830&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1938366643,1751177362&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3659898425,2948980918&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2483415675,2226885028&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2714053634,2951928462&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2980990027,259361045&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1224904108,93581485&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=800274749,3560269987&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2158332137,3914260445&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=934278520,495630521&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1347002449,569728833&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1804686124,2817435486&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=10380796,1038230589&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=326844657,168862954&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=559280242,3629937094&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=42234963,3359794470&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3691557634,3533793386&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2366521412,4187387997&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2272021988,2600909759&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2117720982,169686863&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1673698395,2662990695&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1395642137,81965921&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1077412268,1486449152&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2774420294,2604280244&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3982795986,3289528383&fm=26&gp=0.jpg",
            "http://ca.neoinfotech.com/empimages/16 09 2019.png",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1152354189,4075004834&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1241938828,3177192306&fm=26&gp=0.jpg"};*/
//    private CardViewPager viewPager;

    private boolean isCard = true;
    private ImageView mainBG;
//    private CardSlideView<MyBean> slideView;
//    private List<MyBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcirculer);
        image=findViewById(R.id.image);
        date=findViewById(R.id.date);
        subtitle=findViewById(R.id.subtitle);
        title=findViewById(R.id.title);
        link=findViewById(R.id.link);
        time=findViewById(R.id.time);
        location=findViewById(R.id.locationtext);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        imagestr = intent.getStringExtra("image");
        datestr = intent.getStringExtra("date");
        subtitlestr = intent.getStringExtra("subtitle");
        titlestr = intent.getStringExtra("student_title");
        linkstr = intent.getStringExtra("link");
        timeStr=intent.getStringExtra("time");
        locationstr=intent.getStringExtra("location");



        Picasso.get().load(""+imagestr).into(image);
        date.setText(datestr);
        title.setText(titlestr);
        subtitle.setText(linkstr);
        link.setText(linkstr);
        time.setText("\u20B9"+timeStr);
        location.setText(subtitlestr);


//        slideView = findViewById(R.id.slide_view);

//        new Getcirculer().execute();
    }
  /*  private class Getcirculer extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsList = new ArrayList<>();
            list = new ArrayList<MyBean>();
//            Toast.makeText(Student_details.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Api api=new Api();
            String urlstring=""+api.NotificationimageEndpoints(linkstr);
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

                        String image = c.getString("image");

//                        list.add(new MyBean(image));



                    }
                } catch (final JSONException e) {
                    Log.e("", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                         *//*   Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();*//*
                        }
                    });

                }

            } else {
                Log.e("", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                     *//*   Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();*//*
                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
       *//*     slideView.setOnPageChangeListener(new MyPageChangeListener());
            Log.e("MainActivity", "list.size():" + list.size());
            slideView.bind(list, new MyCardHolder());
*//*
        }
    }
//    class MyPageChangeListener implements OnPageChangeListener {
//
//        @Override
//        public void onPageSelected(int position) {
//            MyBean data = list.get(position);
//            Log.e("MainActivity", "onPageSelected" + "position:" + position);
//            Glide.with(Viewcirculer.this)
//                    .load(data.getImg())
//                    .apply(new RequestOptions()
//                            .transform(new BlurTransformation(5)))
//                    .into(new SimpleTarget<Drawable>() {
//                        @Override
//                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
////                            mainBG.setImageDrawable(resource);
//                        }
//                    });
//        }
//    }*/


/*

    static class MyCardHolder implements CardHolder<MyBean> {
        View imagelayout;
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
            imagelayout=inflater.inflate(R.layout.item, container, false);
            return imagelayout;
        }

        @Override
        public void onBindView(@NonNull CardViewHolder holder, MyBean data, int position) {
            Log.e("MainActivity", "onBindView---data:" + data + "position:" + position);
            ImageView imageView = holder.getView(R.id.image);
            final String img = data.getImg();
            Glide.with(imageView.getContext()).load(img).apply(new RequestOptions().dontAnimate()).into(imageView);
            holder.itemView.setOnClickListener(v -> {
                Log.e("MainActivity", "setOnClickListener---data:" + data + "position:" + position);
//                TestActivity.start(v.getContext(), img);
                showAlertDialogButtonClicked(imagelayout,img);
            });
        }


    }

    static class MyScale implements PageTransformer {

        @Override
        public void transformPage(@NonNull View view, float offsetPercent, int orientation) {
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
    }

    public void onClick(View view) {
//        int currentItem = slideView.getCurrentItem();
//        int orientation = slideView.getOrientation();
        slideView.setItemTransformer(new MyScale());
        slideView.setOrientation(CardSlideView.VERTICAL);
        slideView.setSnapHelper(new CardLinearSnapHelper());
        slideView.setLooper(true);
        slideView.bind(list, new CardHolder<MyBean>() {
            @Override
            public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
                return inflater.inflate(R.layout.item, container, false);
            }

            @Override
            public void onBindView(@NonNull CardViewHolder holder, MyBean data, int position) {
                Log.e("MainActivity", "onBindView---data:" + data + "position:" + position);
                ImageView imageView = holder.getView(R.id.image);
                final String img = data.getImg();
                Glide.with(imageView.getContext()).load(img).apply(new RequestOptions().dontAnimate()).into(imageView);
                holder.itemView.setOnClickListener(v -> {
                    Log.e("MainActivity", "setOnClickListener---data:" + data + "position:" + position);
                    TestActivity.start(v.getContext(), img);
                });
            }
        });
    }
*/

    public static void showAlertDialogButtonClicked(View view, String img) {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//        builder.setTitle("Attendance Details");
        // set the custom layout
        View customLayout =LayoutInflater.from(view.getContext()).inflate(R.layout.image, null);
        builder.setView(customLayout);

        ImageView imageView=customLayout.findViewById(R.id.viewimage);

        Picasso.get().load(""+img).into(imageView);
        // add a button
        /*builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             *//*   // send data from the AlertDialog to the Activity
                EditText editText = customLayout.findViewById(R.id.editText);
                sendDialogDataToActivity(editText.getText().toString());*//*
                dialog.dismiss();
            }
        });*/
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
