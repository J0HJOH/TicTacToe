package com.task1.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.task1.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOneScore, playerTwoScore;

    private int playerOneTotal, playerTwoTotal;
    private Button resetGame;

    private TextView [] boxText = new TextView[9];
    private CardView [] buttons = new CardView[9];


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

        String getplayerOneName = getIntent().getStringExtra("playerOne");
        String getplayerTwoName = getIntent().getStringExtra("playerTwo");


        playerOneScore = findViewById(R.id.oneScore);
        playerTwoScore = findViewById(R.id.twoScore);
        resetGame = findViewById(R.id.resetGame);

        binding.firstPlayer.setText(getplayerOneName);
        binding.secondPlayer.setText(getplayerTwoName);



        for(int i=0; i< buttons.length; i++ ){
            String buttonID = "btn_" + i ;
            String textID = buttonID+ "_txt";
            int cardResourceID = getResources().getIdentifier(
                    buttonID, "id",getPackageName()
            );
            int textResourceID = getResources().getIdentifier(
                    textID,"id",getPackageName()
            );

            buttons[i] = (CardView) findViewById(cardResourceID);
            boxText[i] = (TextView) findViewById(textResourceID);

            buttons[i].setOnClickListener(this);


        }
        playerOneTotal=0;
        playerTwoTotal = 0;
        activePlayer = true;

    }

    @Override
    public void onClick(View v) {

        if(!((TextView)v).getText().toString().equals("")) {
            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gamePointer = Integer.parseInt(buttonID.substring(buttonID.length()-5));

        if(activePlayer){
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_corners);
            binding.playerTwoLayout.setBackgroundResource(R.color.cream);
//            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_corners);
            ((TextView) v).setText("X");
            boardPosition[gamePointer] = 0;
        }else{
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_corners);
            binding.playerOneLayout.setBackgroundResource(R.color.cream);
//            binding.playerOneLayout.setBackgroundResource(R.drawable.black_corners);
            ((TextView) v).setText("O");
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

                resetGame();
            }else{
                playerTwoTotal++;
                updatePlayerScore();
//                Result Dialog
                WinnerMsg winnerMessage = new WinnerMsg(MainActivity.this,
                        binding.secondPlayer.getText().toString() +" is the Winner!",
                        MainActivity.this);
                winnerMessage.setCancelable(true);
                winnerMessage.show();

                resetGame();
            }

        }else if(totalSelecxtedBox==9){

            Toast.makeText(this,
                    "There is no winner for this round. Try again :)",
                    Toast.LENGTH_SHORT).show();
            resetGame();

        }else{
            activePlayer = !activePlayer;
        }

    }

    public boolean checkWinner( ){
        boolean winner =false;

        for(int [] rightCombo : winningCombinations){
            if(boardPosition[winningCombinations[0][0]] == boardPosition[winningCombinations[1][1]] &&
                    boardPosition[winningCombinations[1][1]] == boardPosition[winningCombinations[2][2]] &&
                    boardPosition[winningCombinations[0][0]] !=3){
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


        for(int i = 0; i< buttons.length; i++){
            boardPosition[i] = 3;
            boxText[i].setText("");
        }
        updatePlayerScore();
    }


}