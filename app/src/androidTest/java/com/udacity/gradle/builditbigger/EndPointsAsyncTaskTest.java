package com.udacity.gradle.builditbigger;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

/**
 * Created by Salil on 22-10-2016.
 */
public class EndPointsAsyncTaskTest {
    private final Context context = getInstrumentation().getTargetContext();
    private String mJokeStr = null;
    private CountDownLatch signal = null;

    @Before
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @After
    public void tearDown() throws Exception {
        signal.countDown();
    }

    @Test
    public void testResult() throws InterruptedException {
        EndPointsAsyncTask task = new EndPointsAsyncTask();
        task.setListener(new EndPointsAsyncTask.GetJokeTaskListener() {
            @Override
            public void onComplete(String jokeStr) {
                mJokeStr = jokeStr;
                signal.countDown();
            }
        }).execute(context);

        signal.await(10, TimeUnit.SECONDS);
        assertTrue(mJokeStr != null && !mJokeStr.equals("") && !mJokeStr.contains("failed"));
    }
}