package com.example.rabbitchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int MAX_USERNAME_LENGTH = 20;

    private Button btnSettings;
    private EditText InputUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSettings = findViewById(R.id.btnSettings);
        InputUserName = findViewById(R.id.InputUserName);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkingUsername(InputUserName) == true) return;

                Intent intent = new Intent(MainActivity.this, Chat.class);
                intent.putExtra("username", InputUserName.getText().toString());
                startActivity(intent);
            }
        });

    }

    private boolean checkingUsername(EditText InputUserName) {

        if(InputUserName.length() > MAX_USERNAME_LENGTH) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Ваше имя привысило допустимую длинну (" +MAX_USERNAME_LENGTH+")." +
                            "\nВы ввели (" + InputUserName.length() + ").",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return true;
        }

        if(InputUserName.length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Вы не ввели свой псевдоним",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return true;
        }
        return false;
    }
}
