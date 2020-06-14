package com.example.rabbitchat;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView messageViewID, messageViewText;

    public ViewHolder(@NonNull View itemView){
        super(itemView);

        messageViewID = itemView.findViewById(R.id.viewID);
        messageViewText = itemView.findViewById(R.id.viewText);
    }
}
