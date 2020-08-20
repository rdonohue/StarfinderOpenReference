package edu.tacoma.uw.ryandon.starfinderopenreference.authenticate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.tacoma.uw.ryandon.starfinderopenreference.FilterActivity;
import edu.tacoma.uw.ryandon.starfinderopenreference.R;
import edu.tacoma.uw.ryandon.starfinderopenreference.model.Members;



public class SignInActivity extends AppCompatActivity implements LoginFragmentListener {


    public static FragmentManager fragmentManager;

    private JSONObject mMembersJSON;
    private SharedPreferences mSharedPreferences;

    private boolean logInApproved;
    private String LOG_IN = "Login";



    public void ReturnButton(View view){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }



    /**
     * Sets up the fragment support manager and intent based off the login preferences of the user.
     * @param savedInstanceState a Bundle that represents the saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        fragmentManager = getSupportFragmentManager();
        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);

        if (mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), true)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sign_in_fragment_container, new LoginFragment())
                    .commit();
        } else {
            Intent intent = new Intent(this, FilterActivity.class);
            startActivity(intent);
            finish();
        }
    }
    
    /**
     * The implementation of the login method which first checks for user logged in preferences and then runs an asynchronous task
     * grabbing information from the members database and checking it against the user input login credentials.
     * @param email the user input email.
     * @param pwd the user input password.
     */

    @Override
    public void login(String email, String pwd) {


        if (logInApproved) {
            mSharedPreferences
                    .edit()
                    .putBoolean(getString(R.string.LOGGEDIN), true)
                    .commit();


            Intent i = new Intent(this, FilterActivity.class);
            startActivity(i);

            return;
        } else {
            StringBuilder url = new StringBuilder(getString(R.string.login));
            mMembersJSON = new JSONObject();
            try {
                mMembersJSON.put(Members.EMAIL, email);
                mMembersJSON.put(Members.PASSWORD,pwd);
                new SignInActivity.AddMembersAsyncTask().execute(url.toString());

                if (logInApproved) {
                    mSharedPreferences
                            .edit()
                            .putBoolean(getString(R.string.LOGGEDIN), true)
                            .commit();



                    Intent i = new Intent(this, FilterActivity.class);
                    startActivity(i);
                    finish();
                    return;
                }
            } catch (JSONException e) {
                Toast.makeText(this, "Error with JSON creation on logging in: "
                        + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }




    /**
     * This method brings the user into the register activity
     */
    @Override
    public void register() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public class AddMembersAsyncTask extends AsyncTask<String, Void, String> {

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
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setDoOutput(true);
                    OutputStreamWriter wr =
                            new OutputStreamWriter(urlConnection.getOutputStream());

                    // For Debugging
                    Log.i(LOG_IN, mMembersJSON.toString());
                    wr.write(mMembersJSON.toString());
                    wr.flush();
                    wr.close();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to login, Reason: "
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
                    Toast.makeText(getApplicationContext(), "Log in approved"
                            , Toast.LENGTH_SHORT).show();


                    logInApproved = true;
                    if(logInApproved) {
                        mSharedPreferences
                                .edit()
                                .putBoolean(getString(R.string.LOGGEDIN), true)
                                .commit();


                        Intent i = new Intent(getApplicationContext(), FilterActivity.class);
                        startActivity(i);


                    }
                    return;

                } else {
                    Toast.makeText(getApplicationContext(), "couldnt log in: "
                                    + jsonObject.getString("error")
                            , Toast.LENGTH_LONG).show();
                    Log.e(LOG_IN, jsonObject.getString("error"));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Parsing error on logging in"
                                + e.getMessage()
                        , Toast.LENGTH_LONG).show();
                Log.e(LOG_IN, e.getMessage());
            }
        }
    }


}