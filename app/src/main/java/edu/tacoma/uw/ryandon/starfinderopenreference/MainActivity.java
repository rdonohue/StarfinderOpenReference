package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.tacoma.uw.ryandon.starfinderopenreference.model.MembersContent;


public class MainActivity extends AppCompatActivity {


    public static FragmentManager fragmentManager;
    private JSONObject mSpellsJson;


    /**
     * The overriden onCreate method setting our layout to main activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        StringBuilder url = new StringBuilder(getString(R.string.spells));
        mSpellsJson = new JSONObject();
        new GetSpellsAsyncTask().execute(url.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_class_mystic:
                if (checked) {
                    // Spell filter clicked

                } else {
                    // Spell filter un-clicked

                }
                break;
        }
    }

    public class GetSpellsAsyncTask extends AsyncTask<String, Void, String> {

        /**
         * This is the overriden background method that posts to the login url.
         *
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
                    urlConnection.setRequestProperty("User-Agent", "Accept-Language");
                    urlConnection.setDoOutput(true);
                    InputStreamReader reader =
                            new InputStreamReader(urlConnection.getInputStream());

                    // For Debugging
                    Log.i("GET_SPELL", mSpellsJson.toString());
                    reader.read();
                    //reader.flush();
                    reader.close();

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

        @Override
        protected void onPostExecute(String s) {

            if (s.startsWith("Unable to")) {
                Toast.makeText(getApplicationContext(), "Unable to download" + s, Toast.LENGTH_LONG).show();

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

