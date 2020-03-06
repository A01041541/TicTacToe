package com.hobbsingh.tictactoe.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Tic-Tac-Toe class that does all the work
 */
public class TicTacToe extends AppCompatActivity{

    /**
     * Button that lets you start with an X
     */
    Button btnX;

    /**
     * Button that lets you start with a 0
     */
    Button btn0;

    /**
     * Button that lets you reset
     */
    private Button reset;

    /**
     * Button in the grid.
     */
    private Button btn1;

    /**
     * Button in the grid.
     */
    private Button btn2;

    /**
     * Button in the grid.
     */
    private Button btn3;

    /**
     * Button in the grid.
     */
    private Button btn4;

    /**
     * Button in the grid.
     */
    private Button btn5;

    /**
     * Button in the grid.
     */
    private Button btn6;

    /**
     * Button in the grid.
     */
    private Button btn7;

    /**
     * Button in the grid.
     */
    private Button btn8;

    /**
     * Button in the grid.
     */
    private Button btn9;

    /**
     * Button Array for Buttons in the grid.
     */
    private Button[][] btnArray;

    /**
     * Tells the user who won
     */
    private TextView wins;

    /**
     * Keeps track of the clicked buttons
     */
    private ArrayList<Button> btnClicked;

    /**
     * checks if it is X's turn
     */
    private boolean isXturn = true;

    /**
     * Boolean that tells if continue the game or not
     */
    private boolean continueGame = true;

    /**
     * boolean to check if the game is a draw
     */
    private boolean draw = false;

    /**
     * Method to render everything.
     */
    public void render() {
        reset = findViewById(R.id.reset);
        wins = findViewById(R.id.wins);
        btn0 = findViewById(R.id.btn0);
        btnX = findViewById(R.id.btnX);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnArray = new Button[][] {{btn1, btn2, btn3}, {btn4, btn5, btn6}, {btn7, btn8, btn9}};
        btnClicked = new ArrayList<Button>();

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isXturn = false;
                reset();
            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isXturn = true;
                reset();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                reset();
            }
        });
        for(final Button bb[] : btnArray) {
            for(final Button b: bb) {
                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        takeTurn(b);
                        btnClicked.add(b);
                    }
                });
            }
        }
    }

    /**
     * method to take turn
     * @param b
     *          the button that user clicks
     */
    public void takeTurn(Button b) {
        if(continueGame) {
            if (isXturn) {
                XTurn(b);
            } else {
                oTurn(b);
            }
        }
    }

    /**
     * method called if it is X's turn
     * @param b
     *          Button that user clicked.
     */
    public void XTurn(Button b) {
        b.setText("X");
        isXturn = false;
        if(!checkStatus(b)) {
            wins.setVisibility(View.VISIBLE);
            reset.setVisibility(View.INVISIBLE);
            if (!draw) {
                wins.setText("X Wins!");
            } else{
                wins.setText("Draw!");
                draw = false;
            }
        }
    }

    /**
     * method called if it is 0's turn
     * @param b
     *          Button that user clicked.
     */
    public void oTurn(Button b) {
        b.setText("0");
        isXturn = true;
        if(!checkStatus(b)) {
            wins.setVisibility(View.VISIBLE);
            reset.setVisibility(View.INVISIBLE);
            if (!draw) {
                wins.setText("0 Wins!");
            } else{
                wins.setText("Draw!");
                draw = false;
            }
        }
    }

    /**
     * method to check the status, i.e, if someone won or not
     * @param b
     *          Button that is clicked by the user
     * @return
     *      true/false
     */
    public boolean checkStatus(Button b) {
        int x = 0;
        int y = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(btnArray[i][j] == b) {
                    x = i;
                    y = j;
                }
            }
        }

        if(btnArray[x][0].getText().equals(b.getText()) && btnArray[x][1].getText().equals(b.getText()) && btnArray[x][2].getText().equals(b.getText())) {
            continueGame = false;
        }

        if(btnArray[0][y].getText().equals(b.getText()) && btnArray[1][y].getText().equals(b.getText()) && btnArray[2][y].getText().equals(b.getText())) {
            continueGame = false;
        }

        if(btnArray[0][0].getText().equals(b.getText()) && btnArray[1][1].getText().equals(b.getText()) && btnArray[2][2].getText().equals(b.getText())) {
            continueGame = false;
        }

        if(btnArray[0][2].getText().equals(b.getText()) && btnArray[1][1].getText().equals(b.getText()) && btnArray[2][0].getText().equals(b.getText())) {
            continueGame = false;
        }

        Set<Button> uniqueBtn = new HashSet<Button>(btnClicked);
        if(uniqueBtn.size() == 8 && continueGame) {
            draw = true;
            continueGame = false;
            uniqueBtn.clear();
        }
        return continueGame;
    }

    /**
     * resets the screen
     */
    public void reset() {
        for(final Button bb[] : btnArray) {
            for(final Button b: bb) {
                b.setText("");
            }
        }
        btnClicked.clear();
        wins.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.VISIBLE);
        continueGame = true;
    }
}