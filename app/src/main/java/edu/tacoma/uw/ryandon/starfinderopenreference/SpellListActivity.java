package edu.tacoma.uw.ryandon.starfinderopenreference;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.tacoma.uw.ryandon.starfinderopenreference.authenticate.SignInActivity;
import edu.tacoma.uw.ryandon.starfinderopenreference.model.Spell;
import edu.tacoma.uw.ryandon.starfinderopenreference.model.SpellContent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An activity representing a list of Spells. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SpellDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SpellListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private List<Spell> mSpellList;
    private RecyclerView mRecyclerView;
    private boolean classMysticCheck, classTechnomancerCheck, spellLevel0Check, spellLevel1Check
            , spellLevel2Check, spellLevel3Check, spellLevel4Check, spellLevel5Check, spellLevel6Check
            , schoolAbjCheck, schoolConjCheck, schoolDivCheck, schoolEnchCheck, schoolEvocCheck
            , schoolIlluCheck, schoolNecCheck, schoolTranCheck, rangePersonalCheck, rangeTouchCheck
            , rangeCloseCheck, rangeMediumCheck, rangeLongCheck, rangePlanetaryCheck, rangeSystemCheck
            , rangePlaneCheck, castStandardCheck, castMinuteCheck, castTenMinutesCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.spell_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        classMysticCheck = getIntent().getBooleanExtra("classMysticCheck", false);
        classTechnomancerCheck = getIntent().getBooleanExtra("classTechnomancerCheck", false);
        spellLevel0Check = getIntent().getBooleanExtra("spellLevel0Check", false);
        spellLevel1Check = getIntent().getBooleanExtra("spellLevel1Check", false);
        spellLevel2Check = getIntent().getBooleanExtra("spellLevel2Check", false);
        spellLevel3Check = getIntent().getBooleanExtra("spellLevel3Check", false);
        spellLevel4Check = getIntent().getBooleanExtra("spellLevel4Check", false);
        spellLevel5Check = getIntent().getBooleanExtra("spellLevel5Check", false);
        spellLevel6Check = getIntent().getBooleanExtra("spellLevel6Check", false);
        schoolAbjCheck = getIntent().getBooleanExtra("schoolAbjCheck", false);
        schoolConjCheck = getIntent().getBooleanExtra("schoolConjCheck", false);
        schoolDivCheck = getIntent().getBooleanExtra("schoolDivCheck", false);
        schoolEnchCheck = getIntent().getBooleanExtra("schoolEnchCheck", false);
        schoolEvocCheck = getIntent().getBooleanExtra("schoolEvocCheck", false);
        schoolIlluCheck = getIntent().getBooleanExtra("schoolIlluCheck", false);
        schoolNecCheck = getIntent().getBooleanExtra("schoolNecCheck", false);
        schoolTranCheck = getIntent().getBooleanExtra("schoolTranCheck", false);
        rangePersonalCheck = getIntent().getBooleanExtra("rangePersonalCheck", false);
        rangeTouchCheck = getIntent().getBooleanExtra("rangeTouchCheck", false);
        rangeCloseCheck = getIntent().getBooleanExtra("rangeCloseCheck", false);
        rangeMediumCheck = getIntent().getBooleanExtra("rangeMediumCheck", false);
        rangeLongCheck = getIntent().getBooleanExtra("rangeLongCheck", false);
        rangePlanetaryCheck = getIntent().getBooleanExtra("rangePlanetaryCheck", false);
        rangeSystemCheck = getIntent().getBooleanExtra("rangeSystemCheck", false);
        rangePlaneCheck = getIntent().getBooleanExtra("rangePlaneCheck", false);
        castStandardCheck = getIntent().getBooleanExtra("castStandardCheck", false);
        castMinuteCheck = getIntent().getBooleanExtra("castMinuteCheck", false);
        castTenMinutesCheck = getIntent().getBooleanExtra("castTenMinutesCheck", false);


        mRecyclerView = findViewById(R.id.spell_list);
        assert mRecyclerView != null;
        setupRecyclerView(mRecyclerView);
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        StringBuilder url = new StringBuilder(getString(R.string.spells));
        new SpellsTask().execute(url.toString());
        setupRecyclerView(mRecyclerView);
    }

    /**
     * Set's up the Recycler view to display the filtered spells.
     *
     * @param recyclerView
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        if (mSpellList != null)
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, filterSpells(mSpellList), mTwoPane));
    }

    /**
     * Simple set up for Recycler View
     */
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final SpellListActivity mParentActivity;
        private final List<Spell> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spell item = (Spell) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putSerializable(SpellDetailFragment.ARG_ITEM_ID, item.getSpellName());
                    SpellDetailFragment fragment = new SpellDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.spell_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, SpellDetailActivity.class);
                    intent.putExtra(SpellDetailFragment.ARG_ITEM_ID, item);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(SpellListActivity parent,
                                      List<Spell> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.spell_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getSpellName());
            holder.mContentView.setText(mValues.get(position).getClassName() + " "
                                        + mValues.get(position).getSpellLevel());
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }

    /**
     * AsyncTask for pulling from the spells table of the login and preferences heroku database
     */
    private class SpellsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();
                    InputStream content = urlConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }
                } catch (Exception e) {
                    response = "Unable to download the list of spells, Reason: " + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.startsWith("Unable to")) {
                Toast.makeText(getApplicationContext(), "Unable to download " + s, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getBoolean("success")) {
//                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    mSpellList = Spell.parseSpellsJson(jsonObject.getString("spells"));

                    //Filter spells from the JSONObject HERE

                    if (!mSpellList.isEmpty()) {
                        setupRecyclerView((RecyclerView) mRecyclerView);
                    }
                }
            } catch (JSONException e) {
               Toast.makeText(getApplicationContext(), "JSON Error: " + e.getMessage(), Toast.LENGTH_LONG);
            }
        }
    }

    /**
     * Uses the checkbox filters to return a spell list that represents the users choices.
     *
     * @param theSpellList The full spell list.
     * @return A spell list filtered to only display what was chosen. If all checkboxes are empty or selected, all spells are returned.
     */
    private List<Spell> filterSpells(List<Spell> theSpellList) {
        Set<Spell> filteredSet =  new HashSet<>();

        for (Spell spell : theSpellList) {
            if (classMysticCheck && spell.getClassName().equals("Mystic")) {
                filteredSet.add(spell);
            }
            if (classTechnomancerCheck && spell.getClassName().equals("Technomancer")) {
                filteredSet.add(spell);
            }
            if (spellLevel0Check && spell.getSpellLevel().equals("0")) {
                filteredSet.add(spell);
            }
            if (spellLevel1Check && spell.getSpellLevel().equals("1")) {
                filteredSet.add(spell);
            }
            if (spellLevel2Check && spell.getSpellLevel().equals("2")) {
                filteredSet.add(spell);
            }
            if (spellLevel3Check && spell.getSpellLevel().equals("3")) {
                filteredSet.add(spell);
            }
            if (spellLevel4Check && spell.getSpellLevel().equals("4")) {
                filteredSet.add(spell);
            }
            if (spellLevel5Check && spell.getSpellLevel().equals("5")) {
                filteredSet.add(spell);
            }
            if (spellLevel6Check && spell.getSpellLevel().equals("6")) {
                filteredSet.add(spell);
            }
            if (schoolAbjCheck && spell.getSpellSchool().equals("abjuration")) {
                filteredSet.add(spell);
            }
            if (schoolConjCheck && spell.getSpellSchool().equals("conjuration")) {
                filteredSet.add(spell);
            }
            if (schoolDivCheck && spell.getSpellSchool().equals("divination")) {
                filteredSet.add(spell);
            }
            if (schoolEnchCheck && spell.getSpellSchool().equals("enchantment")) {
                filteredSet.add(spell);
            }
            if (schoolEvocCheck && spell.getSpellSchool().equals("evocation")) {
                filteredSet.add(spell);
            }
            if (schoolIlluCheck && spell.getSpellSchool().equals("illusion")) {
                filteredSet.add(spell);
            }
            if (schoolNecCheck && spell.getSpellSchool().equals("necromancy")) {
                filteredSet.add(spell);
            }
            if (schoolTranCheck && spell.getSpellSchool().equals("transmutation")) {
                filteredSet.add(spell);
            }
            if (rangePersonalCheck && spell.getSpellRange().equals("personal")) {
                filteredSet.add(spell);
            }
            if (rangeTouchCheck && spell.getSpellRange().equals("touch")) {
                filteredSet.add(spell);
            }
            if (rangeCloseCheck && spell.getSpellRange().equals("close")) {
                filteredSet.add(spell);
            }
            if (rangeMediumCheck && spell.getSpellRange().equals("medium")) {
                filteredSet.add(spell);
            }
            if (rangeLongCheck && spell.getSpellRange().equals("long")) {
                filteredSet.add(spell);
            }
            if (rangePlanetaryCheck && spell.getSpellRange().equals("planetary")) {
                filteredSet.add(spell);
            }
            if (rangeSystemCheck && spell.getSpellRange().equals("system-wide")) {
                filteredSet.add(spell);
            }
            if (rangePlaneCheck && spell.getSpellRange().equals("plane")) {
                filteredSet.add(spell);
            }
            if (castStandardCheck && spell.getSpellCastTime().equals("1 standard action")) {
                filteredSet.add(spell);
            }
            if (castStandardCheck && spell.getSpellCastTime().equals("1 standard action")) {
                filteredSet.add(spell);
            }
            if (castMinuteCheck && spell.getSpellCastTime().equals("1 minute")) {
                filteredSet.add(spell);
            }
            if (castTenMinutesCheck && spell.getSpellCastTime().equals("10 minutes+")) {
                filteredSet.add(spell);
            }
        }
//        theSpellList.removeAll(filteredList);
        if (filteredSet.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Spell filters resulted in no spells to display. Displaying all spells instead.", Toast.LENGTH_LONG).show();
            return theSpellList;
        }

        return new ArrayList<>(filteredSet);
    };
}