package com.example.wojtek.snake;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Board board;
    private Snake snake;
    public int currentDirection = 0;
    private long score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        board = new Board(this, getApplicationContext(), R.id.gridLayout);
        snake = new Snake(3, new Point(7,7), Snake.DIR_SOUTH);
        Gem gem = new Gem(board.getSize().x, board.getSize().y);
        gem.generateRandomGem();
        board.setGem(gem);
        board.setSnake(snake);
        board.test(0);//chyba od 1 ma być numeracja

        //board.test(224);
        final TextView scoreTextView = findViewById(R.id.textViewScore);
        final Handler UIRefresher = new Handler();
        UIRefresher.post(new Runnable() {
            @Override
            public void run() {
                snake.moveSnake(currentDirection);
                board.updateBoard();
                scoreTextView.setText(String.valueOf(board.getScore()));
                UIRefresher.postDelayed(this, 300);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonUp:
                currentDirection = Snake.DIR_NORTH;
                break;
            case R.id.buttonDown:
                currentDirection = Snake.DIR_SOUTH;
                break;
            case R.id.buttonLeft:
                currentDirection = Snake.DIR_WEST;
                break;
            case R.id.buttonRight:
                currentDirection = Snake.DIR_EAST;
                break;
        }
    }
}
