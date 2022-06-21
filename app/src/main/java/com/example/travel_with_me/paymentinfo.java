package com.example.travel_with_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class paymentinfo extends AppCompatActivity {


    ImageView image1,image2;

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
        setContentView(R.layout.paymentinfo);

        image1 = (ImageView) findViewById(R.id.home);
        image1.setOnClickListener(myClickListener);
        image2 = (ImageView) findViewById(R.id.logout);
        image2.setOnClickListener(myClickListener);

    }
}