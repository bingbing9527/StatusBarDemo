package com.qbb.statusbardemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 创建日期：2017/8/10 11:50
 *
 * @author Qian Bing Bing
 *         类说明：
 */

public class ToolbarStatusBarActivityAdapter extends RecyclerView.Adapter {

    public ToolbarStatusBarActivityAdapter(Context context) {

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        if (0 == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            TextView textView = new TextView(parent.getContext());
            textView.setTextColor(Color.RED);
            viewHolder = new ViewHolder(textView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (0 == position) {

        } else {
            ((TextView) viewHolder.itemView).setText("第" + (position - 1) + "个条目");
        }


    }

    @Override
    public int getItemCount() {
        return 101;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
