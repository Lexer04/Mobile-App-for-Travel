package com.example.travel_with_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class tiket extends AppCompatActivity {

    ImageView image1, image2;
    String url;
    ArrayList<String> id, airline, flightcode, from, destination, departuretime, arrivaltime, flighttime, date, seatclass;
    ListView mylv;

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.home:
                    goToHome();
                    break;
                case R.id.logout:
                    goToLogout();
                    break;
            }
        }
    };

    private void goToHome() {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    private void goToLogout() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket);

        image1 = (ImageView) findViewById(R.id.home);
        image1.setOnClickListener(myClickListener);
        image2 = (ImageView) findViewById(R.id.logout);
        image2.setOnClickListener(myClickListener);

//        View view = inflater.inflate(R.layout.tiket, container, false);
        url = getString(R.string.urlServer);

        id = new ArrayList<>();
        airline = new ArrayList<>();
        flightcode = new ArrayList<>();
        from = new ArrayList<>();
        destination = new ArrayList<>();
        departuretime = new ArrayList<>();
        arrivaltime = new ArrayList<>();
        flighttime = new ArrayList<>();
        date = new ArrayList<>();
        seatclass = new ArrayList<>();
        mylv = (ListView) findViewById(R.id.mylistview);

        new ReqTask().execute(url + "/api/ticket", "GET");

    }

    public class ReqTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... uri) {
            try {
                URL url = new URL((uri[0]));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //GET Method
                if (uri[1] == "GET") {
                    con.setRequestMethod(uri[1]);
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String input;
                    StringBuffer response = new StringBuffer();
                    while ((input = in.readLine()) != null) {
                        response.append(input);
                    }
                    in.close();
                    Log.d("Test", String.valueOf(response));
                    return String.valueOf(response);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONArray myRec = null;
            try {
                myRec = new JSONArray(s);
                for (int i = 0; i < myRec.length(); i++) {
                    JSONObject myRecObj = myRec.getJSONObject(i);
//                    id.add(myRecObj.getString("FlightID"));
                    airline.add(myRecObj.getString("Airline"));
                    flightcode.add(myRecObj.getString("Flight Code"));
                    from.add(myRecObj.getString("From"));
                    destination.add(myRecObj.getString("Destination"));
                    departuretime.add(myRecObj.getString("Departure Time"));
                    arrivaltime.add(myRecObj.getString("Arrival Time"));
                    flighttime.add(myRecObj.getString("Flight Time"));
                    date.add(myRecObj.getString("Date"));
                    seatclass.add(myRecObj.getString("Seat Class"));
                    CustomAdapter customAdapter = new CustomAdapter(tiket.this, airline, flightcode, from, destination,
                            departuretime, arrivaltime, flighttime, date, seatclass);
                    mylv.setAdapter(customAdapter);
                    //if (myRecObj.getString("Ticket").equals("Available Tickets")) {
//
//                        mylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                Intent intent = new Intent(tiket.this, ticketdetails.class);
//                                startActivity(intent);
//                            }
//                        });
                    }
                //}
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}