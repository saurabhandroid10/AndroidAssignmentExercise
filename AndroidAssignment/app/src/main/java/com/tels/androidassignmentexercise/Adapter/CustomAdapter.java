package com.tels.androidassignmentexercise.Adapter;

/**
 * Created by saurabhgarg on 17/02/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tels.androidassignmentexercise.Model.Row;
import com.tels.androidassignmentexercise.R;

import java.util.List;

/**
 * This class is responsible to inflate list with title ,description and image.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private List<Row> mRowData;
    private Context mContext;

    public CustomAdapter(List<Row> rowData, Context context) {
        mRowData = rowData;
        mContext = context;
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder holder, int position) {

        holder.tvTitle.setText(mRowData.get(position).getTitle());
        holder.tvDescription.setText(mRowData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mRowData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDescription;
        public ImageView ivThumbnail;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            ivThumbnail = (ImageView) view.findViewById(R.id.ivThumbnail);
        }
    }
}
