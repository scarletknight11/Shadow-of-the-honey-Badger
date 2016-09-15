package com.example.medbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class Create extends Activity {

/** Called when the activity is first created. */

@Override

public void onCreate(Bundle savedInstanceState) {

super.onCreate(savedInstanceState);

setContentView(R.layout.activity_create);



//Click this button. it will take you back to the home pagw
Button button1 = (Button) findViewById(R.id.Home);

button1.setOnClickListener(new OnClickListener(){

	 
	public void onClick(View v) {
		 Intent i = new Intent(Create.this,  MainActivity.class);
		 startActivity(i);
		 Toast.makeText(getApplicationContext(),
				 "You are Home", Toast.LENGTH_LONG)
		 .show();
	}
	});

}

@Override

public boolean onCreateOptionsMenu(Menu menu) {

MenuInflater inflater = getMenuInflater();

inflater.inflate(R.menu.mymenu, menu);

return true;

}


@Override

public boolean onOptionsItemSelected(MenuItem item) {

switch (item.getItemId()) {

case R.id.open:

showToast("Open Clicked");

return true;

case R.id.savecurrent:

showToast("Save current Clicked");

return true;

case R.id.saveall:

showToast("Save All Clicked");

return true;

case R.id.closecurrent:

showToast("Close current Clicked");

return true;

case R.id.closeall:

showToast("Close all Clicked");

return true;

default:

return super.onOptionsItemSelected(item);

}

}

public void showToast(String message)

{

Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);

toast.show();

}
public boolean onCreateOptionsMenu1(Menu menu) {

menu.add(1, 1, 0, "Open the file");

menu.add(1, 2, 1, "Save the file");

menu.add(1, 3, 2, "Close the file");

return true;

}
public void onCreate1(Bundle savedInstanceState) 
{ 
 		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create); 
		ScrollView sView = (ScrollView)findViewById(R.id.ScrollView03); 
		// Hide the Scollbar 
		sView.setVerticalScrollBarEnabled(false); 
		sView.setHorizontalScrollBarEnabled(false);

  }


}

