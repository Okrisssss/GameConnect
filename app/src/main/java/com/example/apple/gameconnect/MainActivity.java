package com.example.apple.gameconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLAyout;
    LinearLayout layout;
    TextView winnerMessage;

    int activPlayer = 0;  //0 = yellow, 1 = red.
    boolean gameIsActive = true;

    int [] gameState = {2,2,2,2,2,2,2,2,2}; //2 means unplayed cells
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLAyout = (GridLayout) findViewById(R.id.gridLayoutTable);
        layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        winnerMessage = (TextView) findViewById(R.id.winnerMessage);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activPlayer;

            counter.setTranslationY(-1500f);

            if (activPlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activPlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activPlayer = 0;
            }

            counter.animate().translationYBy(1500f).rotation(3600f).setDuration(300);

            for (int[] winningPosition : winningPositions) {// ask bro what does it mean this row

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] // ask bro what does it mean this row
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]  // ask bro what does it mean this row
                        && gameState[winningPosition[0]] != 2) {  // ask bro what does it mean this row
                    //some has won!

                    gameIsActive = false;
                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }
                    winnerMessage.setText(winner + " has wone!");
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        winnerMessage.setText("It is a draw!");
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }

    public void playAgain (View view){

        gameIsActive = true;
        layout.setVisibility(View.INVISIBLE);
        activPlayer = 0;
        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        for (int i = 0; i<gridLAyout.getChildCount(); i++){
            ((ImageView) gridLAyout.getChildAt(i)).setImageResource(0);
        }
    }
}
