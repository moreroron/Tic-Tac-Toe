package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    TextView firstPlayerField;
    TextView secondPlayerField;

    Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstPlayerField = findViewById(R.id.firstPlayerField);
        secondPlayerField = findViewById(R.id.secondPlayerField);
        startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstPlayerName = firstPlayerField.getText().toString();
                final String secondPlayerName = secondPlayerField.getText().toString();

                if (!firstPlayerName.equals("") && !secondPlayerName.equals("")) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("firstPlayerName", firstPlayerName);
                    intent.putExtra("secondPlayerName", secondPlayerName);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "both fields must be supplied", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
