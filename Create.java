package com.example.medbox;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Create extends Activity {
	 
		String fname,lname,email;
		SQLiteDatabase db;
		 TableRow tableRow;
		   TextView textView,textView1,textView2,textView3,textView4,textView5;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_create);
	        
	      //Click this it will take you back home
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

	        
	        db=openOrCreateDatabase("MyDB1",MODE_PRIVATE, null);
	        db.execSQL("CREATE TABLE IF NOT EXISTS Student(fname VARCHAR,lname VARCHAR,email VARCHAR);");
	    }
	public void data(View view)
	{
	   
	  EditText edittext3=(EditText )findViewById(R.id.email);
	   
	  email=edittext3.getText().toString();
	  db.execSQL("INSERT INTO  Student VALUES('"+fname+"','"+lname+"','"+email+"');");
		
		 
	}
	public void showdata(View view)
	{
	    Cursor c=db.rawQuery("SELECT * from Student", null);
	     int count= c.getCount();
	    c.moveToFirst();
	    TableLayout tableLayout = new TableLayout(getApplicationContext());
	    tableLayout.setVerticalScrollBarEnabled(true);
	   TableRow tableRow;
	   TextView textView,textView1,textView2,textView3,textView4,textView5;
	   tableRow = new TableRow(getApplicationContext());
	    
	     
	    textView5=new TextView(getApplicationContext());
	  	textView5.setText("Firstname");
	  	textView5.setTextColor(Color.RED);
	  	textView5.setTypeface(null, Typeface.BOLD);
	  	textView5.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView5);
	   tableLayout.addView(tableRow);
	   
	     for (Integer j = 0; j < count; j++)
	     {
	         tableRow = new TableRow(getApplicationContext());
	          
	         textView3 = new TextView(getApplicationContext());
	         textView3.setText(c.getString(c.getColumnIndex("email")));
	          
	         textView3.setPadding(20, 20, 20, 20);
	          
	         tableRow.addView(textView3);
	         tableLayout.addView(tableRow);
	         c.moveToNext() ;
	       
	     }
	     setContentView(tableLayout);
	     
	db.close();
	}
	public void close(View view)
	{
	System.exit(0);	
	}
	}
