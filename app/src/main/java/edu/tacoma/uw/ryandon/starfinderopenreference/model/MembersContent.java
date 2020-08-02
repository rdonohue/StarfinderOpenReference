package edu.tacoma.uw.ryandon.starfinderopenreference.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MembersContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<Members> ITEMS = new ArrayList<Members>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Members> ITEM_MAP = new HashMap<String, Members>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createMemberItem(i));
        }
    }

    private static void addItem(Members item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getmMemberID(), item);
    }

    private static Members createMemberItem(int position) {
        return new Members( "MemberID" + position,  "FirstName" + position,
                "LastName" + position, "UserName" + position,
                "Email" + position,  "Password" + position);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Course: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class MemberItem {
        public final String id;
        public final String content;
        public final String details;

        public MemberItem(String id, String content, String details) {
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
