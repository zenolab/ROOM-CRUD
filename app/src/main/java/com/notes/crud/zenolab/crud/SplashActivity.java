package com.notes.crud.zenolab.crud;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private float imageAlpha = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashScreenUseTimer(1700);

    }


    // Show splash screen in fixed time.
    private void splashScreenUseTimer(long milliSeconds) {
        // Get splash image view object.
        final ImageView splashImageView = (ImageView) findViewById(R.id.splashImage);

        // Create a count down timer object.It will count down every 0.1 seconds and last for milliSeconds milliseconds..
        CountDownTimer countDownTimer = new CountDownTimer(milliSeconds, 100) {
            @Override
            public void onTick(long l) {
                // Reduce the splash screen background image's alpha value for each count down.
                splashImageView.setAlpha(imageAlpha);
                imageAlpha -= 0.1;

                if (imageAlpha <= 0) {
                    imageAlpha = 1;
                }
            }

            @Override
            public void onFinish() {
                // When count down complete, set the image to invisible.
                imageAlpha = 0;
                splashImageView.setAlpha(imageAlpha);
            }
        };
        // Start the count down timer.
        countDownTimer.start();

        // Create a new Handler object.
        Handler splashScreenHandler = new Handler();
        // Create a thread object.
        Runnable splashScreenThread = new Runnable() {
            @Override
            public void run() {
                // Start SplashScreenActivity.
                Intent mainIntent = new Intent(SplashActivity.this,
                        MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                // Close SplashScreenActivity.
                SplashActivity.this.finish();
            }
        };
        // Execute splashScreenThread after 5 seconds.
        splashScreenHandler.postDelayed(splashScreenThread, milliSeconds);
    }

}
