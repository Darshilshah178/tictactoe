package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean playerOneActive = true;
    private int numOfCount;

    //user input text//
    private EditText playerOneName;
    private EditText playerTwoName;

    //default text//
    private TextView p1;
    private TextView p2;

    //score that has text of zero//
    private TextView score1;
    private TextView score2;

    //defines the score class//
    private Scores scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scores = new Scores(this);

        final Button reset = (Button) findViewById(R.id.resetGame);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GAME","reset clicked?" );
                scores.resetScore();
                updateScore();
                resetGameGrid();
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        //User input//
        p1 = findViewById(R.id.P1);

        p2 = findViewById(R.id.P2);


        //Text to replace in game//
        playerOneName = findViewById(R.id.player1name);
//        if(playerOneName.equals(null)){
//            p1.setText("Player 1:");
//        }else{
            p1.setText(message + ":");

//        }
//        if(playerTwoName.equals(null)){
//            p2.setText("Player 2:");
//        }else{
            p2.setText(message2 + ":");

        //}

        buttons[0][0] = findViewById(R.id.button_00);
        buttons[0][1] = findViewById(R.id.button_01);
        buttons[0][2] = findViewById(R.id.button_02);
        buttons[1][0] = findViewById(R.id.button_03);
        buttons[1][1] = findViewById(R.id.button_04);
        buttons[1][2] = findViewById(R.id.button_05);
        buttons[2][0] = findViewById(R.id.button_06);
        buttons[2][1] = findViewById(R.id.button_07);
        buttons[2][2] = findViewById(R.id.button_08);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y].setOnClickListener(this);
            }
        }
    }
    public void quit(View view){
        Intent quit = new Intent(this, Quit.class);
        startActivity(quit);
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (playerOneActive) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        numOfCount++;
        if (win()) {
            if (playerOneActive) {
                scores.playerWins(1);
                updateScore();
                resetGameGrid();
            }else{
                scores.playerWins(2);
                updateScore();
                resetGameGrid();
            }
            } else if(numOfCount==9){
                gameIsDraw();
                resetGameGrid();
            }else{
                playerOneActive = !playerOneActive;
            }
        }
        private void updateScore(){
            score1=(TextView) findViewById(R.id.score1);
            score1.setText(Integer.toString(scores.getPlayer1Score()));

            score2=(TextView) findViewById(R.id.score2);
            score2.setText(Integer.toString(scores.getPlayer2Score()));
        }

        private void gameIsDraw(){
            Toast.makeText(this,"It's a draw!", Toast.LENGTH_LONG).show();
            resetGameGrid();
        }

        public void resetGameGrid(){
            for(int x = 0; x < 3; x++){
                for(int y = 0; y <3; y++){
                    buttons[x][y].setText("");
                }
            }
            numOfCount = 0;
            playerOneActive = true;
    }



    private boolean win() {
        String[][] position = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                position[i][j] = buttons[i][j].getText().toString();
            }
        }
        //button_00 diagonal win//
        if (position[0][0].equals(position[1][1])
                && position[0][0].equals(position[2][2])
                && !position[0][0].equals("")) {
            return true;
        }
        //button_00 vertical win//
        else if (position[0][0].equals(position[1][0])
                && position[0][0].equals(position[2][0])
                && !position[0][0].equals("")) {
            return true;
        }
        //button_00 horizontal win//
        else if (position[0][0].equals(position[0][1])
                && position[0][0].equals(position[0][2])
                && !position[0][0].equals("")) {
            return true;
        }
        //button_01 vertical win//
        if (position[0][1].equals(position[1][1])
                && position[0][1].equals(position[2][1])
                && !position[0][1].equals("")) {
            return true;
        }
        //button_01 horizontal win//
        else if (position[0][1].equals(position[0][0])
                && position[0][1].equals(position[0][2])
                && !position[0][1].equals("")) {
            return true;
        }
        //button_02 vertical win//
        if (position[0][2].equals(position[1][2])
                && position[0][2].equals(position[2][2])
                && !position[0][2].equals("")) {
            return true;
        }
        //button_02 horizontal win//
        else if (position[0][2].equals(position[0][0])
                && position[0][2].equals(position[0][1])
                && !position[0][2].equals("")) {
            return true;
        }
        //button_02 diagonal win//
        else if (position[0][2].equals(position[1][1])
                && position[0][2].equals(position[2][0])
                && !position[0][2].equals("")) {
            return true;
        }
        //button_03 vertical win//
        if (position[1][0].equals(position[0][0])
                && position[1][0].equals(position[2][0])
                && !position[1][0].equals("")) {
            return true;
        }
        //button_03 horizontal win//
        else if (position[1][0].equals(position[1][1])
                && position[1][0].equals(position[1][2])
                && !position[1][0].equals("")) {
            return true;
        }
        //button_04 vertical win//
        if (position[1][1].equals(position[1][0])
                && position[1][1].equals(position[1][2])
                && !position[1][1].equals("")) {
            return true;
        }
        //button_04 horizontal win//
        else if (position[1][1].equals(position[0][1])
                && position[1][1].equals(position[2][1])
                && !position[1][1].equals("")) {
            return true;
        }
        //button_04 diagonal win//
        else if (position[1][1].equals(position[0][2])
                && position[1][1].equals(position[2][0])
                && !position[1][1].equals("")) {
            return true;
        }
        //button_04 diagonal2 win//
        else if (position[1][1].equals(position[0][0])
                && position[1][1].equals(position[2][2])
                && !position[1][1].equals("")) {
            return true;
        }
        //button_05 vertical win//
        if (position[1][2].equals(position[1][0])
                && position[1][2].equals(position[1][1])
                && !position[1][2].equals("")) {
            return true;
        }
        //button_05 horizontal win//
        else if (position[1][2].equals(position[0][2])
                && position[1][2].equals(position[2][2])
                && !position[1][2].equals("")) {
            return true;
        } //button_06 diagonal win//
        if (position[2][0].equals(position[1][1])
                && position[2][0].equals(position[0][2])
                && !position[2][0].equals("")) {
            return true;
        }
        //button_06 vertical win//
        else if (position[2][0].equals(position[0][0])
                && position[2][0].equals(position[1][0])
                && !position[2][0].equals("")) {
            return true;
        }
        //button_06 horizontal win//
        else if (position[2][0].equals(position[2][1])
                && position[2][0].equals(position[2][2])
                && !position[2][0].equals("")) {
            return true;
        }

        //button_07 vertical win//
        if (position[2][1].equals(position[0][1])
                && position[2][1].equals(position[1][1])
                && !position[2][1].equals("")) {
            return true;
        }
        //button_07 horizontal win//
        else if (position[2][1].equals(position[2][0])
                && position[2][1].equals(position[2][2])
                && !position[2][1].equals("")) {
            return true;
        }
        //button_08 vertical win//
        if (position[2][2].equals(position[1][2])
                && position[2][2].equals(position[0][2])
                && !position[2][2].equals("")) {
            return true;
        }
        //button_08 horizontal win//
        else if (position[2][2].equals(position[2][0])
                && position[2][2].equals(position[2][1])
                && !position[2][2].equals("")) {
            return true;
        }
        //button_08 diagonal win//
        else if (position[2][2].equals(position[1][1])
                && position[2][2].equals(position[0][0])
                && !position[2][2].equals("")) {
            return true;
        }
        return false;
    }

}
