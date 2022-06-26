package com.example.travel_with_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class home extends AppCompatActivity {

    ImageView image1,image2,image3,image4;

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tickets:
                    goToTicket();
                    break;
                case R.id.book:
                    goToBook();
                    break;
                case R.id.logout:
                    goToLogout();
                    break;
//                case R.id.mytic:
//                    gotToMyTicket();
//                    break;
            }
        }
    };

    private void goToBook() {
        Intent intent = new Intent(this, book.class);
        startActivity(intent);
    }

    private void goToTicket() {
        Intent intent = new Intent(this, tiket.class);
        startActivity(intent);
    }

    private void goToLogout() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    private void gotToMyTicket() {
        Intent intent = new Intent(this, my_ticket.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.home);

        image1 = (ImageView) findViewById(R.id.tickets);
        image1.setOnClickListener(myClickListener);
        image2 = (ImageView) findViewById(R.id.book);
        image2.setOnClickListener(myClickListener);
        image3 = (ImageView) findViewById(R.id.logout);
        image3.setOnClickListener(myClickListener);
//        image4 = (ImageView) findViewById(R.id.mytic);
//        image4.setOnClickListener(myClickListener);
    }
}