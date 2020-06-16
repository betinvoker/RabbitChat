package com.example.rabbitchat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Build;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Chat extends AppCompatActivity {

    private static int MAX_MESSAGE_LENGTH = 200;

    private Button btnMSG;
    private EditText InputText;
    private String username;

    DatabaseReference ref;

    private FirebaseRecyclerOptions<Message> options;
    private FirebaseRecyclerAdapter<Message, ViewHolder> adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        username = getIntent().getStringExtra("username");

        InputText = findViewById(R.id.InputText);

        btnMSG = findViewById(R.id.btnMSG);

        final Date currentDate = new Date();
        final DateFormat timeFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault());
        final String timeText = timeFormat.format(currentDate);

        ref = FirebaseDatabase.getInstance().getReference().child("Messages");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnMSG.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if(checkingMessageLength(InputText) == true) return;

                String NAME = username;
                String MSG = InputText.getText().toString();
                String TIME = timeText;

                String key = ref.push().getKey();
                ref.child(key).child("NAME").setValue(NAME);
                ref.child(key).child("MSG").setValue(MSG);
                ref.child(key).child("TIME").setValue(TIME);

                InputText.setText("");
            }
        });

        options = new FirebaseRecyclerOptions.Builder<Message>().setQuery(ref, Message.class).build();
        adapter = new FirebaseRecyclerAdapter<Message, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Message message) {
                viewHolder.messageViewName.setText(message.getNAME());
                viewHolder.messageViewText.setText(message.getMSG());
                viewHolder.messageViewTime.setText(message.getTIME());
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

    private boolean checkingMessageLength(EditText inputText) {

        if(InputText.length() > MAX_MESSAGE_LENGTH) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Ваше сообщение привысило допустимый лимит символов ("+ MAX_MESSAGE_LENGTH +")." +
                            "\nВы ввели (" + InputText.length() + ").",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return true;
        }

        if(InputText.length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Вы не ввели сообщение",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return true;
        }
        return false;
    }
}
