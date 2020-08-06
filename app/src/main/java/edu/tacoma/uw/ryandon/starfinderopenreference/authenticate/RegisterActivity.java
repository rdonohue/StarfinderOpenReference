package edu.tacoma.uw.ryandon.starfinderopenreference.authenticate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//import edu.tacoma.uw.ryandon.starfinderopenreference.MembersDetailActivity;
import edu.tacoma.uw.ryandon.starfinderopenreference.R;
import edu.tacoma.uw.ryandon.starfinderopenreference.data.MembersDB;
import edu.tacoma.uw.ryandon.starfinderopenreference.model.Members;

public class RegisterActivity extends AppCompatActivity implements MemberAddFragment.MemberAddFragmentListener {

    public static final String ADD_MEMBER = "ADD_Member";
    private JSONObject mMembersJSON;


    /**
     * This method sets up the content view and fragment manager for the registration of new members.
     * @param savedInstanceState a Bundle object representing the saved instance state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.register_fragment_container, new MemberAddFragment())
                    .commit();




    }

    /**
     * This method adds members to the database by creating a JSON object and running an asynchronous task to access the heroku backend.
     * @param members The member object to be added to the database .
     */
    @Override
    public void addMember(Members members) {

        StringBuilder url = new StringBuilder(getString(R.string.register));

        Log.d("myTag", "" + url);

        mMembersJSON = new JSONObject();
        try {

            mMembersJSON.put(Members.FIRST_NAME, members.getmFirstName());
            mMembersJSON.put(Members.LAST_NAME, members.getmLastName());

            mMembersJSON.put(Members.EMAIL, members.getmEmail());
            mMembersJSON.put(Members.USERNAME, members.getmUsername());
            mMembersJSON.put(Members.PASSWORD, members.getmPassword());
            new RegisterActivity.AddMembersAsyncTask().execute(url.toString());
        } catch (JSONException e) {
            Toast.makeText(this, "Error with JSON creation on addng a Member: "
                    + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public  class AddMembersAsyncTask extends AsyncTask<String, Void, String> {
        /**
         * This method connects to the heroku login url in the background
         * @param urls the heroku website address that can query the login functions.
         * @return the response from the heroku server
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
                    Log.i(ADD_MEMBER, mMembersJSON.toString());
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
                    response = "Unable to add the new member, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * This method reads the incoming JSON from heroku and displays relevant error and success messages to the developer.
         * @param s a String denoting the JSON object being sent
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
                    Toast.makeText(getApplicationContext(), "Member Added successfully"
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Member couldn't be added: "
                                    + jsonObject.getString("error")
                            , Toast.LENGTH_LONG).show();
                    Log.e(ADD_MEMBER, jsonObject.getString("error"));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Parsing error on Adding Member"
                                + e.getMessage()
                        , Toast.LENGTH_LONG).show();
                Log.e(ADD_MEMBER, e.getMessage());
            }



        }

    }
}
