package com.snakesladers;


public class Ladder {
    private int initialPosition;
    private int finalPosition;
    private int positionsToAdvance;

    public Ladder(int initialPosition, int finalPosition) {
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public int getPositionsToAdvance(){
        return finalPosition-initialPosition;
    }
}
