package com.example.travel_with_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ticketdetails extends AppCompatActivity {

    String url,airline,flightcode,from,destination,departuretime,arrival,
            date,seatclass,flighttime,price, getprice, status;
    int passenger = 1, total=1;
    ImageView image1,image2;
    TextView airlinetv,flightcodetv,fromtv,destinationtv,
            departuretv,arrivaltv,datetv,seatclasstv,flighttimetv,
            pricetv,passengertv;
    Button button1, buttonplus, buttonminus;
    String profiluser;

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
                case R.id.button:
                    new ReqTask().execute(url + "/Booked_Ticket", "POST");
                    status = "PENDING";
                    goToPayment();
                    break;
                case R.id.button2:
                    passenger = passenger+1;
                    if (passenger > 10) {
                        passenger = 10;
                    }
                    else {
                        total = Integer.parseInt(price) * passenger;
                        passengertv.setText(String.valueOf(passenger));
                        pricetv.setText(String.valueOf(total));
                    }
                    break;
                case R.id.button3:
                    if (passenger < 2) {
                        passenger = 1;
                    }
                    else {
                        passenger = passenger -1;
                        total = Integer.parseInt(price) * passenger;
                        passengertv.setText(String.valueOf(passenger));
                        pricetv.setText(String.valueOf(total));
                    }
                    break;
            }
        }


    };
    private void goToPayment() {
        Intent intent = new Intent(this, payment.class);
        startActivity(intent);
    }

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

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.ticketdetails);
        url = getString(R.string.urlServer);
//        tiketprice = tiket.g;

