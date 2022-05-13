package com.katas.snakesAndLaders;

import static java.lang.Math.abs;

public class Snake {
    private int initialPosition;
    private int finalPosition;
    private int positionsToGoBack;

    public Snake(int initialPosition, int finalPosition) {
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public int getPositionsToGoBack() {
        return abs(initialPosition-finalPosition);
    }
}
