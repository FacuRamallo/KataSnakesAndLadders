package com.snakesladers;

public class Player {
    private Integer id;
    private int position = 0;

    public Player(Integer id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setNewPosition(int dicesSum) {

        this.position += dicesSum;
    }

}
