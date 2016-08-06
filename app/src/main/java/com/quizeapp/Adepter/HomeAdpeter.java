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
 * The Adapter class for the ListView displayed on home Screen.
 */
public class HomeAdpeter extends BaseAdapter {

    /**
     * The items.
     */
    private ArrayList<Data> items;

    /**
     * The context.
     */
    private Context context;

    /**
     * The selection.
     */
    private int selection;
    private ViewHolder holder;

    /**
     * Checks if is selection.
     *
     * @return the int
     */
    public int isSelection() {

        return selection;
    }

    /**
     * Sets the selection.
     *
     * @param selection the new selection
     */
    public void setSelection(int selection) {
        this.selection = selection;
        notifyDataSetChanged();
    }

    /**
     * Instantiates a new Home List Adpter.
     *
     * @param context the context of activity
     * @param items   the array of items to be displayed on ListView
     */
    public HomeAdpeter(Context context, ArrayList<Data> items) {
        this.context = context;
        this.items = items;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return items.size();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Data getItem(int arg0) {
        return items.get(arg0);
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_home_list, null);
            holder = new ViewHolder();
        }


        Data f = getItem(position);
        holder.lbl = (TextView) convertView.findViewById(R.id.lb1);
        holder.lbl.setText(f.getTitle());
        if (position == 0) {
            TextView head = (TextView) convertView.findViewById(R.id.txt_topics);
            head.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    public class ViewHolder {
        TextView lbl;
    }
}
