package com.example.wojtek.snake;


import android.graphics.Color;
import android.graphics.Point;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {

    public static final int DIR_NORTH = 1;
    public static final int DIR_SOUTH = 2;
    public static final int DIR_WEST = 3;
    public static final int DIR_EAST = 4;

    private int initialLength;
    private Point initialPosition;
    private ArrayList<Point> snakeBody = new ArrayList<>();

    public Snake(int initialLength, Point initialPosition, int direction) {
        this.initialLength = initialLength;
        this.initialPosition = initialPosition;
        initSnake(direction);
    }

    private void initSnake(int direction) {
        int x = 0, y = 0;
        switch (direction) {
            case DIR_NORTH:
                x = 0;
                y = -1;
                break;
            case DIR_SOUTH:
                x = 0;
                y = 1;
                break;
            case DIR_EAST:
                x = 1;
                y = 0;
                break;
            case DIR_WEST:
                x = -1;
                y = 0;
                break;
        }
        snakeBody.add(initialPosition);
        for(int i = 1; i < initialLength; i++) {
            Point temp = new Point(initialPosition.x + (i * x), initialPosition.y + (i*y));
            //temp.offset(x, y);
            Log.i("offset", temp.toString());
            snakeBody.add(temp);
            Log.i("Snake", Arrays.toString(snakeBody.toArray()));
        }

    }

    public ArrayList<Point> getSnakeBody() { return this.snakeBody; }

    public void moveSnake(int direction) {
        int x = 0, y = 0;
        switch (direction) {
            case DIR_NORTH:
                if(snakeBody.get(0).y > 0) {
                    Point newHead = new Point(snakeBody.get(0).x , snakeBody.get(0).y - 1);
                    snakeBody.add(0, newHead);
                    snakeBody.remove(snakeBody.size()-1);
                }
                break;
            case DIR_SOUTH:
                if(snakeBody.get(0).y < 14) {
                    Point newHead = new Point(snakeBody.get(0).x, snakeBody.get(0).y + 1);
                    snakeBody.add(0, newHead);
                    snakeBody.remove(snakeBody.size()-1);
                }
                break;
            case DIR_EAST:
                if(snakeBody.get(0).x < 14) {
                    Point newHead = new Point(snakeBody.get(0).x + 1, snakeBody.get(0).y);
                    snakeBody.add(0, newHead);
                    snakeBody.remove(snakeBody.size()-1);
                }
                break;
            case DIR_WEST:
                if((snakeBody.get(0).x > 0)) {
                    Point newHead = new Point(snakeBody.get(0).x - 1, snakeBody.get(0).y);
                    snakeBody.add(0, newHead);
                    snakeBody.remove(snakeBody.size()-1);
                }
                break;
        }

    }

    public void extend(Point point) {
        snakeBody.add(point);
    }
}

