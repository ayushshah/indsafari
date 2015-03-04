package com.example.pleasework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private ProgressBar bar;
	Intent intent7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_options_menu);

		setContentView(R.layout.activity_taj_mahal);
		bar = (ProgressBar) findViewById(R.id.progressBar1);

		ActionBar bar2 = getActionBar();
		bar2.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00008B")));
	
		
		TextView txt = (TextView) findViewById(R.id.welcomeText);
		Typeface font = Typeface.createFromAsset(getAssets(),
				"blackcherry.ttf");
		txt.setTypeface(font);

		bar.setProgress(0);
		new Thread(new Task()).start();

		 intent7 = new Intent(this, MyService.class);
		 startService(intent7);

		Toast.makeText(getApplicationContext(), "The journey begins...",
				Toast.LENGTH_LONG).show();

		new PrefetchData().execute();

	}

	private class PrefetchData extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected Void doInBackground(Void... arg0) {

			LoadData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			stopService(intent7);
			Intent i = new Intent(MainActivity.this, OptionsMenu.class);
			startActivity(i);

			finish();
		}

	}

	private void LoadData() {

		InputStream is = null;
		InputStream is2 = null;
		DataHelper db = new DataHelper(this);

		db.deleteAll();

		try {
			is = getAssets().open("datafile4.csv");
			is2 = getAssets().open("datafile6.csv");
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(
					is2));

			String line2;

			String line;

			while ((line2 = reader2.readLine()) != null) {
				String[] RowData = line2.split(",");
				db.insert2(RowData[0].trim(), RowData[1].trim(),
						RowData[2].trim(), RowData[3].trim(),
						RowData[4].trim(), RowData[5].trim(),
						RowData[6].trim(), RowData[7].trim(),
						RowData[8].trim(), RowData[9].trim());
			}

			while ((line = reader.readLine()) != null) {
				String[] RowData = line.split(",");
				db.insert(RowData[0].trim(), RowData[1].trim(),
						RowData[2].trim(), RowData[3].trim(),
						RowData[4].trim(), RowData[5].trim(),
						RowData[6].trim(), RowData[7].trim(), RowData[8].trim());
			}

		} catch (IOException ex) {
			Toast.makeText(getApplicationContext(),
					"Error in reading the operators file!", Toast.LENGTH_LONG)
					.show();
		}

	}

	class Task implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i <= 1000; i++) {
				final int value = i;
				try {
					Thread.sleep(35);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
				bar.setProgress(value);
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.taj_mahal, menu);
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
