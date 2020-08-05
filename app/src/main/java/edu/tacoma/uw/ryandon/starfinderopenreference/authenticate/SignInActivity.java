package edu.tacoma.uw.ryandon.starfinderopenreference.authenticate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import edu.tacoma.uw.ryandon.starfinderopenreference.MainActivity;
import edu.tacoma.uw.ryandon.starfinderopenreference.R;

public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener {

    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);

        if(mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sign_in_fragment_container, new LoginFragment())
                    .commit();
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();;
        }
    }

    @Override
    public void login(String email, String pwd) {

        // in here we want to check if the email and pwd match a member in our members database
        //we should look courses to see how the sql values were analyzed in android studio.

        mSharedPreferences
                .edit()
                .putBoolean(getString(R.string.LOGGEDIN), true)
                .commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}