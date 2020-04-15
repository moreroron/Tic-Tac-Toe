package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean flag = false;
    int moves;

    String firstPlayerName;
    String secondPlayerName;
    TextView moveTextView;

    TextView firstPlayerTextView;
    TextView secondPlayerTextView;

    Intent intent;
    Intent getName;

    Button square1;
    Button square2;
    Button square3;
    Button square4;
    Button square5;
    Button square6;
    Button square7;
    Button square8;
    Button square9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getName = getIntent();
        firstPlayerName = getName.getStringExtra("firstPlayerName");
        secondPlayerName = getName.getStringExtra("secondPlayerName");

        Log.d("first name", firstPlayerName);
        Log.d("second name", secondPlayerName);

        intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putExtra("firstPlayerName", firstPlayerName);
        intent.putExtra("secondPlayerName", secondPlayerName);

        moves = 1;
        moveTextView = findViewById(R.id.movesTextView);
        firstPlayerTextView = findViewById(R.id.firstPlayerTextView);
        secondPlayerTextView = findViewById(R.id.secondPlayerTextView);

        moveTextView.setText(getString(R.string.move, moves));
        firstPlayerTextView.setText(firstPlayerName);
        secondPlayerTextView.setText(secondPlayerName);
        firstPlayerTextView.setAlpha(1);
        secondPlayerTextView.setAlpha((float) 0.3);

        square1 = findViewById(R.id.square1);
        square2 = findViewById(R.id.square2);
        square3 = findViewById(R.id.square3);
        square4 = findViewById(R.id.square4);
        square5 = findViewById(R.id.square5);
        square6 = findViewById(R.id.square6);
        square7 = findViewById(R.id.square7);
        square8 = findViewById(R.id.square8);
        square9 = findViewById(R.id.square9);

        square1.setOnClickListener(this);
        square2.setOnClickListener(this);
        square3.setOnClickListener(this);
        square4.setOnClickListener(this);
        square5.setOnClickListener(this);
        square6.setOnClickListener(this);
        square7.setOnClickListener(this);
        square8.setOnClickListener(this);
        square9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button clickedSquare = findViewById(v.getId());
        String value = clickedSquare.getText().toString();
        if (value.equals("")) {
            if (flag) clickedSquare.setText("O");
            else clickedSquare.setText("X");
            flag = !flag;
        } else {
            Toast.makeText(MainActivity.this, "Square is already taken", Toast.LENGTH_SHORT).show();
        }

        // checking if finish
        if (calculateWinner("X")) {
            intent.putExtra("winner", firstPlayerName);
            finish();
            startActivity(intent);
        }
        else if (calculateWinner("O")) {
            intent.putExtra("winner", secondPlayerName);
            finish();
            startActivity(intent);
        }

        // checking if draw
        if (moves == 9) {
            intent.putExtra("winner", getString(R.string.draw));
            finish();
            startActivity(intent);
        }

        // update player's move
        if (!flag) {
            firstPlayerTextView.setAlpha(1);
            secondPlayerTextView.setAlpha((float) 0.3);

        }
        else
        {
            firstPlayerTextView.setAlpha((float) 0.3);
            secondPlayerTextView.setAlpha(1);
        }

        moves++;
        moveTextView.setText(getString(R.string.move, moves));

    }

    public boolean calculateWinner(String contestant) {
        // checking rows
        if (square1.getText().toString().equals(contestant) && square2.getText().toString().equals(contestant) && square3.getText().toString().equals(contestant)) return true;
        if (square4.getText().toString().equals(contestant) && square5.getText().toString().equals(contestant) && square6.getText().toString().equals(contestant)) return true;
        if (square7.getText().toString().equals(contestant) && square8.getText().toString().equals(contestant) && square9.getText().toString().equals(contestant)) return true;

        // checking cols
        if (square1.getText().toString().equals(contestant) && square4.getText().toString().equals(contestant) && square7.getText().toString().equals(contestant)) return true;
        if (square2.getText().toString().equals(contestant) && square5.getText().toString().equals(contestant) && square8.getText().toString().equals(contestant)) return true;
        if (square3.getText().toString().equals(contestant) && square6.getText().toString().equals(contestant) && square9.getText().toString().equals(contestant)) return true;

        // checking dig
        if (square1.getText().toString().equals(contestant) && square5.getText().toString().equals(contestant) && square9.getText().toString().equals(contestant)) return true;
        if (square3.getText().toString().equals(contestant) && square5.getText().toString().equals(contestant) && square7.getText().toString().equals(contestant)) return true;

        return false;
    }
}
