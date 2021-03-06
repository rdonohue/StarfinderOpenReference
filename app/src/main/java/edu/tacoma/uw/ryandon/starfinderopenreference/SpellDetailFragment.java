package edu.tacoma.uw.ryandon.starfinderopenreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.tacoma.uw.ryandon.starfinderopenreference.model.Spell;


/**
 * A fragment representing a single Spell detail screen.
 * This fragment is either contained in a {@link SpellListActivity}
 * in two-pane mode (on tablets) or a {@link SpellDetailActivity}
 * on handsets.
 */
public class SpellDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The spell content this fragment is presenting.
     */
    private Spell mSpell;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpellDetailFragment() {
    }

    /**
     * Creates a nice string for the email that will be shared.
     */
    private String emailMaker(){
        String str ="Check out this spell! \n" +
                    "Name: " + mSpell.getSpellName() + " \n" +
                      "Level: " + mSpell.getSpellLevel() + " \n" +
                      "Cast Time: " + mSpell.getSpellCastTime() + " \n" +
                      "School: " + mSpell.getSpellSchool() + " \n" +
                      "Range: " + mSpell.getSpellRange() + " \n" +
                        "Class Name: " + mSpell.getClassName() + " \n" +
                      "Spell Description: " + " \n" +
                      mSpell.getSpellDescription();



                return str;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mSpell = (Spell) getArguments().getSerializable(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mSpell.getSpellName());
            }
        }
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, emailMaker());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spell_detail, container, false);

        // Show the spell content as text in a TextView.
        if (mSpell != null) {
            ((TextView) rootView.findViewById(R.id.school_dynamic_id)).setText(
                    mSpell.getSpellSchool());
            ((TextView) rootView.findViewById(R.id.cast__time_dynamic_id)).setText(
                    mSpell.getSpellCastTime());
            ((TextView) rootView.findViewById(R.id.range_dynamic_id)).setText(
                    mSpell.getSpellRange());
            ((TextView) rootView.findViewById(R.id.level_dynamic_id)).setText(
                    mSpell.getSpellLevel());
            ((TextView) rootView.findViewById(R.id.class_dynamic_id)).setText(
                    mSpell.getClassName());
            ((TextView) rootView.findViewById(R.id.description_dynamic_id)).setText(
                    mSpell.getSpellDescription());
        }

        return rootView;
    }
}