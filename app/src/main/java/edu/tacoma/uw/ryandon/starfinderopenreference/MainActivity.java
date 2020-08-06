package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.tacoma.uw.ryandon.starfinderopenreference.authenticate.SignInActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * The overriden onCreate method setting our layout to main activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OGLButton(View view) {
        Intent intent = new Intent(this, OGLPage.class);
        startActivity(intent);
    }

}

