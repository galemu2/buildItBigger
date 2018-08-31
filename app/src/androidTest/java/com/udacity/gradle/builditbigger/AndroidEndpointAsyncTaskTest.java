package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AndroidEndpointAsyncTaskTest {

    private String joke;

    @Before
    public void initializeAsyncTask() {
        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask();
        endpointAsyncTask.execute();

        //Do while loop used to wait until background task is complete
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (endpointAsyncTask.getStatus() == AsyncTask.Status.RUNNING);

        /**
         * Source:https://stackoverflow.com/a/43580594/7504259
         * Name:  IntelliJ Amiya
         * Date:  Apr 24 '17
         * */
        if (endpointAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            joke = EndpointAsyncTask.getNewJoke();
        }
    }

    @Test
    public void verifyAsyncTaskReturnNotNull() {
        assertNotNull(joke);
    }

    @Test
    public void verifyAsyncTaskReturnNotEmpty() {
        assertTrue(!joke.isEmpty());
    }


}
