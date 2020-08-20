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



    public String getmUsername() {
        return mUsername;
    }



    public String getmPassword() {
        return mPassword;
    }



    public String getmFirstName() {
        return mFirstName;
    }



    public String getmLastName() {
        return mLastName;
    }





    public static final String FIRST_NAME ="first";
    public static final String LAST_NAME = "last";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";




    public Members(String memberFirst, String memberLast, String memberEmail, String memberUser, String memberPass) {


        if(memberEmail.length() < 6 || !memberEmail.contains("@")
                                       ) {
            throw new IllegalArgumentException("Incorrect email: Email must be 6 characters containing a @");
        }else if(memberPass.length() < 6) {
            throw new IllegalArgumentException("Password must be atleast 6 characters");
        }else if(memberEmail.equals(null) ){
            throw new NullPointerException("Null value for email ");
        }else if( memberPass.equals(null)){
            throw new NullPointerException("Null value for  password");
        }

        mFirstName = memberFirst;
        mLastName = memberLast;
        mEmail = memberEmail;
        mUsername = memberUser;
        mPassword = memberPass;

    }


}
