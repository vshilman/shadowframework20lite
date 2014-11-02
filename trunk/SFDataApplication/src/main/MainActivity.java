package main;


import com.example.sfdataapplication.R;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	
	public final static String EXTRA_MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    /** Called when the user clicks the Confirm button */
    public void sendMessage(View view) {
    	
    	EditText editText1 = (EditText) findViewById(R.id.EditText01);
    	EditText editText2 = (EditText) findViewById(R.id.EditText02);
    	EditText editText3 = (EditText) findViewById(R.id.EditText03);
    	EditText editText4 = (EditText) findViewById(R.id.EditText04);
    	EditText editText5 = (EditText) findViewById(R.id.EditText05);
    	
    	if(editText1.getText().length()!=0 && editText2.getText().length()!=0 && 
    	   editText3.getText().length()!=0 && editText4.getText().length()!=0 && editText5.getText().length()!=0){
    	
    	SFObjectsBuilder objectBuilder = new SFObjectsBuilder(editText1,editText2,editText3,editText4);
    	SFDataAssetBuilder dataAssetBuilder = new SFDataAssetBuilder(objectBuilder);
    	dataAssetBuilder.createResource(dataAssetBuilder);
//    	SFObjectFileWriter fileSaving = new SFObjectFileWriter(objectBuilder);
    	String fileName = editText5.getText().toString().trim();
    	SFDataAssetFileWriter dataAssetFileWriter  = new SFDataAssetFileWriter(dataAssetBuilder);
    	dataAssetFileWriter.writeData(fileName);
//    	fileSaving.saveData(fileName);
    	Toast toastPositive = Toast.makeText(this, R.string.advertisement2, (short)1);
		toastPositive.show();
		
    	}
  
    	else{
    		Toast toastNegative = Toast.makeText(this, R.string.advertisement1, (short)1);
    		toastNegative.show();
    	}
    }
    
    /** Called when the user clicks the read order button */
    public void readOrder(View view) {
    	
    	EditText editText5 = (EditText) findViewById(R.id.EditText05);
    	
    	if(editText5.getText().length()!=0){
    	
    	String fileName = editText5.getText().toString().trim();
    	Intent intent = new Intent(this, DisplayOrderActivity.class);
    	intent.putExtra(EXTRA_MESSAGE, fileName);
    	this.startActivity(intent);
    	}
    	else{
    		Toast toastNegative = Toast.makeText(this, R.string.advertisement3, (short)1);
    		toastNegative.show();
    	  }
   }
    
    public void readSFBOrder(View view){
    	EditText editText5 = (EditText) findViewById(R.id.EditText05);
    	
    	if(editText5.getText().length()!=0){
        	
        	String fileName = editText5.getText().toString().trim();
        	Intent intent = new Intent(this, SFBOrderActivity.class);
        	intent.putExtra(EXTRA_MESSAGE, fileName);
        	this.startActivity(intent);
        	}
        	else{
        		Toast toastNegative = Toast.makeText(this, R.string.advertisement3, (short)1);
        		toastNegative.show();
        	  }
    }
}
