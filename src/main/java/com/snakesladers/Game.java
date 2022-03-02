package com.snakesladers;

import java.util.ArrayList;

public class Game {
    private Integer[][] board = {{1,2,3,4,5,6,7,8,9,10},{1,2,3,4,5,6,7,8,9,10}};
    private Integer players;
    private ArrayList<Player> playersArray = new ArrayList<Player>();
    private Integer turn = 0;
    private Dices dices;

    public Game(Integer players) {
        this.players = players;
        dices = new Dices();
        generatePlayers();
    }

    public Game(Integer players, Dices dices) {
        this.players = players;
        this.dices = dices;
        generatePlayers();
    }

    public void generatePlayers(){
        for (Integer i = 0; i < this.players ; i++) {
            String pref = "player";
            playersArray.add(i,new Player(i));
        }
    }

    public ArrayList<Player> getPlayersArray() {
        return playersArray;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public void playerIterator(){
        if (turn == (playersArray.size() - 1)) {
            setTurn(0);
        } else {
            setTurn(turn + 1);
        }
    }

    public int play(){
        dices.throwDices();
        int sumOfDices = dices.sumOf();
        if(dices.equalNumbers()){return sumOfDices;}
        playerIterator();
        return sumOfDices;
    }

}
