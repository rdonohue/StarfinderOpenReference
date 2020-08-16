package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;


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


import edu.tacoma.uw.ryandon.starfinderopenreference.authenticate.SignInActivity;

import edu.tacoma.uw.ryandon.starfinderopenreference.model.MembersContent;



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

        StringBuilder url = new StringBuilder(getString(R.string.spells));
        mSpellsJson = new JSONObject();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        StringBuilder url = new StringBuilder(getString(R.string.spells));

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


}

