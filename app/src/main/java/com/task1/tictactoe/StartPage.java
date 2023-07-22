package com.task1.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {

    EditText playerOne = findViewById(R.id.playerOneName);
    EditText playerTwo = findViewById(R.id.playerTwoName);

    Button startGame = findViewById(R.id.playGame)

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()){
                    Toast.makeText(StartPage.this,
                            "Please input players name",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(StartPage.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                }

            }
        });
    }
}