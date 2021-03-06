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
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME);
        String password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD);

        // concatenate username and password strings
        String credentials = username+"\n"+password;

        //set the .xml file as the activity layout
        setContentView(R.layout.activity_personal);

        // modify text view content
        TextView credentialText = (TextView) findViewById(R.id.credentialText);
        credentialText.setText(credentials);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_goToMaps:
                goToMaps();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToMaps(){

        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}
