package edu.tacoma.uw.ryandon.starfinderopenreference.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.ryandon.starfinderopenreference.R;
import edu.tacoma.uw.ryandon.starfinderopenreference.model.Members;

public class MembersDB {
    private MembersDBHelper mMembersDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public MembersDB(Context context) {
        mMembersDBHelper = new MembersDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mMembersDBHelper.getWritableDatabase();
    }


    /**
     * Inserts the members into the local sqlite table. Returns true if successful, false otherwise.
     * @param id
     * @param firstName
     * @param lastName
     * @param username
     * @param email
     * @param password
     * @return true or false
     */
    public boolean insertMembers(String id, String firstName, String lastName, String username, String email, String password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("MembersID", id);
        contentValues.put("FirstName", firstName);
        contentValues.put("LastName", lastName);
        contentValues.put("Username", username);
        contentValues.put("Email", email);
        contentValues.put("Password", password);

        long rowId = mSQLiteDatabase.insert("Members", null, contentValues);
        return rowId != -1;
    }

    /**
     * Delete all the data from the Members
     */
    public void deleteMembers() {
        mSQLiteDatabase.delete("Members", null, null);
    }
    /**
     * Returns the list of members from the local Members table.
     * @return list
     */
    public List<Members> getMembers() {

        String[] columns = {
                "MembersID", "FirstName", "LastName", "Username", "Email", "Password"
        };

        Cursor c = mSQLiteDatabase.query(
                "Members",  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<Members> list = new ArrayList<Members>();
        for (int i=0; i<c.getCount(); i++) {
            String id = c.getString(0);
            String firstName = c.getString(1);
            String lastName = c.getString(2);
            String username = c.getString(3);
            String email = c.getString(4);
            String password = c.getString(5);
            Members member = new Members(id, firstName, lastName, username,email,password);
            list.add(member);
            c.moveToNext();
        }

        return list;
    }




    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Members.db";



    class MembersDBHelper extends SQLiteOpenHelper {

        private final String CREATE_MEMBERS_SQL;

        private final String DROP_MEMBERS_SQL;

        public MembersDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_MEMBERS_SQL = context.getString(R.string.CREATE_MEMBERS_SQL);
            DROP_MEMBERS_SQL = context.getString(R.string.DROP_MEMBERS_SQL);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_MEMBERS_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_MEMBERS_SQL);
            onCreate(sqLiteDatabase);
        }
    }
}
