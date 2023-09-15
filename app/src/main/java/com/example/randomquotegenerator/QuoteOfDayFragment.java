package com.example.randomquotegenerator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;


import com.example.randomquotegenerator.R;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

    public class QuoteOfDayFragment extends Fragment {

        private TextView quoteTextView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.quote_of_the_day, container, false);
            quoteTextView = view.findViewById(R.id.textView_quote_of_the_day);

            // Set a click listener on a button to fetch and display the quote
            Button fetchQuoteButton = view.findViewById(R.id.button_fetch_quote);
            fetchQuoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        fetchQuoteOfTheDay();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            return view;
        }

        private void fetchQuoteOfTheDay() {
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        URL url = new URL("https://quotes.rest/qod?category=funny");

                        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                        urlc.setRequestMethod("GET");
                        urlc.setRequestProperty("Content-Type", "application/json");
                        urlc.setRequestProperty("X-TheySaidSo-Api-Secret", "QqqR9seHyjoUEwITWdyO37pVXNDdTkGMxIP5UmoX");
                        urlc.setAllowUserInteraction(false);
                        urlc.connect();

                        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
                        String response = "";
                        String line;
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                        br.close();

                        return response;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(String response) {
                    if (response != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject contentsObject = jsonObject.getJSONObject("contents");
                            JSONObject quotesObject = contentsObject.getJSONArray("quotes").getJSONObject(0);
                            String quote = quotesObject.getString("quote");
                            quoteTextView.setText(quote);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Handle error
                    }
                }
            }.execute();
        }

    }


