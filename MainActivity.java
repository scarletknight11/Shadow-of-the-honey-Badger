import com.example.Caregiver.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable; 

public class MainActivity extends Activity  {
	
static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;	 
static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
private Uri fileUri;



@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.testmenu, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item){
	super.onOptionsItemSelected(item);
	switch(item.getItemId()){
	case R.id.Create:
		CreateMenuItem();
		break;
	case R.id.scan:
		scanMenuItem();
		break;
	case R.id.View:
		ViewMenuItem();
		break;
	}
	return true;
	}
private void CreateMenuItem(){
	new AlertDialog.Builder(this)
	.setTitle("Create")
	.setMessage("This is a create dialog")
	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
	}).show();
}
 
private void scanMenuItem(){
	try{
		Intent intent = new Intent(ACTION_SCAN);
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}catch (ActivityNotFoundException e){
		showDialog(MainActivity.this, "No Scanner Found", 
"Download a scanner code activity?", "Yes", "No").show();
	}
}

private void ViewMenuItem(){
	new AlertDialog.Builder(this)
	.setTitle("View")
	.setMessage("This is a  dialog")
	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
	}).show();
}

	public void scanBar(View v){
		try{
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
			startActivityForResult(intent, 0);
		}catch (ActivityNotFoundException e){
			showDialog(MainActivity.this, "No Scanner Found", 
		"Download a scanner code activity?", "Yes", "No").show();
		
		}
		
		
	}
	
	public void scanQR(View v){
		try{
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);
		}catch (ActivityNotFoundException e){
			showDialog(MainActivity.this, "No Scanner Found", 
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
	
	