//        airline = getIntent().getStringExtra(tiket.);
        airlinetv = findViewById(R.id.tvairline);
        flightcodetv= findViewById(R.id.tvflightcode);
        fromtv= findViewById(R.id.tvfrom);
        destinationtv= findViewById(R.id.tvdestination);
        departuretv= findViewById(R.id.tvdeparturetime);
        arrivaltv= findViewById(R.id.tvarrivaltime);
        datetv= findViewById(R.id.tvdate);
        seatclasstv= findViewById(R.id.tvseatclass);
        flighttimetv = findViewById(R.id.tvflighttime);
        pricetv = findViewById(R.id.tvprice);
        passengertv = findViewById(R.id.tvpassenger);


        airline = getIntent().getStringExtra("Airline");
        flightcode = getIntent().getStringExtra("Flight Code");
        from = getIntent().getStringExtra("From");
        destination = getIntent().getStringExtra("Destination");
        departuretime = getIntent().getStringExtra("Departure Time");
        arrival = getIntent().getStringExtra("Arrival Time");
        date = getIntent().getStringExtra("Date");
        seatclass = getIntent().getStringExtra("Seat Class");
        flighttime = getIntent().getStringExtra("Flight Time");
        price = getIntent().getStringExtra("Price");

        airlinetv.setText("Airline : " +airline);
        flightcodetv.setText("Flight Code : " +flightcode);
        fromtv.setText("From : "+from);
        destinationtv.setText("Destination : " +destination);
        departuretv.setText("Departure Time : " +departuretime);
        arrivaltv.setText("Arrival Time : " +arrival);
        datetv.setText("Date : " +date);
        seatclasstv.setText("Seat Class : " +seatclass);
        flighttimetv.setText("Flight Time : " +flighttime);
        pricetv.setText("Ticket Price : " +price);

        pricetv.setText(String.valueOf(price));
        passengertv.setText(String.valueOf(passenger));



        profiluser = login.a;
        image1 = (ImageView) findViewById(R.id.home);
        image1.setOnClickListener(myClickListener);
        image2 = (ImageView) findViewById(R.id.logout);
        image2.setOnClickListener(myClickListener);
        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(myClickListener);
        buttonplus = (Button) findViewById(R.id.button2);
        buttonplus.setOnClickListener(myClickListener);
        buttonminus = (Button) findViewById(R.id.button3);
        buttonminus.setOnClickListener(myClickListener);

        new ReqTask().execute(url + "/Booked_Ticket", "POST");

    }
    public class ReqTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... uri) {
            try {
                URL url = new URL((uri[0]));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //GET Method
//                if (uri[1] == "GET") {
//                    con.setRequestMethod(uri[1]);
//                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String input;
//                    StringBuffer response = new StringBuffer();
//                    while ((input = in.readLine()) != null) {
//                        response.append(input);
//                    }
//                    in.close();
//                    Log.d("Test", String.valueOf(response));
//                    return String.valueOf(response);
//                }
                if (uri[1] == "POST") {
                    con.setRequestMethod((uri[1]));
                    Log.d("test", uri[1]);
                    con.setRequestProperty("Content-type", "application/json");
                    con.setDoOutput(false);
                    con.setDoInput(true);

                    status = "PENDING";
                    JSONObject data = new JSONObject();
                    data.put("Name", profiluser);
                    data.put("Airline", airline);
                    data.put("Flightcode", flightcode);
                    data.put("Departuretime", departuretime);
                    data.put("Arrivaltime", arrival);
                    data.put("From", from);
                    data.put("Destination", destination);
                    data.put("Dateofflight", date);
                    data.put("Seatclass", seatclass);
                    data.put("Flighttime", flighttime);
                    data.put("Price", price);
                    data.put("Passenger", passenger);
                    data.put("Status", status);
                    Log.d("test", data.toString());

                    OutputStream os = new BufferedOutputStream(con.getOutputStream());
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    out.write(data.toString());
                    out.flush();
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String input;
                    while ((input = in.readLine()) != null) {
                        Log.d("test", input);
                    }
                    return "ok";
            }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

//        @Override
//        protected void onPostExecute(String s) {
//            JSONArray myRec = null;
//            try {
//                myRec = new JSONArray(s);
//                for (int i = 0; i < myRec.length(); i++) {
//                    JSONObject myRecObj = myRec.getJSONObject(i);
////                    id.add(myRecObj.getString("FlightID"));
//                    airline.add(myRecObj.getString("Airline"));
//                    flightcode.add(myRecObj.getString("Flight Code"));
//                    from.add(myRecObj.getString("From"));
//                    destination.add(myRecObj.getString("Destination"));
//                    departuretime.add(myRecObj.getString("Departure Time"));
//                    arrivaltime.add(myRecObj.getString("Arrival Time"));
//                    flighttime.add(myRecObj.getString("Flight Time"));
//                    date.add(myRecObj.getString("Date"));
//                    seatclass.add(myRecObj.getString("Seat Class"));
//                    price.add(String.valueOf(myRecObj.getInt("Price")));
////                    g = myRecObj.getInt("Price");
//
//                    CustomAdapter customAdapter = new CustomAdapter(tiket.this, airline, flightcode, from, destination,
//                            departuretime, arrivaltime, flighttime, date, seatclass,price);
//                    mylv.setAdapter(customAdapter);
//
//                    mylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                            Intent intent = new Intent(tiket.this, ticketdetails.class);
//                            intent.putExtra("Airline",airline.get(i));
//                            intent.putExtra("Flight Code",flightcode.get(i));
//                            intent.putExtra("From",from.get(i));
//                            intent.putExtra("Flight Time",flighttime.get(i));
//                            intent.putExtra("Destination",destination.get(i));
//                            intent.putExtra("Departure Time",departuretime.get(i));
//                            intent.putExtra("Arrival Time",arrivaltime.get(i));
//                            intent.putExtra("Date",date.get(i));
//                            intent.putExtra("Seat Class",seatclass.get(i));
//                            intent.putExtra("Price",price.get(i));
//                            startActivity(intent);
//                        }
//                    });
//                    //if (myRecObj.getString("Ticket").equals("Available Tickets")) {
////
////                        mylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                            @Override
////                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                                Intent intent = new Intent(tiket.this, ticketdetails.class);
////                                startActivity(intent);
////                            }
////                        });
//                    }
//                //}
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
    }


}