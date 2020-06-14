package com.example.rabbitchat;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView message;

    public ViewHolder(View itemView){
        super(itemView);

        message = itemView.findViewById(R.id.myMessage);
    }
}
