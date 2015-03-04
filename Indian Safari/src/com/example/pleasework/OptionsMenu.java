package com.example.pleasework;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class OptionsMenu extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00008B")));
		setContentView(R.layout.activity_options_menu);
	}

	
	public void takeToHotels (View v){
		Intent intent = new Intent(OptionsMenu.this, MainActivity2.class);
		startActivity(intent);
	}
	
	public void takeToATO (View v){
		Intent intent = new Intent(OptionsMenu.this, FurtherDetails.class);
		intent.putExtra("Type", "Adventure Tour Operator");
		startActivity(intent);
	}
	
	public void takeToDTO (View v){
		Intent intent = new Intent(OptionsMenu.this, FurtherDetails.class);
		intent.putExtra("Type", "Domestic Tour Operator");
		startActivity(intent);
	}
	
	public void takeToTTO (View v){
		Intent intent = new Intent(OptionsMenu.this, FurtherDetails.class);
		intent.putExtra("Type", "Tourist Transport Operator");
		startActivity(intent);
	}
	
	public void takeToTA (View v){
		Intent intent = new Intent(OptionsMenu.this, FurtherDetails.class);
		intent.putExtra("Type", "Travel Agent");
		startActivity(intent);
	}
	
	public void takeToITO (View v){
		Intent intent = new Intent(OptionsMenu.this, FurtherDetails.class);
		intent.putExtra("Type", "Inbound Tour Operator");
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_menu, menu);
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
