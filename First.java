package com.example.medbox;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class First extends Activity {

	static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;	 
	static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
	private Uri fileUri;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		//Click this it will take you back home
		Button button1 = (Button) findViewById(R.id.Home);

		button1.setOnClickListener(new OnClickListener(){

			 
			public void onClick(View v) {
				 Intent i = new Intent(First.this,  MainActivity.class);
				 startActivity(i);
				 Toast.makeText(getApplicationContext(),
						 "You are Home", Toast.LENGTH_LONG)
				 .show();
			}
			});

		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}
	
	public void onCreate1(Bundle savedInstanceState) 
	{ 
	 		super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_first); 
			ScrollView sView = (ScrollView)findViewById(R.id.ScrollView02); 
			// Hide the Scollbar 
			sView.setVerticalScrollBarEnabled(false); 
			sView.setHorizontalScrollBarEnabled(false);

	  }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

//if barcode/qrcodee API is not installed it will ask you for the option to install it
	public void BarCode(View v){
		try{
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
			startActivityForResult(intent, 0);
		}catch (ActivityNotFoundException e){
			showDialog(First.this, "No Scanner Found", 
		"Download a scanner code activity?", "Yes", "No").show();
	
	}
		
		
	}
	
//if barcode/qrcode API is not installed it will ask you for the option to install it
	public void QRCode(View v){
		try{
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);
		}catch (ActivityNotFoundException e){
			showDialog(First.this, "No Scanner Found", 
	"Download a scanner code activity?", "Yes", "No").show();
		}
	} 
//Camera option to take picture of the medicine
	public void Camera(View v){
		try{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		}catch (ActivityNotFoundException e){
			 
		}
	}

	private static AlertDialog showDialog(final Activity act,
			CharSequence title, CharSequence message, 
			CharSequence buttonYes,
			CharSequence buttonNo) {
		
	AlertDialog.Builder dowloadDialog = new AlertDialog.Builder(act);
	dowloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Uri uri = Uri.parse("market://search?q=pname:" +
			"com.google.zxing.client.android");
				
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				try{
					act.startActivity(intent);
				} catch(ActivityNotFoundException e){
		
				}
				
			}
}).setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				 
				
			}
		});
		
		return dowloadDialog.show();
		
	}
	
//Click this button it will take you to the option to take a picture	
	protected void onActivityResult1(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	            Toast.makeText(this, "Image saved to:\n" +
	                     data.getData(), Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        	  
	        } else {
	            // Image capture failed, advise user
	        }
	    }
	}

/*Click this button it will take you to the option to Barcode/QRCode Scan
 *After a product is scanned it will take you to api.outpan.com/v2/products/ 
 * From there you will see the name, brand and information of the product that was scanned
 */
 	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		if(requestCode == 0){
			if(resultCode == RESULT_OK){
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				String apioutpan = "https://api.outpan.com/v2/products/" + contents+ "?apikey=139c8752bc1213a618d987b8195422d3";  

				Toast.makeText(this, "Content:" + contents + "Format:" + format,
				Toast.LENGTH_LONG).show();
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(apioutpan));
				startActivity(browserIntent);
	} else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                
			}
		}
	}
}
