package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import in.net.codestar.androlibrary.JokeActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        EndPointsAsyncTask task = new EndPointsAsyncTask();
        task.setListener(new MyJokeTaskListener()).execute(this);
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
}
