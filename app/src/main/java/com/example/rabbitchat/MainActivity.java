package com.example.rabbitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static int LENGTH_ID = 4;
    private static int MAX_MESSAGE_LENGTH = 200;

    private Button btnMSG;
    private EditText inputID, InputText;

    DatabaseReference ref;

    private FirebaseRecyclerOptions<Message> options;
    private FirebaseRecyclerAdapter<Message, ViewHolder> adapter;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputID = findViewById(R.id.inputID);
        InputText = findViewById(R.id.InputText);

        btnMSG = findViewById(R.id.btnMSG);

        ref = FirebaseDatabase.getInstance().getReference().child("Messages");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputID.length() != LENGTH_ID) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Допустимое количество символов ID ("+ LENGTH_ID +")",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                if(InputText.length() > MAX_MESSAGE_LENGTH) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ваше сообщение привысило допустимый лимит символов ("+ MAX_MESSAGE_LENGTH +")",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                if(InputText.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Вы не ввели сообщение",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                int ID = Integer.parseInt(inputID.getText().toString());
                String MSG = InputText.getText().toString();

                String key = ref.push().getKey();
                ref.child(key).child("ID").setValue(ID);
                ref.child(key).child("MSG").setValue(MSG);

                InputText.setText("");
            }
        });

        options = new FirebaseRecyclerOptions.Builder<Message>().setQuery(ref, Message.class).build();
        adapter = new FirebaseRecyclerAdapter<Message, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Message message) {
                viewHolder.messageViewID.setText("ID: " + Integer.toString(message.getID()));
                viewHolder.messageViewText.setText("msg: " + message.getMSG());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout, parent, false);
                return new ViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}
