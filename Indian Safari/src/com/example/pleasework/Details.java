package com.example.pleasework;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		ActionBar bar2 = getActionBar();
		bar2.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#00008B")));

		Intent intent = getIntent();
		String name = intent.getStringExtra("data1");
		String state = intent.getStringExtra("data2");
		String origin = intent.getStringExtra("data3");
		if (origin.equals("Hotels")) {
			((TextView) findViewById(R.id.textName)).setText(name);
			DataHelper dh = new DataHelper(this);
			Hotels hotel = dh.selectAllDetails(name, state);

			((TextView) findViewById(R.id.textAddress)).setText(hotel
					.getAddress().toString());
			((TextView) findViewById(R.id.textMail)).setText(hotel.getEmail()
					.toString());
			((TextView) findViewById(R.id.textPhone)).setText(hotel.getPhone()
					.toString());
			((TextView) findViewById(R.id.textRooms)).setText(hotel.getRooms()
					.toString() + " Rooms");
			((TextView) findViewById(R.id.textType)).setText(hotel.getType()
					.toString());
			((TextView) findViewById(R.id.textWebsite)).setText(hotel
					.getWebsite().toString());

		} else {
			((TextView) findViewById(R.id.textName)).setText(name);
			DataHelper dh = new DataHelper(this);
			Operators operator = dh.selectAllDetails2(name, state, origin);
			
			((TextView) findViewById(R.id.textAddress)).setText(operator
					.getAddress().toString());
			((TextView) findViewById(R.id.textMail)).setText(operator.getMail()
					.toString());
			((TextView) findViewById(R.id.textPhone)).setText(operator
					.getPhone().toString());
			((TextView) findViewById(R.id.textRooms))
					.setText("Contact Person: "
							+ operator.getPerson().toString());
			((TextView) findViewById(R.id.textType)).setText(operator.getType()
					.toString());
			((TextView) findViewById(R.id.textWebsite)).setText(operator
					.getFax().toString());
			
		}

	}

	public void visitWeb(View v) {

		String web = ((TextView) findViewById(R.id.textWebsite)).getText()
				.toString();
		if (!(web.equals("NA"))) {
			if (web.substring(0, 3).equals("www")) {
				web = "http://" + web.substring(4) + "/";
			} else {
				web = "http://" + web + "/";
			}
			try {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
				startActivity(i);
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Sorry, No website",
						Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(getApplicationContext(), "No website",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void sendMail(View v) {

		String mailAddress = ((TextView) findViewById(R.id.textMail)).getText()
				.toString();
		if (!(mailAddress.equals("NA"))) {
			String[] TO = { ((TextView) findViewById(R.id.textMail)).getText()
					.toString() };
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setData(Uri.parse("mailto:"));
			emailIntent.setType("text/plain");
			emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry");
			emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

			try {
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
				finish();
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(getApplicationContext(),
						"There is no email client installed.",
						Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(getApplicationContext(), "No email Address",
					Toast.LENGTH_SHORT).show();

		}
	}

	public void makeCall(View v) {
		TextView conNumber = (TextView) findViewById(R.id.textPhone);
		String mobileNo = conNumber.getText().toString();
		mobileNo = mobileNo.trim();

		if ((mobileNo.charAt(0) != '0')
				&& !((mobileNo.charAt(0) == '9') && (mobileNo.charAt(1) == '1'))) {
			mobileNo = '0' + mobileNo;
		}

		int pos = mobileNo.indexOf("-", 0);
		if (pos != -1) {
			int pos2 = mobileNo.indexOf("-", pos + 1);
			if (pos2 != -1) {
				mobileNo = mobileNo.substring(0, pos2 - pos);
			}
		}

		String uri = "tel:" + mobileNo;

		Intent intent2 = new Intent(Intent.ACTION_CALL);
		intent2.setData(Uri.parse(uri));
		startActivity(intent2);

	}

	public void sendCall(View v) {
		TextView conNumber = (TextView) findViewById(R.id.textPhone);
		String mobileNo = conNumber.getText().toString();
		mobileNo = mobileNo.trim();

		if ((mobileNo.charAt(0) != '0')
				&& !((mobileNo.charAt(0) == '9') && (mobileNo.charAt(1) == '1'))) {
			mobileNo = '0' + mobileNo;
		}

		int pos = mobileNo.indexOf("-", 0);
		if (pos != -1) {
			int pos2 = mobileNo.indexOf("-", pos + 1);
			if (pos2 != -1) {
				mobileNo = mobileNo.substring(0, pos2 - pos);
			}
		}
		String uri = "smsto:" + mobileNo;
		Intent intent2 = new Intent(Intent.ACTION_SENDTO);
		intent2.setData(Uri.parse(uri));
		startActivity(intent2);
	}

	public void visitMap(View v) {
		Intent intent4 = new Intent(this, MapActivity.class);
		String hotelAddress = ((TextView) (findViewById(R.id.textAddress)))
				.getText().toString();
		intent4.putExtra("hotelAddress", hotelAddress);
		startActivity(intent4);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
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
