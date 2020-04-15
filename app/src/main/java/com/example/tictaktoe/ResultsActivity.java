package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    Button newGameButton;
    Button rematchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        newGameButton = findViewById(R.id.newGameButton);
        rematchButton = findViewById(R.id.rematchButton);

        final Intent intent = getIntent();
        String winner = intent.getStringExtra("winner");
        final String firstPlayerName = intent.getStringExtra("firstPlayerName");
        final String secondPlayerName = intent.getStringExtra("secondPlayerName");
        TextView congrats = findViewById(R.id.congratsTextView);
        assert winner != null;
        if (winner.equals(getString(R.string.draw)))
            congrats.setText(getString(R.string.draw));
        else
            congrats.setText(getString(R.string.winner, winner));

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent newGameScreen = new Intent(ResultsActivity.this, RegisterActivity.class);
                startActivity(newGameScreen);
            }
        });

        rematchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent rematchScreen = new Intent(ResultsActivity.this, MainActivity.class);
                rematchScreen.putExtra("firstPlayerName", firstPlayerName);
                rematchScreen.putExtra("secondPlayerName", secondPlayerName);
                startActivity(rematchScreen);
            }
        });
    }
}
