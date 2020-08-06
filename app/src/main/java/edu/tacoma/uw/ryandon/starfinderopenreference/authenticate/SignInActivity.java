package edu.tacoma.uw.ryandon.starfinderopenreference.authenticate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener {
    private JSONObject mMembersJSON;
    private SharedPreferences mSharedPreferences;

    private boolean logInApproved;
    private String LOG_IN = "Login";
    private Object SignInActivity;
    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);

        if (mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), true)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sign_in_fragment_container, new LoginFragment())
                    .commit();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }



    @Override
    public void login(String email, String pwd) {

        // in here we want to check if the email and pwd match a member in our members database
        //we should look courses to see how the sql values were analyzed in android studio.
        //put async get class here for the login in post
        //how do i access the members table from heroku
        if(logInApproved){

            mSharedPreferences
                    .edit()
                    .putBoolean(getString(R.string.LOGGEDIN), true)
                    .commit();


            //we go to main activity if we are signing in , well need another method called register where login was instantiated?

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        } else{
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



    @Override
    public void register() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    public class AddMembersAsyncTask extends AsyncTask<String, Void, String> {


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