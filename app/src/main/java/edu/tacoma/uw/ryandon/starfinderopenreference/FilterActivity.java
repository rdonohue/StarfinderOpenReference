package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

import org.json.JSONObject;


public class FilterActivity extends AppCompatActivity {


    public static FragmentManager fragmentManager;
    private CheckBox classMystic, classTechnomancer, spellLevel0, spellLevel1
            , spellLevel2, spellLevel3, spellLevel4, spellLevel5, spellLevel6, schoolAbj
            , schoolConj, schoolDiv, schoolEnch, schoolEvoc, schoolIllu, schoolNec
            , schoolTran, rangePersonal, rangeTouch, rangeClose, rangeMedium
            , rangeLong, rangePlanetary, rangeSystem, rangePlane, castStandard
            , castMinute, castTenMinutes;
    private boolean classMysticCheck, classTechnomancerCheck, spellLevel0Check, spellLevel1Check
            , spellLevel2Check, spellLevel3Check, spellLevel4Check, spellLevel5Check, spellLevel6Check
            , schoolAbjCheck, schoolConjCheck, schoolDivCheck, schoolEnchCheck, schoolEvocCheck
            , schoolIlluCheck, schoolNecCheck, schoolTranCheck, rangePersonalCheck, rangeTouchCheck
            , rangeCloseCheck, rangeMediumCheck, rangeLongCheck, rangePlanetaryCheck, rangeSystemCheck
            , rangePlaneCheck, castStandardCheck, castMinuteCheck, castTenMinutesCheck;

