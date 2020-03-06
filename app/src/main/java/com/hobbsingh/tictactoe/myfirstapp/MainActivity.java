package com.hobbsingh.tictactoe.myfirstapp;

import android.os.Bundle;

public class MainActivity extends TicTacToe {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame();
    }

    public void startGame() {
        render();
    }
}
