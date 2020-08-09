package edu.tacoma.uw.ryandon.starfinderopenreference.authenticate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.tacoma.uw.ryandon.starfinderopenreference.MainActivity;
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
                    .addToBackStack(null)
                    .commit();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
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

        // in here we want to check if the email and pwd match a member in our members database
        //we should look courses to see how the sql values were analyzed in android studio.
        //put async get class here for the login in post
        //how do i access the members table from heroku
        if(logInApproved) {
            mSharedPreferences
                    .edit()
                    .putBoolean(getString(R.string.LOGGEDIN), true)
                    .commit();
            //we go to main activity if we are signing in , well need another method called register where login was instantiated?
//            getSupportFragmentManager().beginTransaction()

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
//            finish();
            return;
        } else{
            Log.d("Login else statement", "LoginApproved = false");
            StringBuilder url = new StringBuilder(getString(R.string.login));
            mMembersJSON = new JSONObject();
            try {
                mMembersJSON.put(Members.EMAIL, email);
                mMembersJSON.put(Members.PASSWORD,pwd);
                new SignInActivity.AddMembersAsyncTask().execute(url.toString());

                if(logInApproved){
                    Log.d("tag","logInApproved second if statement");
                    mSharedPreferences
                            .edit()
                            .putBoolean(getString(R.string.LOGGEDIN), true)
                            .commit();


                    //we go to main activity if we are signing in , well need another method called register where login was instantiated?
//                    getSupportFragmentManager().beginTransaction()
//                            .add(R.id.sign_in_fragment_container, new LoginFragment())
//                            .addToBackStack(null)
//                            .commit();
                    Intent i = new Intent(this, MainActivity.class);
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