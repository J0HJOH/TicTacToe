package com.task1.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class WinnerMsg extends Dialog {

    private  final String message;
    private final MainActivity mainActivity;

    public WinnerMsg(@NonNull Context context,
                     String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_message);

        TextView messageText = findViewById(R.id.winner_text);

        messageText.setText(message);
    }
}