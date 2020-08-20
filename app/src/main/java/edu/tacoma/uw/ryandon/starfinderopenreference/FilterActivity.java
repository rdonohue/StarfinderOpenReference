package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.content.Intent;


import android.net.Uri;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.Menu;

import android.view.View;
import android.widget.CheckBox;


import org.json.JSONObject;




import edu.tacoma.uw.ryandon.starfinderopenreference.authenticate.SignInActivity;

import edu.tacoma.uw.ryandon.starfinderopenreference.model.MembersContent;

    public class FilterActivity extends AppCompatActivity {
        public static FragmentManager fragmentManager;

        public JSONObject mSpellsJson;

        private CheckBox classMystic, classTechnomancer, spellLevel0, spellLevel1, spellLevel2
                , spellLevel3, spellLevel4, spellLevel5, spellLevel6, schoolAbj, schoolConj
                , schoolDiv, schoolEnch, schoolEvoc, schoolIllu, schoolNec, schoolTran, rangePersonal
                , rangeTouch, rangeClose, rangeMedium, rangeLong, rangePlanetary, rangeSystem, rangePlane
                , castStandard, castMinute, castTenMinutes;

        private boolean classMysticCheck, classTechnomancerCheck, spellLevel0Check, spellLevel1Check
                , spellLevel2Check, spellLevel3Check, spellLevel4Check, spellLevel5Check, spellLevel6Check
                , schoolAbjCheck, schoolConjCheck, schoolDivCheck, schoolEnchCheck, schoolEvocCheck
                , schoolIlluCheck, schoolNecCheck, schoolTranCheck, rangePersonalCheck, rangeTouchCheck
                , rangeCloseCheck, rangeMediumCheck, rangeLongCheck, rangePlanetaryCheck, rangeSystemCheck
                , rangePlaneCheck, castStandardCheck, castMinuteCheck, castTenMinutesCheck;


        /**
         * The overriden onCreate method setting our layout to main activity.
         *
         * @param savedInstanceState
         */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_filter);
            fragmentManager = getSupportFragmentManager();

            StringBuilder url = new StringBuilder(getString(R.string.spells));
            mSpellsJson = new JSONObject();

            checkboxInit();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);


            StringBuilder url = new StringBuilder(getString(R.string.spells));

            return true;
        }

        /**
         * On click method for the Search button.
         *
         * @param view
         */
        public void searchButton(View view) {
            Intent intent = new Intent(this, SpellListActivity.class);
            filterIsChecked();
            intent.putExtra("classMysticCheck", classMysticCheck);
            intent.putExtra("classTechnomancerCheck", classTechnomancerCheck);
            intent.putExtra("spellLevel0Check", spellLevel0Check);
            intent.putExtra("spellLevel1Check", spellLevel1Check);
            intent.putExtra("spellLevel2Check", spellLevel2Check);
            intent.putExtra("spellLevel3Check", spellLevel3Check);
            intent.putExtra("spellLevel4Check", spellLevel4Check);
            intent.putExtra("spellLevel5Check", spellLevel5Check);
            intent.putExtra("spellLevel6Check", spellLevel6Check);
            intent.putExtra("schoolAbjCheck", schoolAbjCheck);
            intent.putExtra("schoolConjCheck", schoolConjCheck);
            intent.putExtra("schoolDivCheck", schoolDivCheck);
            intent.putExtra("schoolEnchCheck", schoolEnchCheck);
            intent.putExtra("schoolEvocCheck", schoolEvocCheck);
            intent.putExtra("schoolIlluCheck", schoolIlluCheck);
            intent.putExtra("schoolNecCheck", schoolNecCheck);
            intent.putExtra("schoolTranCheck", schoolTranCheck);
            intent.putExtra("rangePersonalCheck", rangePersonalCheck);
            intent.putExtra("rangeTouchCheck", rangeTouchCheck);
            intent.putExtra("rangeCloseCheck", rangeCloseCheck);
            intent.putExtra("rangeMediumCheck", rangeMediumCheck);
            intent.putExtra("rangeLongCheck", rangeLongCheck);
            intent.putExtra("rangePlanetaryCheck", rangePlanetaryCheck);
            intent.putExtra("rangeSystemCheck", rangeSystemCheck);
            intent.putExtra("rangePlaneCheck", rangePlaneCheck);
            intent.putExtra("castStandardCheck", castStandardCheck);
            intent.putExtra("castMinuteCheck", castMinuteCheck);
            intent.putExtra("castTenMinutesCheck", castTenMinutesCheck);

            startActivity(intent);
        }

//        /**
//         * Opens a browser to display the Open Game License applicable to Starfinder rules and spells displayed in this app.
//         *
//         * @param view
//         */
//        public void oglButton(View view) {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://starfinder.fandom.com/wiki/OGL"));
//            startActivity(intent);
//        }

        /**
         * Helper method to for determining which checkboxes are selected for filters upon Searching
         */
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



