package com.example.dice_game;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    private TextView[] diceTextViews = new TextView[5];
    private TextView scoreTextView;
    private TextView rollCountTextView;
    private TextView currentRollScoreTextView;
    private int rollCount = 0;
    private int totalScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceTextViews[0] = findViewById(R.id.dice1);
        diceTextViews[1] = findViewById(R.id.dice2);
        diceTextViews[2] = findViewById(R.id.dice3);
        diceTextViews[3] = findViewById(R.id.dice4);
        diceTextViews[4] = findViewById(R.id.dice5);

        scoreTextView = findViewById(R.id.score);
        rollCountTextView = findViewById(R.id.rollCount);
        currentRollScoreTextView = findViewById(R.id.currentRollScore);

        Button rollDiceButton = findViewById(R.id.rollDiceButton);
        Button resetGameButton = findViewById(R.id.resetGameButton);

        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
        resetGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        int[] diceResults = new int[5];
        int currentRollScore = 0;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            diceResults[i] = random.nextInt(6) + 1;
            currentRollScore += diceResults[i];
        }

        totalScore += currentRollScore;
        rollCount++;
        displayDiceResults(diceResults);
        updateScore(currentRollScore, totalScore, rollCount); // Aktualizacja wyników przez updateScore
    }

    private void resetGame() {
        for (TextView textView : diceTextViews) {
            textView.setText("?");
        }
        totalScore = 0;
        rollCount = 0;
        updateScore(0, 0, rollCount); // Resetujemy wszystkie wyniki przez updateScore
    }

    private void updateScore(int currentRollScore, int newTotalScore, int newRollCount) {
        currentRollScoreTextView.setText("Wynik tego losowania: " + currentRollScore);
        scoreTextView.setText("Wynik gry: " + newTotalScore);
        rollCountTextView.setText("Licznik rzutów: " + newRollCount);
    }

    private void displayDiceResults(int[] diceResults) {
        for (int i = 0; i < diceResults.length; i++) {
            diceTextViews[i].setText(String.valueOf(diceResults[i]));
        }
    }
}