package com.task1.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final List<int []> combinationList = new ArrayList<>();
    private int [] boardPosition = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelecxtedBox = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void inputSymbol(String symbol, int selectedPosition){
        boardPosition[selectedPosition] = playerTurn;

        if(playerTurn==1){

        }

    }
}