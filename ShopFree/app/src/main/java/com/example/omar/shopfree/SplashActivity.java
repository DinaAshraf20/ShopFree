package com.example.omar.shopfree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.omar.shopfree.R;
import com.example.omar.shopfree.login.LoginActivity;


public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                // Toast.makeText(SplashActivity.this, " SPLASH " , Toast.LENGTH_LONG).show();
                SplashActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
