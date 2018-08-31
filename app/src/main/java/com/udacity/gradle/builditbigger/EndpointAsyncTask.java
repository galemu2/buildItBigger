package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {

    public static String newJoke ="";


    public EndpointAsyncTask( ) {
        buildApiService();


     }

    private static final String TAG = EndpointAsyncTask.class.getSimpleName();
    private static MyApi myApiService = null;

    private static final String ROOT_URL = "http://10.0.2.2:8080/_ah/api";

    @Override
    protected String doInBackground(Void... voids) {

        if (myApiService == null) {
            buildApiService();
        }

        String name = "00";

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "--Error-- " + e.getMessage());
            return "";
        }
    }

    private void buildApiService() {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl(ROOT_URL)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                        request.setDisableGZipContent(true);
                    }
                });
        myApiService = builder.build();
    }

    @Override
    protected void onPostExecute(String s) {
        newJoke = s;
        Log.d(TAG, "out: " + s);

    }



}
