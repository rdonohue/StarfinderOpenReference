package edu.tacoma.uw.ryandon.starfinderopenreference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.ryandon.starfinderopenreference.model.Spell;

public class SpellDB {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Course.db";

    private SQLiteDatabase mSQLiteDatabase;
   // private SpellDBHelper mSpellDBHelper;

//    public SpellDB(Context context) {
//        mSpellDBHelper = new SpellDBHelper(
//                context, DB_NAME, null, DB_VERSION);
//        mSQLiteDatabase = mSpellDBHelper.getWritableDatabase();
//    }

//    /**
//     * Inserts the course into the local sqlite table. Returns true if successful, false otherwise.
//     * @param id
//     * @param shortDesc
//     * @param longDesc
//     * @param prereqs
//     * @return true or false
//     */
//    public boolean insertSpell(String id, String shortDesc, String longDesc, String prereqs) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", id);
//        contentValues.put("shortDesc", shortDesc);
//        contentValues.put("longDesc", longDesc);
//        contentValues.put("prereqs", prereqs);
//
//        long rowId = mSQLiteDatabase.insert("Course", null, contentValues);
//        return rowId != -1;
//    }

    /**
     * Delete all the data from the Courses
     */
    public void deleteCourses() {
        mSQLiteDatabase.delete("Course", null, null);
    }

    /**
     * Returns the list of courses from the local Course table.
     * @return list
     */
    public List<Spell> getSpells() {

        String[] columns = {
                "Name", "Class", "Level", "School", "Range", "Cast Time"
        };

        Cursor c = mSQLiteDatabase.query(
                "Spells",  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<Spell> list = new ArrayList<Spell>();
        for (int i = 0; i < c.getCount(); i++) {
            String theName = c.getString(0);
            String theClass = c.getString(1);
            String theLevel = c.getString(2);
            String theSchool = c.getString(3);
            String theRange = c.getString(4);
            String theCastTime = c.getString(5);
            String theDesc = c.getString(6);
            Spell spell = new Spell(theName, theClass, theLevel, theSchool, theRange, theCastTime, theDesc);
            list.add(spell);
            c.moveToNext();
        }
        return list;
    }


//    class SpellDBHelper extends SQLiteOpenHelper {
//
//        private final String CREATE_COURSE_SQL;
//        private final String DROP_COURSE_SQL;
//
//        public SpellDBHelper(@Nullable Context context, @Nullable String name
//                , @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//            super(context, name, factory, version);
//            CREATE_COURSE_SQL = context.getString(R.string.CREATE_COURSE_SQL);
//            DROP_COURSE_SQL = context.getString(R.string.DROP_COURSE_SQL);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase sqLiteDatabase) {
//            sqLiteDatabase.execSQL(CREATE_COURSE_SQL);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//            sqLiteDatabase.execSQL(DROP_COURSE_SQL);
//            onCreate(sqLiteDatabase);
//        }
//    }
}
