package com.example.rabbitchat;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView messageViewName, messageViewText;

    public ViewHolder(@NonNull View itemView){
        super(itemView);

        messageViewName = itemView.findViewById(R.id.viewName);
        messageViewText = itemView.findViewById(R.id.viewText);
    }
}