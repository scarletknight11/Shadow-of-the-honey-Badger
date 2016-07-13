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
Button button1 = (Button) findViewById(R.id.scanner);

button1.setOnClickListener(new OnClickListener(){

	 
	public void onClick(View v) {
		 Intent i = new Intent(MainActivity.this, Create.class);
		 startActivity(i);
	}
	});

Button button2 = (Button) findViewById(R.id.scanner2);

button2.setOnClickListener(new OnClickListener(){

	 
	public void onClick(View v) {
		 Intent i = new Intent(MainActivity.this, First.class);
		 startActivity(i);
	}
	});

 
Button button3 = (Button) findViewById(R.id.scanner3);

button3.setOnClickListener(new OnClickListener(){

	 
	public void onClick(View v) {
		 Intent i = new Intent(MainActivity.this, ViewOption.class);
		 startActivity(i);
	}
	});

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
	case R.id.scan1:
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
	setContentView(R.layout.activity_main);
	Button button4 = (Button) findViewById(R.id.scan1);

	button4.setOnClickListener(new OnClickListener(){

		 
		public void onClick(View v) {
			 Intent i = new Intent(MainActivity.this, ScanOptions.class);
			 startActivity(i);
		}
		});

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
}
	
