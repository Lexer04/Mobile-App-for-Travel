package com.example.travel_with_me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter{

    Context c;
    ArrayList id, airline, flightcode, from, destination,
            departuretime, arrivaltime, flighttime, date, seatclass;
    LayoutInflater inflater;

    public CustomAdapter (Context c, ArrayList airline, ArrayList flightcode,
                          ArrayList from, ArrayList destination, ArrayList departuretime,
                          ArrayList arrivaltime, ArrayList flighttime, ArrayList date,
                          ArrayList seatclass) {
        this.c = c;
//        this.id = id;
        this.airline = airline;
        this.flightcode = flightcode;
        this.from = from;
        this.destination = destination;
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
        this.flighttime = flighttime;
        this.date = date;
        this.seatclass = seatclass;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return airline.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.mylvelement, null);
//        TextView myid = (TextView) view.findViewById(R.id.idnumber);
        TextView myairline = (TextView) view.findViewById(R.id.tvairline);
        TextView myflightcode = (TextView) view.findViewById(R.id.tvflightcode);
        TextView myfrom = (TextView) view.findViewById(R.id.tvfrom);
        TextView mydestination = (TextView) view.findViewById(R.id.tvdestination);
        TextView mydeparturetime = (TextView) view.findViewById(R.id.tvdeparturetime);
        TextView myarrivaltime = (TextView) view.findViewById(R.id.tvarrivaltime);
        TextView myflighttime = (TextView) view.findViewById(R.id.tvflighttime);
        TextView mydate = (TextView) view.findViewById(R.id.tvdate);
        TextView myseatclass = (TextView) view.findViewById(R.id.tvseatclass);
//        myid.setText(String.valueOf(id.get(i)));
        myairline.setText(String.valueOf("Airline: "+airline.get(i)));
        myflightcode.setText(String.valueOf("Flight Code: "+flightcode.get(i)));
        myfrom.setText(String.valueOf("From: "+from.get(i)));
        mydestination.setText(String.valueOf("Destination: "+destination.get(i)));
        mydeparturetime.setText(String.valueOf("Departure Time: "+departuretime.get(i)));
        myarrivaltime.setText(String.valueOf("Arrival Time: "+arrivaltime.get(i)));
        myflighttime.setText(String.valueOf("Flight Time: "+flighttime.get(i)));
        mydate.setText(String.valueOf("Date: "+date.get(i)));
        myseatclass.setText(String.valueOf("Seat Class: "+seatclass.get(i)));
        return view;
    }

}

