package com.example.sem2project;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    //object for view
    public TextView title,activityName;
    public CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        activityName = itemView.findViewById(R.id.activityName);
        cardView = itemView.findViewById(R.id.main_container);
    }

//
}
