package com.example.wojtek.snake;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;


public class Board {

    private GridLayout gridLayout;
    private Activity parentActivity;
    private Context context;
    private Snake snake;
    private ArrayList<Point> snakeBody;
    private Gem gem;
    private Point prevLastSnakePart = new Point(0,0);
    private long score = 0;

    public Board(Activity activity, Context context, int id) {
        this.context = context;
        parentActivity = activity;
        gridLayout = parentActivity.findViewById(id);
        initViews();
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
        this.snakeBody = snake.getSnakeBody();
    }

    public void setGem(Gem gem) {
        this.gem = gem;
    }

    public Point getSize() {
        return new Point(gridLayout.getColumnCount(), gridLayout.getRowCount());
    }

    public void initViews() {

        int columns = gridLayout.getColumnCount();
        int rows = gridLayout.getRowCount();
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) gridLayout.getLayoutParams();
        int viewSize = (computeBoardSize().x - vlp.leftMargin - vlp.rightMargin) / columns;
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                ImageView img = new ImageView(context);
                img.setTag(r + "|" + c);
                gridLayout.addView(img, viewSize, viewSize);

            }
        }
    }

    public void test(int index) {
        ImageView view = (ImageView) gridLayout.getChildAt(index);

        Log.i("test", view.getTag()+"");
        view.setBackgroundColor(Color.RED);
    }

    private Point computeBoardSize() {
        Display display = parentActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public long getScore() {
        return score;
    }


    public void updateBoard() {
        gridLayout.getChildAt(gem.getCurrentPostion().y * gridLayout.getColumnCount() + gem.getCurrentPostion().x)
                .setBackgroundColor(context.getResources().getColor(R.color.colorGem));
        snakeBody = snake.getSnakeBody();
        //Log.i("snakebody", snakeBody.toString());
        gridLayout.getChildAt(prevLastSnakePart.y * gridLayout.getColumnCount() + prevLastSnakePart.x)
                .setBackgroundColor(context.getResources().getColor(R.color.colorBoard));
        for(int i = 0; i < snakeBody.size(); i++) {
            Point onePiece = snakeBody.get(i);
            gridLayout.getChildAt(onePiece.y * gridLayout.getColumnCount() + onePiece.x)
                    .setBackgroundColor(context.getResources().getColor(R.color.colorSnake));
        }
        prevLastSnakePart = snakeBody.get(snakeBody.size() - 1);
        if(snakeBody.get(0).equals(gem.getCurrentPostion())) {
            gem.generateRandomGem();
            snake.extend(prevLastSnakePart);
            score++;
        }
    }
}
