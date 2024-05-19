package com.example.sem2project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    // pass string which i want to show
    private List<MyModel> list;
    private SelectListener listener;

    public CustomListAdapter(Context context, List<MyModel> list, SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.single_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.activityName.setText(String.valueOf(list.get(position).getActivityName()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void filterList(List<MyModel> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }
}
