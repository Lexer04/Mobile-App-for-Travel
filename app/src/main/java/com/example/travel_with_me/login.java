package com.example.travel_with_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class login extends AppCompatActivity {

    Button b1;
    EditText username, pass;
    String url;
    DBHandler mydb;
    DataModel usermodel;
    public String user, password;
    public static String a, b, c;

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login:
//                    goTohome();
                    user = username.getText().toString();
                    password = pass.getText().toString();
                    String sendUrl;
                    if (user.equals("") | password.equals("")) {
                        Log.d("URL 1", "Kosong");
                    } else {
                        sendUrl = "http://172.22.0.228:8000/user/" + user;
                        Log.d("URL 2", "TES");
                        Log.d("URL 2", sendUrl);
                        new ReqTask().execute(sendUrl, "GET");
                    }
                    break;
            }
        }
    };

    private void goTohome() {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    private void checkpass() {

        if (user.equals(a) && password.equals(b)) {
            Toast.makeText(getApplicationContext(), "Welcome to travelwithme.com " + a, Toast.LENGTH_LONG).show();
            goTohome();

        } else {

            Toast.makeText(getApplicationContext(), "incorrect username or password", Toast.LENGTH_SHORT).show();
            username.setText("");
            pass.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        url = "http://192.168.1.34:8000";
        b1 = (Button) findViewById(R.id.login);
        b1.setOnClickListener(myClickListener);

        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);

        mydb = new DBHandler(this);
//        getData();
    }

    class ReqTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL((strings[0]));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(strings[1]);
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                if (strings[1] == "GET") {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String input;
                    StringBuffer response = new StringBuffer();
                    while ((input = in.readLine()) != null) {
                        response.append(input);
                    }
                    in.close();
                    return String.valueOf(response);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s) {
            JSONArray DataArray = null;
            try {
                DataArray = new JSONArray(s);
                JSONObject DataObj = DataArray.getJSONObject(0);
                a = DataObj.getString("username");
                b = DataObj.getString("password");
                checkpass();
            } catch (JSONException e) {
                checkpass();
                e.printStackTrace();
            }
        }
    }
}