package main;

import com.example.sfdataapplication.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SFBOrderActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sfborder);
		
		Intent intent = getIntent();
	    String fileName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    
	    SFBOrderReader sfbOrderReader = new SFBOrderReader();
	    System.out.println();
	    TextView textView1 = new TextView(this);
	    textView1.setTextSize(20);
	    TextView textView2 = new TextView(this);
	    textView2.setTextSize(20);
	    TextView textView3 = new TextView(this);
	    textView2.setTextSize(20);
	    textView1.setText(sfbOrderReader.createSFBOrder(fileName)[0].toString());
	    textView2.setText(sfbOrderReader.createSFBOrder(fileName)[1].toString());
	    textView2.setText(sfbOrderReader.createSFBOrder(fileName)[2].toString());
	    LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
	    layout.addView(textView1);
	    layout.addView(textView2);
	    layout.addView(textView3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sfborder, menu);
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
}
