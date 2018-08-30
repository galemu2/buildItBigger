package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AndroidEndpointAsyncTaskTest {

    private String jokeVal = "this is a private joke";

    @Before
    public void initializeAsyncTask() {
        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask();
        endpointAsyncTask.execute();
    }

    @Test
    public void verifyAsyncTaskResponse() {
        String joke = EndpointAsyncTask.getNewJoke();
        assert joke.equals(jokeVal);
    }
}
