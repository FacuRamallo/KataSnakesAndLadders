package com.snakesladers;

public class Player {
    private Integer id;
    private int [][] position = {{1},{1}};

    public Player(Integer id) {
        this.id = id;
    }

    public int[][] getPosition() {
        return position;
    }

    public void setPosition(int[][] position) {
        this.position = position;
    }

}
