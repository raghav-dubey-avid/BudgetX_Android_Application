package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /*TODO
    Show how much to maintain budget for:
    Daily Spending
    Weekly Spending
    Monthly Spending
     */


    Button addButton;
    Button removeButton;
    EditText inputEditText;
    TextView resultsTextView;
    TagDBHandler dbHandler;
    Button secondActivityButton;
    TextView welcomeTextView;

    TextView previouslyInitTextView;
    Button previouslyInitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("BudgetX\\MainActivity");

        //region referencesToIds

        removeButton = (Button) findViewById(R.id.removeButton);
        inputEditText = (EditText) findViewById(R.id.amountEditText);
        resultsTextView = (TextView) findViewById(R.id.resultsTextView);
        dbHandler = new TagDBHandler(this, null, null, 1);
        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        previouslyInitTextView = (TextView) findViewById(R.id.previouslyInitTextView);
        previouslyInitButton = (Button) findViewById(R.id.previouslyInitButton);
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        //endregion

        //print the info in the SQLite table upon creating activity
        printDatabase();

        String firstName = HomeActivity.thisUser.getFirstName();
        welcomeTextView.setText("Welcome, " + firstName + ".");

        removeButton.setEnabled(false);

        //region Button Onclicks

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.dequeue();
                printDatabase();
            }
        });

        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(startIntent);
            }
        });

        //endregion

    } // end of onCreate

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        resultsTextView.setText(dbString);
        inputEditText.setText("");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}