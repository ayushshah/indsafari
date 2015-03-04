package com.example.pleasework;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	Context mContext;
	private ArrayList<String> myListItems;
	private LayoutInflater myLayoutInflater;

	public ListAdapter(Context context, ArrayList<String> arrayList) {
		mContext = context;
		myListItems = arrayList;
		myLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myListItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static class ViewHolder {

		protected TextView itemName;
	}

	public void filter(String charText, ArrayList<String> arrayList) {

		myListItems = arrayList;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (view == null) {
			holder = new ViewHolder();

			view = myLayoutInflater.inflate(R.layout.listview_item, null);
			holder.itemName = (TextView) view.findViewById(R.id.txtname);

			view.setTag(holder);
		} else {

			holder = (ViewHolder) view.getTag();
		}

		String stringItem = myListItems.get(position).toString();
		if (stringItem != null) {
			if (holder.itemName != null) {
				// set the item name on the TextView
				holder.itemName.setText(stringItem);
			}
		}

/*		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, Details.class);
				intent.putExtra("data", myListItems.get(position).toString());
				mContext.startActivity(intent);
			}
		});
		
*/		return view;
	}

}
