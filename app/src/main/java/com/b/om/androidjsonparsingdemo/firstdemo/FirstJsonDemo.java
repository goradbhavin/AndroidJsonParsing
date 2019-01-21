package com.b.om.androidjsonparsingdemo.firstdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.b.om.androidjsonparsingdemo.R;
import com.b.om.androidjsonparsingdemo.controller.HttpHandler;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhavin on 1/20/2019.
 */

public class FirstJsonDemo extends AppCompatActivity {

    private final String URL_TO_HIT = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt";

    public ListView listView ;
    HttpHandler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_json_demo);


        listView = (ListView) findViewById(R.id.listViewFirstJson);
        handler = new HttpHandler();
        new JsonTask().execute(URL_TO_HIT);
    }

    private class JsonTask extends AsyncTask<String,String,List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Movie> doInBackground(String... strings) {

            HttpHandler handler = new HttpHandler();
            String finalJsonObj = handler.makeServiceCall(strings[0]);

            try {
                JSONObject parentObj = new JSONObject(finalJsonObj);
                JSONArray parentArray = parentObj.getJSONArray("movies");


                List<Movie> movieList = new ArrayList<>();
                Gson gson = new Gson();
                for (int i=0;i<parentArray.length();i++){
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    Movie movie = gson.fromJson(finalObject.toString(),Movie.class);
                    movieList.add(movie);

                }
                return movieList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<Movie> movies) {
            super.onPostExecute(movies);

            if (movies != null){
                MovieAdapter adapter = new MovieAdapter(getApplicationContext(),R.layout.item_movies,movies);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getApplicationContext(),movies.get(i).getMovie(),Toast.LENGTH_LONG).show();
                    }
                });
            }

            }
        }
}
