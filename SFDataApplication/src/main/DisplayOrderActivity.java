package main;

import com.example.sfdataapplication.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DisplayOrderActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_order);
		
		Intent intent = getIntent();
	    String fileName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    SFObjectsBuilder objectBuilder = new SFObjectsBuilder();
	    SFDataAssetBuilder dataAssetBuilder = new SFDataAssetBuilder(objectBuilder);
	    dataAssetBuilder.createResource(dataAssetBuilder);
	    SFDataAssetFileReader dataAssetFileReader = new SFDataAssetFileReader(dataAssetBuilder);
	    dataAssetFileReader.readData(fileName);
	    SFObjectFileReader fileReader = new SFObjectFileReader(objectBuilder);
	    fileReader.readData(fileName);
	    // Create the text view
	    TextView textView1 = new TextView(this);
	    textView1.setTextSize(20);
	    TextView textView2 = new TextView(this);
	    textView2.setTextSize(20);
	    TextView textView3 = new TextView(this);
	    textView3.setTextSize(20);
	    TextView textView4 = new TextView(this);
	    textView4.setTextSize(20);
	    
//	    textView1.setText("Tire Mark: "+objectBuilder.getTireMark().getString());
//	    textView2.setText("Tire Type: "+objectBuilder.getTireType().getString());
//	    textView3.setText("Tire Dimension: "+objectBuilder.getTireDimension().getFloatValue());
//	    textView4.setText("Tire Amount: "+objectBuilder.getTireAmount().getIntValue());

	    textView1.setText("Tire Mark: "+dataAssetBuilder.getTireMark());
	    textView2.setText("Tire Type: "+dataAssetBuilder.getTireType());
	    textView3.setText("Tire Dimension: "+dataAssetBuilder.getTireDimension());
	    textView4.setText("Tire Amount: "+dataAssetBuilder.getTireAmount());
	    
	    LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
	    layout.addView(textView1);
	    layout.addView(textView2);
	    layout.addView(textView3);
	    layout.addView(textView4);
  
	}	
}
