package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jokelibraryandroid.MainJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {


    Context mContext;

    public EndpointAsyncTask(Context context) {
        buildApiService();
        this.mContext = context;
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
        if(s!=null&& !s.isEmpty()) {
            Intent intent = new Intent(mContext, MainJokeActivity.class);
            intent.putExtra(MainJokeActivity.PASSED_JOKE, s);
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "problem with backend", Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, "out: " + s);
        mContext=null;
    }


}
