package com.android.path;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ritesh on 25/03/17.
 */

public class HeaderButtonRecycleViewAdapter extends RecyclerView.Adapter {

    private ArrayList<String> buttonsArray;

    public HeaderButtonRecycleViewAdapter(ArrayList<String> buttonsArray) {
        this.buttonsArray = buttonsArray;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HeaderButtonViewHolder(new HeaderButtonView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HeaderButtonView)holder.itemView).displayItem(buttonsArray.get(position));
    }

    @Override
    public int getItemCount() {
        return buttonsArray.size();
    }

    private class HeaderButtonViewHolder extends RecyclerView.ViewHolder {

        public HeaderButtonViewHolder(View itemView) {
            super(itemView);
        }
    }
}
