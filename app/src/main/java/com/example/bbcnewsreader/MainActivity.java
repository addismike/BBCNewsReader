package com.example.bbcnewsreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bbcnewsreader.Adapter.FeedAdapter;
import com.example.bbcnewsreader.Common.HTTPDataHandler;
import com.example.bbcnewsreader.Model.RSSObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

   Toolbar toolbar;
   RecyclerView recyclerView;
   RSSObject rssObject;

   // RSS Link

    private final String RSS_link="http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml";
    private final String RSS_to_Json_API="https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("NEWS");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        loadRSS();



        }

        // we will use AsyncTask

    private void loadRSS() {
        AsyncTask<String, String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog dialog = new ProgressDialog(MainActivity.this);

            @Override
            protected void onPreExecute() {

                dialog.setMessage("Please wait........");
                dialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler httpDataHandler = new HTTPDataHandler();
                result = httpDataHandler.GetHTTData(Params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "thanks for waiting !!", Toast.LENGTH_SHORT).show();
                rssObject = new Gson().fromJson(s, RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject, getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }


        };


            }
        }



