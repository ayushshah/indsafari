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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class FurtherDetails extends ActionBarActivity {
	String type, state, region, city;
	DataHelper dh;
	List<String> names;
	List<String> names2;
	final String[] items2 = new String[100];
	final String[] items3 = new String[100];
	final String[] items4 = new String[100];
	private static ArrayList<String> nameList;
	private static ArrayList<String> nameList2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_further_details);

		ActionBar bar2 = getActionBar();
		bar2.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#00008B")));

		dh = new DataHelper(this);
		Intent intent = getIntent();
		type = intent.getStringExtra("Type");

		Spinner spinner = (Spinner) findViewById(R.id.spinRegion);

		names2 = dh.spinContent("region", "tbloperators", "type", type);
		nameList2 = new ArrayList<String>();
		int i = 0;
		for (String name : names2) {
			items2[i] = name;
			nameList2.add(name);
			i++;
		}

		ArrayAdapter<String> a = new ArrayAdapter<String>(FurtherDetails.this,
				android.R.layout.simple_spinner_item, nameList2);
		a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(a);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				String regionValue = ((TextView) (selectedItemView)).getText()
						.toString();

				names = dh.spinContent2("state", "tbloperators", "region",
						regionValue, "type", type);
				nameList = new ArrayList<String>();
				int i = 0;
				for (String name : names) {
					items2[i] = name;
					nameList.add(name);
					i++;
				}
				Spinner spinner2 = (Spinner) findViewById(R.id.spinState);
				ArrayAdapter<String> a = new ArrayAdapter<String>(
						FurtherDetails.this,
						android.R.layout.simple_spinner_item, nameList);
				a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner2.setAdapter(a);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here

			}

		});

		Spinner spinner2 = (Spinner) findViewById(R.id.spinState);
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				String regionValue = ((TextView) (selectedItemView)).getText()
						.toString();

				names = dh.spinContent2("city", "tbloperators", "state",
						regionValue, "type", type);
				nameList = new ArrayList<String>();
				int i = 0;
				for (String name : names) {
					items3[i] = name;
					nameList.add(name);
					i++;
				}
				Spinner spinner3 = (Spinner) findViewById(R.id.spinCity);
				ArrayAdapter<String> a = new ArrayAdapter<String>(
						FurtherDetails.this,
						android.R.layout.simple_spinner_item, nameList);
				a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner3.setAdapter(a);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}

		});

	}

	public void takeToList(View v) {

		state = ((Spinner) (findViewById(R.id.spinState))).getSelectedItem()
				.toString();
		region = ((Spinner) (findViewById(R.id.spinRegion))).getSelectedItem()
				.toString();
		city = ((Spinner) (findViewById(R.id.spinCity))).getSelectedItem()
				.toString();

		Intent intent = new Intent(this, ListOfHotels.class);
		intent.putExtra("data1", region);
		intent.putExtra("data2", city);
		intent.putExtra("data3", type);
		intent.putExtra("data4", state);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.further_details, menu);
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
