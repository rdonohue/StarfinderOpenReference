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

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Spell: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class SpellItem {
        public final String id;
        public final String content;
        public final String details;

        public SpellItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
