package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}

