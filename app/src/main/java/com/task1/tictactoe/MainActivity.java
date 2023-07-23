package com.task1.tictactoe;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.task1.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOneScore, playerTwoScore;

    private int playerOneTotal, playerTwoTotal;
    private Button resetGame;

    private Button [] buttons = new Button[9];

    ActivityMainBinding binding;


     int [][] winningCombinations = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6},{1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };
    private int [] boardPosition = {3, 3, 3, 3, 3, 3, 3, 3, 3};
    private boolean activePlayer = false;
    private int totalSelecxtedBox = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");


        playerOneScore = findViewById(R.id.oneScore);
        playerTwoScore = findViewById(R.id.twoScore);
        resetGame = findViewById(R.id.resetGame);

        binding.firstPlayer.setText(getPlayerOneName);
        binding.secondPlayer.setText(getPlayerTwoName);



        for(int i=0; i< buttons.length; i++ ){
            String buttonID = "btn_" + i ;
            int btnResourceID = getResources().getIdentifier(
                    buttonID, "id",getPackageName()
            );

            buttons[i] = (Button) findViewById(btnResourceID);

            buttons[i].setOnClickListener(this);

        }
        activePlayer = true;

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {


        if(!((Button)v).getText().toString().equals("")) {
            Toast.makeText(this, "This box is filled", Toast.LENGTH_SHORT).show();
            return;
        }

        //gets that button id
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        //gets the last char of the id which indicates the index of the box
        int gamePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,
                buttonID.length()));

        if(activePlayer){
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_corners);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_box);
            ((Button) v).setText("X");
            ((Button)v).setTextColor(getColor(R.color.blue));
            //sets the value of that index to be 0
            boardPosition[gamePointer] = 0;

        }else{
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_corners);
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_box);
            ((Button) v).setText("O");
            ((Button)v).setTextColor(getColor(R.color.red));
            boardPosition[gamePointer] = 1;
        }
        totalSelecxtedBox++;

        if(checkWinner()){
            if(activePlayer){
                playerOneTotal++;
                updatePlayerScore();
//                Result Dialog
                WinnerMsg winnerMessage = new WinnerMsg(MainActivity.this,
                        binding.firstPlayer.getText().toString() +" is the Winner!",
                        MainActivity.this);
                winnerMessage.setCancelable(true);
                winnerMessage.show();
                emptyBoard();

            }else{
                playerTwoTotal++;
                updatePlayerScore();
//                Result Dialog
                WinnerMsg winnerMessage = new WinnerMsg(MainActivity.this,
                        binding.secondPlayer.getText().toString() +" is the Winner!",
                        MainActivity.this);
                winnerMessage.setCancelable(true);
                winnerMessage.show();
                emptyBoard();
            }

        }else if(totalSelecxtedBox==9){

            Toast.makeText(this,
                    "Match Draw. Try again :)",
                    Toast.LENGTH_SHORT).show();
            emptyBoard();
        }else{
            activePlayer = !activePlayer;
        }

    }

    public boolean checkWinner( ){
        boolean winner =false;

        for(int [] rightCombo : winningCombinations){
            if(boardPosition[rightCombo[0]] == boardPosition[rightCombo[1]] &&
                    boardPosition[rightCombo[1]] == boardPosition[rightCombo[2]] &&
                    boardPosition[rightCombo[0]] !=3){
                winner = true;
            }
        }
        return winner;
    }

    private void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneTotal));
        playerTwoScore.setText(Integer.toString(playerTwoTotal));
    }

    private void resetGame() {
        totalSelecxtedBox = 0;
        activePlayer = true;
        playerOneTotal = 0;
        playerTwoTotal = 0;
        emptyBoard();
        updatePlayerScore();
    }

    public void emptyBoard() {
        for(int i = 0; i< boardPosition.length; i++){
            boardPosition[i] = 3;
            buttons[i].setText("");
            totalSelecxtedBox = 0;
            updatePlayerScore();
        }
    }


}