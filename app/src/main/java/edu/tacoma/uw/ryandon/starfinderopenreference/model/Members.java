package edu.tacoma.uw.ryandon.starfinderopenreference.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Members implements Serializable {
    private String mMemberID;
    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mEmail;
    private String mPassword;

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }



    public String getmMemberID() {
        return mMemberID;
    }

    public void setmMemberID(String mMemberID) {
        this.mMemberID = mMemberID;
    }





    public static final String FIRST_NAME ="first";
    public static final String LAST_NAME = "last";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";




    public Members(String memberFirst, String memberLast, String memberEmail, String memberUser, String memberPass) {

        mFirstName = memberFirst;
        mLastName = memberLast;
        mEmail = memberEmail;
        mUsername = memberUser;

        mPassword = memberPass;

    }

    public static List<Members> parseCourseJson(String courseJson) throws JSONException {
        List<Members> courseList = new ArrayList<>();

        if(courseJson != null){
            JSONArray arr = new JSONArray(courseJson);


            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                Members member = new Members(
                        obj.getString(Members.FIRST_NAME),
                        obj.getString(Members.LAST_NAME),
                        obj.getString(Members.USERNAME),
                        obj.getString(Members.EMAIL),
                        obj.getString(Members.PASSWORD));
                courseList.add(member);
            }
        }

        return courseList;
    }
}