    /**
     * The overriden onCreate method setting our layout to main activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        fragmentManager = getSupportFragmentManager();
        checkboxInit();
//        StringBuilder url = new StringBuilder(getString(R.string.spells));
//        mSpellsJson = new JSONObject();
//        new GetSpellsAsyncTask().execute(url.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    public void onCheckboxClicked(View view) {
//        // Is the view now checked?
//        boolean checked = ((CheckBox) view).isChecked();
//
//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case R.id.checkBox_class_mystic:
//                if (checked) {
//                    // Spell filter clicked
//
//                } else {
//                    // Spell filter un-clicked
//
//                }
//                break;
//        }
//    }

//    public class GetSpellsAsyncTask extends AsyncTask<String, Void, String> {
//
//        /**
//         * This is the overriden background method that posts to the login url.
//         *
//         * @param urls the url visited during the asynchronous task.
//         * @return
//         */
//        @Override
//        protected String doInBackground(String... urls) {
//            String response = "";
//            HttpURLConnection urlConnection = null;
//            for (String url : urls) {
//                try {
//                    URL urlObject = new URL(url);
//                    urlConnection = (HttpURLConnection) urlObject.openConnection();
//                    urlConnection.setRequestMethod("GET");
//                    urlConnection.setRequestProperty("User-Agent", "Accept-Language");
//                    urlConnection.setDoOutput(true);
//                    InputStreamReader reader =
//                            new InputStreamReader(urlConnection.getInputStream());
//
//                    // For Debugging
//                    Log.i("GET_SPELL", mSpellsJson.toString());
//                    reader.read();
//                    //reader.flush();
//                    reader.close();
//
//                    InputStream content = urlConnection.getInputStream();
//
//                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
//                    String s = "";
//                    while ((s = buffer.readLine()) != null) {
//                        response += s;
//                    }
//
//                } catch (Exception e) {
//                    response = "Unable to get spells Reason: "
//                            + e.getMessage();
//                } finally {
//                    if (urlConnection != null)
//                        urlConnection.disconnect();
//                }
//            }
//
//
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//            if (s.startsWith("Unable to")) {
//                Toast.makeText(getApplicationContext(), "Unable to download" + s, Toast.LENGTH_LONG).show();
//
//                return;
//            }
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                if (jsonObject.getBoolean("success")) {
//                    Toast.makeText(getApplicationContext(), "get spell approved"
//                            , Toast.LENGTH_SHORT).show();
//                    Log.d("we successed the get", "success");
//
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "couldnt get spell : "
//                                    + jsonObject.getString("error")
//                            , Toast.LENGTH_LONG).show();
//                    Log.e("GET_SPELLS", jsonObject.getString("error"));
//                }
//            } catch (JSONException e) {
//                Toast.makeText(getApplicationContext(), "JSON Parsing error on getting spell"
//                                + e.getMessage()
//                        , Toast.LENGTH_LONG).show();
//                Log.e("GET_SPELLS", e.getMessage());
//            }
//        }
//    }

    public void searchButton(View view) {
        Intent intent = new Intent(this, SpellListActivity.class);
        filterIsChecked();
        intent.putExtra("classMysticCheck", classMysticCheck);
        intent.putExtra("classTechnomancerCheck", classTechnomancerCheck);
        startActivity(intent);
    }

    private void filterIsChecked() {
        classMysticCheck = classMystic.isChecked();
        classTechnomancerCheck = classTechnomancer.isChecked();
        spellLevel0Check = spellLevel0.isChecked();
        spellLevel1Check = spellLevel1.isChecked();
        spellLevel2Check = spellLevel2.isChecked();
        spellLevel3Check = spellLevel3.isChecked();
        spellLevel4Check = spellLevel4.isChecked();
        spellLevel5Check = spellLevel5.isChecked();
        spellLevel6Check = spellLevel6.isChecked();
        schoolAbjCheck = schoolAbj.isChecked();
        schoolConjCheck = schoolConj.isChecked();
        schoolDivCheck = schoolDiv.isChecked();
        schoolEnchCheck = schoolEnch.isChecked();
        schoolEvocCheck = schoolEvoc.isChecked();
        schoolIlluCheck = schoolIllu.isChecked();
        schoolNecCheck = schoolNec.isChecked();
        schoolTranCheck = schoolTran.isChecked();
        rangePersonalCheck = rangePersonal.isChecked();
        rangeTouchCheck = rangeTouch.isChecked();
        rangeCloseCheck = rangeClose.isChecked();
        rangeMediumCheck = rangeMedium.isChecked();
        rangeLongCheck = rangeLong.isChecked();
        rangePlanetaryCheck = rangePlanetary.isChecked();
        rangeSystemCheck = rangeSystem.isChecked();
        rangePlaneCheck = rangePlane.isChecked();
        castStandardCheck = castStandard.isChecked();
        castMinuteCheck = castMinute.isChecked();
        castTenMinutesCheck = castTenMinutes.isChecked();
    }

    /**
     * Initializes all the checkboxes on for use with the filters.
     */
    private void checkboxInit() {
        classMystic = findViewById(R.id.checkBox_class_mystic);
        classTechnomancer = findViewById(R.id.checkBox_class_tech);
        spellLevel0 = findViewById(R.id.checkBox_level0);
        spellLevel1 = findViewById(R.id.checkBox_level1);
        spellLevel2 = findViewById(R.id.checkBox_level2);
        spellLevel3 = findViewById(R.id.checkBox_level3);
        spellLevel4 = findViewById(R.id.checkBox_level4);
        spellLevel5 = findViewById(R.id.checkBox_level5);
        spellLevel6 = findViewById(R.id.checkBox_level6);
        schoolAbj = findViewById(R.id.checkBox_abjuration);
        schoolConj = findViewById(R.id.checkBox_conjuration);
        schoolDiv = findViewById(R.id.checkBox_divination);
        schoolEnch = findViewById(R.id.checkBox_enchantment);
        schoolEvoc = findViewById(R.id.checkBox_evocation);
        schoolIllu = findViewById(R.id.checkBox_illusion);
        schoolNec = findViewById(R.id.checkBox_necromancy);
        schoolTran = findViewById(R.id.checkBox_transmutation);
        rangePersonal = findViewById(R.id.checkBox_range_personal);
        rangeTouch = findViewById(R.id.checkBox_range_touch);
        rangeClose = findViewById(R.id.checkBox_range_close);
        rangeMedium = findViewById(R.id.checkBox_range_medium);
        rangeLong = findViewById(R.id.checkBox_range_long);
        rangePlanetary = findViewById(R.id.checkBox_range_planetary);
        rangeSystem = findViewById(R.id.checkBox_range_systemwide);
        rangePlane = findViewById(R.id.checkBox_range_plane);
        castStandard = findViewById(R.id.checkBox_cast_stdact);
        castMinute = findViewById(R.id.checkBox_cast_1min);
        castTenMinutes = findViewById(R.id.checkBox_cast_10min);
    }

}

