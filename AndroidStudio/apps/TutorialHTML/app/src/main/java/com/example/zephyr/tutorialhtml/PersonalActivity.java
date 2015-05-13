package com.example.zephyr.tutorialhtml;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class PersonalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the two extras containg credentials from the intent
        Intent i = getIntent();
        String username = i.getStringExtra(LoginActivity.EXTRA_USERNAME);
        String password = i.getStringExtra(LoginActivity.EXTRA_PASSWORD);

        // concatenate username and password strings
        String credentials = username+"\n"+password;

        // create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(credentials);

        // set the text view as the activity layout
        setContentView(textView);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
