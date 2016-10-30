package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import in.net.codestar.androlibrary.JokeActivity;

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                EndPointsAsyncTask task = new EndPointsAsyncTask();
                task.setListener(new MyJokeTaskListener()).execute(MainActivity.this);
            }
        });

        requestNewInterstitial();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        else {
            EndPointsAsyncTask task = new EndPointsAsyncTask();
            task.setListener(new MyJokeTaskListener()).execute(MainActivity.this);
        }
    }

    private class MyJokeTaskListener implements EndPointsAsyncTask.GetJokeTaskListener {
        @Override
        public void onComplete(String jokeStr) {
            mProgressBar.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(MainActivity.this, JokeActivity.class);
            intent.putExtra("joke_string", jokeStr);
            startActivity(intent);
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
