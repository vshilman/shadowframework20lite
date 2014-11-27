package main;

import java.io.File;
import java.util.ArrayList;

import com.example.sfdataapplication.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DisplayFiles extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "MESSAGE";
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFOrders";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_files);
		
		final ListView listview = (ListView) findViewById(R.id.listView1);
		
		File f = new File(DIRECTORY);        
		File file[] = f.listFiles();
		final ArrayList<String> fileList = new ArrayList<String>();
		for (int i=0; i < file.length; i++)
		{	
			fileList.add(file[i].getName().toString());
			
		}
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, fileList);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				Log.d("Files", "FileName:" + item);
				if(item.endsWith(".sf")){
					Intent intent = new Intent(view.getContext(), DisplayOrderActivity.class);
			    	intent.putExtra(EXTRA_MESSAGE, item);
			    	startActivity(intent);
				}
				else if(item.endsWith(".sfb")){
					Intent intent = new Intent(view.getContext(), SFBOrderActivity.class);
			    	intent.putExtra(EXTRA_MESSAGE, item);
			    	startActivity(intent);
				}
				
			}
			
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_files, menu);
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
