package com.quizeapp.Adepter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quizeapp.Question;
import com.quizeapp.R;
import com.quizeapp.models.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * The Adapter class for the ListView displayed on Topics Screen.
 */

public class AdpterTopices extends BaseAdapter implements Filterable {

    ViewHolder holder;
    /**
     * The items.
     */
    // for search variable
    private ItemFilter mFilter = new ItemFilter();
    private ArrayList<Data> items;
    private ArrayList<Data> itemsCopy;
    private ArrayList<Data> filteredList = new ArrayList<Data>();


    /**
     * The context.
     */
    private Context context;

    /**
     * The selection.
     */
    private int selection;

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
     * Instantiates a new Topics Adapter
     *
     * @param context the context of activity
     * @param items   the array of items to be displayed on ListView
     */
    public AdpterTopices(Context context, ArrayList<Data> items) {
        this.context = context;
        this.items = items;
        itemsCopy = items;
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
            final Data f = getItem(position);
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_topices, null);
        }


        Data f = getItem(position);
        holder.lbl = (TextView) convertView.findViewById(R.id.lb1);
        holder.btn_log = (RelativeLayout) convertView.findViewById(R.id.btn_log);
        holder.rel_logs = (LinearLayout) convertView.findViewById(R.id.rel_logs);
        holder.btn_play = (RelativeLayout) convertView.findViewById(R.id.btn_play);
        holder.btn_raking = (RelativeLayout) convertView.findViewById(R.id.btn_raking);
        holder.btn_Challang = (RelativeLayout) convertView.findViewById(R.id.btn_Challang);
        holder.btn_discissin = (RelativeLayout) convertView.findViewById(R.id.btn_discissin);

        holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Question.class));
            }
        });
        holder.btn_raking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Question.class));
            }
        });
        holder.btn_Challang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   context.startActivity(new Intent(context, Question.class));
            }
        });
        holder.btn_discissin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //        context.startActivity(new Intent(context, Question.class));
            }
        });

        holder.lbl.setText(f.getTitle());
        if (f.isShow()) {
            holder.rel_logs.setVisibility(View.VISIBLE);
        } else {
            holder.rel_logs.setVisibility(View.GONE);
        }


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class ViewHolder {
        TextView lbl;
        RelativeLayout btn_log;
        LinearLayout rel_logs;
        RelativeLayout btn_play, btn_raking, btn_Challang, btn_discissin;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            int count = itemsCopy.size();
            final List<Data> tempItems = new ArrayList<Data>(
                    count);
            for (int i = 0; i < count; i++) {
                if (itemsCopy.get(i).getTitle().toLowerCase()
                        .contains(filterString)) {
                    tempItems.add(itemsCopy.get(i));
                }
            }

            filteredList = new ArrayList<Data>();
            filteredList = (ArrayList<Data>) tempItems;
            results.values = tempItems;
            results.count = tempItems.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            items = (ArrayList<Data>) filterResults.values;
            notifyDataSetChanged();
        }

    }

}