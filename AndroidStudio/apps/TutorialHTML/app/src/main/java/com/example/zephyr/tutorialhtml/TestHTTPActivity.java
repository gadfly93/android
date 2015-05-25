package com.example.zephyr.tutorialhtml;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;


/**
 * Created by Zephyr on 21/05/2015.
 */

public class TestHTTPActivity extends ActionBarActivity {

    private static final String DEBUG_TAG = "HttpExample";
    private EditText urlText;
    private TextView downloadedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testhttp);

        urlText = (EditText) findViewById(R.id.urlText);
        downloadedText = (TextView) findViewById(R.id.downloadText);
    }

    // When user clicks button, calls AsyncTask.
    // Before attempting to fetch the URL, makes sure that there is a network connection.

    public void downloadFromURL(View view) {
        // Gets the URL from the UI's text field.
        //String stringUrl = urlText.getText().toString();
        String stringUrl = "http://www.swas.polito.it/dotnet/orari_lezione_pub/mobile/ws_orari_mobile.asmx/get_elenco_aule";
        urlText.setText(stringUrl);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadMapsMarkerTask().execute(stringUrl);
        } else {
            //textView.setTextColor(R.color.red);
            downloadedText.setText("No network connection available.");
        }
    }

    // Uses AsyncTask to create a task away from the main UI thread. This task takes a
    // URL string and uses it to create an HttpUrlConnection. Once the connection
    // has been established, the AsyncTask downloads the contents of the webpage as
    // an InputStream. Finally, the InputStream is converted into a string, which is
    // displayed in the UI by the AsyncTask's onPostExecute method.

    private class DownloadMapsMarkerTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            downloadedText.setText(result);
        }

        // Given a URL, establishes an HttpUrlConnection and retrieves
        // the web page content as a InputStream, which it returns as
        // a string.

        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only display the first 1000 characters of the retrieved
            // web page content.

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // Set request nature and parameters
                conn.setRequestMethod("POST");
                conn.setDoInput(true);

                // Starts the query
                conn.connect();

                // Read the response
                int responseCode = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + responseCode);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                BufferedReader in = new BufferedReader(new InputStreamReader(is));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while((inputLine = in.readLine())!= null) {
                    response.append(inputLine);
                }

                in.close();

                return new String(response);

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
    }
}
