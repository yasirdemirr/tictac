package com.ba.tictactoe;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int[] gameState = {2,2,2,2,2,2,2,2,2,};
    String[] dizi;
    int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    LinearLayout linearLayout;
    boolean gameIsActive=true;
    GridLayout gridLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 0 =yellow; 1=red.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        linearLayout=findViewById(R.id.newGameLayout);
        gridLayout=findViewById(R.id.gridLayout);





    }
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tapped=Integer.parseInt(counter.getTag().toString());


            if (gameState[tapped] == 2&&gameIsActive) {
                gameState[tapped] = activePlayer;
                counter.setTranslationY(-1000f);

                if(activePlayer==0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;


            } else {


                counter.setImageResource(R.drawable.red);
                activePlayer = 0;


            }
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

                for(int[] winningposition: winningPosition){
                    if(gameState[winningposition[0]]==gameState[winningposition[1]]&&
                    gameState[winningposition[1]]==gameState[winningposition[2]]&&
                    gameState[winningposition[0]]!=2){
                        gameIsActive=false;
                        if(gameState[winningposition[0]]==0){
                            textView.setText("Yellow Winning");
                        }else{
                            textView.setText("Red Winning");
                            linearLayout.setBackgroundColor(Color.parseColor("#FFFF0000"));
                        }

                        linearLayout.setVisibility(View.VISIBLE);

                    }else{
                        boolean gameOver =true;
                        for(int i : gameState){
                            if(i==2)
                                gameOver=false;
                        }
                        if(gameOver){
                            linearLayout.setVisibility(View.VISIBLE);
                            textView.setText("No Winning");
                        }
                    }
                }

            }

    }
    public void playAgain(View view){
        linearLayout.setVisibility(View.INVISIBLE);
        gameIsActive=true;
       for(int i=0;i<gameState.length;i++){
           gameState[i]=2;
       }
       for(int i=0;i<gridLayout.getChildCount();i++){
           ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
       }

    }
}
