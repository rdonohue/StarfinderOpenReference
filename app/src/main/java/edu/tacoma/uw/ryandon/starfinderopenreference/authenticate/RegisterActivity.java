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
    private MembersDB mMembersDB;
    private List<Members> mMembersList;
    private boolean loginGood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.register_fragment_container, new MemberAddFragment())
                    .commit();




    }


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


//            try {
//                JSONObject jsonObject = new JSONObject(s);
//
//                if (jsonObject.getBoolean("success")) {
//                    mMembersList = Members.parseCourseJson(
//                            jsonObject.getString("register"));
//                    if (mMembersDB == null) {
//                        mMembersDB = new MembersDB(getApplicationContext());
//                    }
//
//                    // Delete old data so that you can refresh the local
//                    // database with the network data.
//                    mMembersDB.deleteMembers();
//
//                    // Also, add to the local database
//                    for (int i = 0; i < mMembersList.size(); i++) {
//                        Members members = mMembersList.get(i);
//                        mMembersDB.insertMembers(members.getmMemberID(),
//                                members.getmFirstName(),
//                                members.getmLastName(),
//                                members.getmUsername(),
//                                members.getmEmail(),
//                                members.getmPassword());
//                    }
//                }
//            } catch (JSONException e) {
//                Toast.makeText(getApplicationContext(), "JSON Error: " + e.getMessage(),
//                        Toast.LENGTH_SHORT).show();
//            }
        }

    }
}
