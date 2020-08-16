package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.tacoma.uw.ryandon.starfinderopenreference.authenticate.SignInActivity;


public class MainActivity extends AppCompatActivity {


    public static FragmentManager fragmentManager;
    public JSONObject mSpellsJson;
    /**
     * The overriden onCreate method setting our layout to main activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        StringBuilder url = new StringBuilder(getString(R.string.spells));

        new MainActivity.GetSpellsAsyncTask().execute(url.toString());
        return true;
    }





    public class GetSpellsAsyncTask extends AsyncTask<String, Void, String> {

        /**
         * This is the overriden background method that posts to the login url.
         * @param urls the url visited during the asynchronous task.
         * @return
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setDoOutput(true);
                    OutputStreamWriter wr =
                            new OutputStreamWriter(urlConnection.getOutputStream());

                    // For Debugging
                    Log.i("GET_SPELL", mSpellsJson.toString());
                    wr.write(mSpellsJson.toString());
                    wr.flush();
                    wr.close();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to get spells Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }



            return response;
        }

        /**
         * This is the overriden onPostExecute method that will change the boolean value allowing entry into the program behind the login screen.
         * @param s a String of data passed on through onPostExecute.
         */
        @Override
        protected void onPostExecute(String s) {

            if (s.startsWith("Unable to")) {
                Toast.makeText(getApplicationContext(), "Unable to download" + s, Toast.LENGTH_SHORT).show();

                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getBoolean("success")) {
                    Toast.makeText(getApplicationContext(), "get spell approved"
                            , Toast.LENGTH_SHORT).show();
                    Log.d("we successed the get", "success");



                } else {
                    Toast.makeText(getApplicationContext(), "couldnt get spell : "
                                    + jsonObject.getString("error")
                            , Toast.LENGTH_LONG).show();
                    Log.e("GET_SPELLS", jsonObject.getString("error"));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Parsing error on getting spell"
                                + e.getMessage()
                        , Toast.LENGTH_LONG).show();
                Log.e("GET_SPELLS", e.getMessage());
            }
        }
    }



}

