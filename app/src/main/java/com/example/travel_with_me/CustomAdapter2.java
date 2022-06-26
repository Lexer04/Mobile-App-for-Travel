package com.example.travel_with_me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {
    Context c;
    ArrayList id, airline, flightcode, from, destination,
            departuretime, arrivaltime, flighttime, date, seatclass, price, name, passenger, status;
    LayoutInflater inflater;

    public CustomAdapter2 (Context c, ArrayList airline, ArrayList flightcode,
                          ArrayList from, ArrayList destination, ArrayList departuretime,
                          ArrayList arrivaltime, ArrayList flighttime, ArrayList date,
                          ArrayList seatclass, ArrayList price, ArrayList name,
                           ArrayList passenger, ArrayList status) {
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
        this.price = price;
        this.name = name;
        this.passenger = passenger;
        this.status = status;
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
        view = inflater.inflate(R.layout.myticketlv, null);
//        TextView myid = (TextView) view.findViewById(R.id.idnumber);
        TextView myairline = (TextView) view.findViewById(R.id.airlineticket);
        TextView myflightcode = (TextView) view.findViewById(R.id.flightcodeticket);
        TextView myfrom = (TextView) view.findViewById(R.id.fromticket);
        TextView mydestination = (TextView) view.findViewById(R.id.destinationticket);
        TextView mydeparturetime = (TextView) view.findViewById(R.id.departuretimeticket);
        TextView myarrivaltime = (TextView) view.findViewById(R.id.arrivaltimeticket);
        TextView myflighttime = (TextView) view.findViewById(R.id.flighttimeticket);
        TextView mydate = (TextView) view.findViewById(R.id.dateticket);
        TextView myseatclass = (TextView) view.findViewById(R.id.seatclassticket);
        TextView mypassenger = (TextView) view.findViewById(R.id.passengerticket);
        TextView myname = (TextView) view.findViewById(R.id.nameticket);
        TextView mystatus = (TextView) view.findViewById(R.id.statusticket);
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
        mypassenger.setText(String.valueOf("Passenger: "+passenger.get(i)));
        myname.setText(String.valueOf("Name: "+name.get(i)));
        mystatus.setText(String.valueOf("Status: "+status.get(i)));
        return view;
    }
}
