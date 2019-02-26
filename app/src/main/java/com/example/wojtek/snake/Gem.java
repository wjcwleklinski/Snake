package com.example.wojtek.snake;


import android.graphics.Point;

import java.util.Random;

public class Gem {

    private int maxX = 0, maxY = 0;
    private int minX = 0, minY = 0;
    private Point currentPostion = new Point(0,0);

    public Gem(int xBoardSize, int yBoardSize) {
        this.maxX = xBoardSize;
        this.maxY = yBoardSize;
    }

    public void generateRandomGem() {
        Random random = new Random();
        int x = random.nextInt(maxX - minX);
        int y = random.nextInt(maxY - minY);
        currentPostion = new Point(x, y);
    }

    public Point getCurrentPostion() {
        return currentPostion;
    }
}
