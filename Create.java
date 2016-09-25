package com.example.medbox;


import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends ListActivity {

	private StudentOperations studentDBoperation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		
		
			


		studentDBoperation = new StudentOperations(this);
		studentDBoperation.open();

		List values = studentDBoperation.getAllStudents();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	public void addUser(View view) {

		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

		EditText text = (EditText) findViewById(R.id.editText1);
		Students stud = studentDBoperation.addStudent(text.getText().toString());

		adapter.add(stud);

	}

	public void deleteFirstUser(View view) {

		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
		Students stud = null;

		if (getListAdapter().getCount() > 0) {
			stud = (Students) getListAdapter().getItem(0);
			studentDBoperation.deleteStudent(stud);
			adapter.remove(stud);
		}

	}

	@Override
	protected void onResume() {
		studentDBoperation.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		studentDBoperation.close();
		super.onPause();
	
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

	}
}
