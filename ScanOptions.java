
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
import android.widget.Toast;

public class ScanOptions extends Activity {
	static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;	 
	static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
	private Uri fileUri;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_options);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan_options, menu);
		return true;
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


		public void BarCode(View v){
			try{
				Intent intent = new Intent(ACTION_SCAN);
				intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
				startActivityForResult(intent, 0);
			}catch (ActivityNotFoundException e){
				showDialog(ScanOptions.this, "No Scanner Found", 
			"Download a scanner code activity?", "Yes", "No").show();
		
		}
			
			
		}
		
		public void QRCode(View v){
			try{
				Intent intent = new Intent(ACTION_SCAN);
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
			}catch (ActivityNotFoundException e){
				showDialog(ScanOptions.this, "No Scanner Found", 
		"Download a scanner code activity?", "Yes", "No").show();
			}
		} 
		
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
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent intent){
			if(requestCode == 0){
				if(resultCode == RESULT_OK){
					String contents = intent.getStringExtra("SCAN_RESULT");
					String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
					Toast.makeText(this, "Content:" + contents + "Format:" + format,
							Toast.LENGTH_LONG).show();
					 
				} else if (resultCode == RESULT_CANCELED) {
	                // Handle cancel
	                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
	                toast.setGravity(Gravity.TOP, 25, 400);
	                toast.show();
	                
				}
			}
		}
	}


