package com.example.myapplication;

/**
 * Created by sanjitsingh on 11/6/16.
 */



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ViewOption extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_option);

        //Click this it will take you back home
        Button button1 = (Button) findViewById(R.id.Home);

        button1.setOnClickListener(new OnClickListener(){


            public void onClick(View v) {
                Intent i = new Intent(ViewOption.this,  MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),
                        "You are Home", Toast.LENGTH_LONG)
                        .show();
            }
        });


    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
