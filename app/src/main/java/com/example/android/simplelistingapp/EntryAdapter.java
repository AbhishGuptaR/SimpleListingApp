package com.example.android.simplelistingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {

    private String[] data;
    public EntryAdapter(String[] data){
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.entry_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView entryname,entryamount,entrytime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            entryname = (TextView) itemView.findViewById(R.id.entrynametext);
            entrytime = (TextView) itemView.findViewById(R.id.entrytimetext);
            entrytime = (TextView) itemView.findViewById(R.id.entryamounttext);

        }
    }
}
