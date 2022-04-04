package com.snakesladers;

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
        return initialPosition-finalPosition;
    }
}
