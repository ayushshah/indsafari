package com.example.pleasework;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends ActionBarActivity {
	DataHelper dh;
	List<String> names;
	final String[] items2 = new String[100];
	private static ArrayList<String> nameList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		ActionBar bar2 = getActionBar();
		bar2.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00008B")));
	
		
		dh = new DataHelper(this);
	 
		Spinner spinner = (Spinner) findViewById(R.id.spinner);

		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.states_array, R.layout.spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				String stateValue = ((TextView) (selectedItemView)).getText()
						.toString();

				names = dh.spinContent("type", "tblhotels", "state", stateValue);
				nameList = new ArrayList<String>();
				int i = 0;
				for (String name : names) {
					items2[i] = name;
					nameList.add(name);
					i++;
				}
				Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
				ArrayAdapter<String> a =new ArrayAdapter<String>(MainActivity2.this,android.R.layout.simple_spinner_item, nameList);
				a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner2.setAdapter(a);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}

		});

	}

	public void okay(View v) {
		String state = ((Spinner) (findViewById(R.id.spinner)))
				.getSelectedItem().toString();
		String type = ((Spinner) (findViewById(R.id.spinner2)))
				.getSelectedItem().toString();

		Intent intent = new Intent(this, ListOfHotels.class);
		intent.putExtra("data1", state);
		intent.putExtra("data2", type);
		intent.putExtra("data3", "Hotels");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}