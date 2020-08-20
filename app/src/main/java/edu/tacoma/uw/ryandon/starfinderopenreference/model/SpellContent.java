package edu.tacoma.uw.ryandon.starfinderopenreference.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<Spell> SPELLS = new ArrayList<Spell>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Spell> ITEM_MAP = new HashMap<String, Spell>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addSpell(createSpellItem(i));
        }
    }

    private static void addSpell(Spell spell) {
        SPELLS.add(spell);
        ITEM_MAP.put(spell.getSpellID(), spell);
    }

    private static Spell createSpellItem(int position) {
        return new Spell( "Name" + position, "Class" + position, "Level" + position
        , "School" + position, "Range" + position, "Cast Time" + position, "Description" + position);
    }




}
