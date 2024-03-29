package com.task1.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {
    MediaPlayer gameSong;

    EditText playerOne ;
    EditText playerTwo ;

    Button startGame ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        playerOne = findViewById(R.id.playerOneName);
        playerTwo  = findViewById(R.id.playerTwoName);
        startGame = findViewById(R.id.playGame);



        gameSong = MediaPlayer.create(StartPage.this, R.raw.game_level_music);

        gameSong.start();
        gameSong.release();

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