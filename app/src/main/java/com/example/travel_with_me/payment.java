package com.example.travel_with_me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class payment extends AppCompatActivity {

    ImageView image1,image2;
    Button view, more;

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
                case R.id.viewbutton:
                    goToViewTicket();
                    break;
                case R.id.morebutton:
                    goToTicket();
                    break;
            }
        }
    };

    private void goToViewTicket() {
        Intent intent = new Intent(this, book.class);
        startActivity(intent);
    }

    private void goToTicket() {
        Intent intent = new Intent(this, tiket.class);
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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_paymentinfo);

        image1 = (ImageView) findViewById(R.id.home2);
        image1.setOnClickListener(myClickListener);
        image2 = (ImageView) findViewById(R.id.logout2);
        image2.setOnClickListener(myClickListener);
        view = (Button) findViewById(R.id.viewbutton);
        view.setOnClickListener(myClickListener);
        more = (Button) findViewById(R.id.morebutton);
        more.setOnClickListener(myClickListener);
    }
}