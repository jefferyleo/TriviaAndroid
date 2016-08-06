package com.quizeapp.Adepter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quizeapp.R;
import com.quizeapp.models.Data;

import java.util.ArrayList;

/**
 * The Adapter class for the ListView displayed in the left navigation drawer.
 */
public class LeftNavAdapter extends BaseAdapter
{

	/** The items. */
	private ArrayList<Data> items;

	/** The context. */
	private Context context;

	/** The selection. */
	private int selection;

	/**
	 * Checks if is selection.
	 * 
	 * @return the int
	 */
	public int isSelection()
	{

        return selection;
	}

	/**
	 * Sets the selection.
	 * 
	 * @param selection
	 *            the new selection
	 */
	public void setSelection(int selection)
	{
		this.selection = selection;
		notifyDataSetChanged();
	}

	/**
	 * Instantiates a new left navigation adapter.
	 * 
	 * @param context
	 *            the context of activity
	 * @param items
	 *            the array of items to be displayed on ListView
	 */
	public LeftNavAdapter(Context context, ArrayList<Data> items)
	{
		this.context = context;
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return items.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Data getItem(int arg0)
	{
		return items.get(arg0);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
			convertView = LayoutInflater.from(context).inflate(
					R.layout.left_nav_item, null);

		Data f = getItem(position);
		TextView lbl = (TextView) convertView.findViewById(R.id.lbl);
		lbl.setText(f.getTexts()[0]);
if(selection==1||selection==0) {
    if (selection == position) {
        lbl.setTextColor(context.getResources().getColor(R.color.pink_txt));
        lbl.setCompoundDrawablesWithIntrinsicBounds(f.getResources()[1], 0,
                f.getResources()[2], 0);

        convertView.setBackgroundColor(context.getResources().getColor(
                R.color.white));
    } else {
        convertView.setBackgroundResource(0);
        lbl.setTextColor(context.getResources().getColor(
                R.color.black));
        lbl.setCompoundDrawablesWithIntrinsicBounds(f.getResources()[0], 0,
                f.getResources()[2], 0);
    }
}
		return convertView;
	}

}
