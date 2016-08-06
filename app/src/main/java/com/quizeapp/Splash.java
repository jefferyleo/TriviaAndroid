package com.quizeapp;

import android.content.Intent;
import android.os.Bundle;

import com.quizeapp.custom.CustomActivity;


public class Splash extends CustomActivity {
    /**
     * The Class SplashScreen will launched at the start of the application. It will
     * be displayed for 3 seconds and than finished automatically and it will also
     * start the next activity of app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);
        try {
            Thread timer = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {

                        startActivity(new Intent(getApplicationContext(),
                                Login.class));
                        overridePendingTransition(0, 0);
                        finish();

                    }
                }

            };
            timer.start();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
