package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;



public class MainActivity extends AppCompatActivity {

    // 0 = yellow, false = 1
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2}; // 0 initialized so means location is available to place a circle

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6}, {1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean isActive = true;

    public void dropIn(View view) {


        ImageView counter = (ImageView) view; // getting the view from parameter and converting it to ImageView

        int tappedTag = Integer.parseInt(counter.getTag().toString());

        if (isActive && gameState[tappedTag] == 2) {

            counter.setTranslationY(-10000f);
            gameState[tappedTag] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(10000f).rotation(360).setDuration(300);

        }
        for (int[] winPos: winningPositions){

            if (gameState[winPos[0]] == gameState[winPos[1]] &&
                    gameState[winPos[1]] == gameState[winPos[2]] &&
                    gameState[winPos[0]] != 2)
            {
                String winner = "Red";
                isActive = false;
                if (gameState[winPos[0]] == 0) {
                    winner = "Yellow";
                }

                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner +" has won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);

            } else {
                boolean isOver = true;
                for (int counterState: gameState) {
                    if (counterState == 2){
                        isOver= false;
                    }
                }
                if (isOver == true) {

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText("It's a Draw!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    public void playAgain(View view) {


        isActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.newgridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}