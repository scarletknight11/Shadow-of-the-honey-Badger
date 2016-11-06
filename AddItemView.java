package com.example.myapplication;

/**
 * Created by sanjitsingh on 11/6/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemView extends Activity implements View.OnClickListener{

    /*
    This method is where the activity layout is set up
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_view);

        //Click this it will take you back home
        Button button1 = (Button) findViewById(R.id.Home);

        button1.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                Intent i = new Intent(AddItemView.this,  MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),
                        "You are Home", Toast.LENGTH_LONG)
                        .show();
            }
        });




    //obtain button and attach press listener to it
        Button addItem = (Button) findViewById(R.id.add_item);
        addItem.setOnClickListener(this);
    }

    /*
    This on-click method listens for taps to what it is attached to
     */
    @Override
    public void onClick(View v) {

        //edittext object of the item entered
        EditText text = (EditText) findViewById(R.id.item_text);

        //create new intent to put the item in
        Intent data = new Intent();
        data.putExtra("text", text.getText().toString());

        //attach data to return call and go back to main view
        setResult(RESULT_OK, data);
        finish();
    }
}
