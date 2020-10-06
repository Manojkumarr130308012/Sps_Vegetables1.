package com.neo.spsvegetables;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.neo.spsvegetables.Config.DBHelper;
import com.neo.spsvegetables.HttpConnection.HttpHandler;
import com.neo.spsvegetables.Model.Constants;
import com.neo.spsvegetables.Model.MyNotificationManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TODO = "";
    EditText email;
    EditText pass;
    ImageView wolf;
    TextView signin;
    TextView Create;
    String checkusername;
    String checkpassword;
View customLayout;
    String Storeuser;
    String Mobile;
    String message;
    String IMEI_Number_Holder;
    TelephonyManager telephonyManager;
    DBHelper dbHelper;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        dbHelper=new DBHelper(this);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        wolf = (ImageView) findViewById(R.id.wolf);
        signin = (TextView) findViewById(R.id.signin);
        Create = (TextView) findViewById(R.id.Create);
Create.setVisibility(View.GONE);
        // TODO Auto-generated method stub
        showAlertDialogButtonClicked();


        /*
         * Displaying a notification locally
         */
//        MyNotificationManager.getInstance(this).displayNotification("SPS Vegtables", "New Offer Is Avaliable");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


     /*           Intent i=new Intent(MainActivity.this,Home.class);
                startActivity(i);
*/

                checkusername = email.getText().toString();
                checkpassword = pass.getText().toString();
                //validate form
                if(validateLogin(checkusername, checkpassword)){
                    //do loginhj

                    Log.e("ffffffffffffffff",""+checkusername);
                    Log.e("ffffffffffffffff",""+checkpassword);

                    fetchData fetchData = new fetchData();
                    fetchData.execute();

                }
            }
        });

    }

    public void showAlertDialogButtonClicked() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Attendance Details");
        // set the custom layout
        LayoutInflater inflater = getLayoutInflater();
        customLayout =  inflater.inflate(R.layout.unique_id, null);
        builder.setView(customLayout);
        TextView no=customLayout.findViewById(R.id.no);
        IMEI_Number_Holder = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        no.setText(""+IMEI_Number_Holder);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {
        String data = "";
        String dataParsed = "";
        String singleParsed = "";


        @Override
        protected void onPreExecute() {
//            pbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (checkusername == null || checkpassword == null) {

//                Toast.makeText(LoginActivity.this, " Fill the Fields", Toast.LENGTH_SHORT).show();

            } else {

                try {

                    URL url = new URL("https://neophrontech.com/spsvegetables.com/admin/login.php?username="+checkusername+"&password="+checkpassword+"&uuid="+IMEI_Number_Holder);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while (line != null) {
                        line = bufferedReader.readLine();
                        data = data + line;
                    }

                    Log.e("dddddddddddddd", "" + data);
                    JSONObject jsonobj = new JSONObject(data);


                    singleParsed = (String) jsonobj.get("login_name");
                    Storeuser = (String) jsonobj.get("login_id");
//                    Mobile=(String) jsonobj.get("login_mobile");
                    message = (String) jsonobj.get("type");
                    Log.e("ddddddddd", "" + singleParsed);
                    Log.e("ddddddddd", "" + Storeuser);
                    Log.e("ddddddddd", "" + message);
                    dataParsed = dataParsed + singleParsed + "\n";
                    if (message.equals("1")) {
//                        pbar.setVisibility(View.INVISIBLE);
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
                            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
                            mChannel.enableLights(true);
                            mChannel.setLightColor(Color.RED);
                            mChannel.enableVibration(true);
                            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                            mNotificationManager.createNotificationChannel(mChannel);
                            token = FirebaseInstanceId.getInstance().getToken();
                            Log.e("ddddddddddddddddddddddd",""+token);
                        }
                        dbHelper.insertData(Storeuser,singleParsed);
                        Intent i=new Intent(MainActivity.this,Home.class);
                        i.putExtra("user",""+Storeuser);
                        startActivity(i);

                    }else if (message.equals("0")){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "InValid User" , Toast.LENGTH_LONG).show();
                            }
                        });                    }



                } catch (MalformedURLException e) {
                    e.printStackTrace();
//                    WriteFile(e);
                } catch (IOException e) {
                    e.printStackTrace();
//                    WriteFile(e);
                } catch (JSONException e) {
                    e.printStackTrace();
//                    WriteFile(e);
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (message.equals("1")){
                Getpushnoti getpushnoti=new Getpushnoti();
                getpushnoti.execute();
            }

        }
    }

    private class Getpushnoti extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
//To do//
                                return;
                            }

// Get the Instance ID token//
                            token = task.getResult().getToken();

                        }
                    });
//            Toast.makeText(Student_details.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            Api api=new Api();
            String urlstring=""+api.Pushnoti(token,IMEI_Number_Holder);
            // Making a request to url and getting response
            String url = ""+urlstring;
            String jsonStr = sh.makeServiceCall(url);

            Log.e("", "Response from url: " + jsonStr);
            if (jsonStr != null) {





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



        }
    }


}
