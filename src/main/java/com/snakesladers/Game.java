package com.snakesladers;

import java.util.ArrayList;

public class Game {
    private Integer[][] board = {{1,2,3,4,5,6,7,8,9,10},{1,2,3,4,5,6,7,8,9,10}};
    private Integer players;
    private ArrayList<Player> playersArray = new ArrayList<Player>();
    private ArrayList<Ladder> laddersArray =new ArrayList<Ladder>();
    private Ladder ladderCurrPlayerIsOn;
    private Integer turn = 0;
    private Dices dices;

    public void setLadderCurrPlayerIsOn(Ladder ladderCurrPlayerIsOn) {
        this.ladderCurrPlayerIsOn = ladderCurrPlayerIsOn;
    }

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

    public ArrayList<Player> getPlayersArray() {
        return playersArray;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public void generatePlayers(){
        for (Integer i = 0; i < this.players ; i++) {
            String pref = "player";
            playersArray.add(i,new Player(i));
        }
    }

    public void  generateLadder(int initialPos, int finalPos){
        laddersArray.add(new Ladder(initialPos,finalPos));
    }

    public void playerIterator(){
        if (turn == (playersArray.size() - 1)) {
            setTurn(0);
        } else {
            setTurn(turn + 1);
        }
    }

    public Boolean isPlayerOnALadder(int currentPlayerNewPosition){
        for (Ladder ladder : laddersArray) {
                if(ladder.getInitialPosition()== currentPlayerNewPosition){
                    setLadderCurrPlayerIsOn(ladder);
                    return true;}
        }
        return false;
    }

    public void play(){
        dices.throwDices();
        int sumOfDices = dices.sumOf();
        Player currentPlayer = playersArray.get(getTurn());
        currentPlayer.setNewPosition(sumOfDices);
        int currentPlayerNewPosition = currentPlayer.getPosition();
        if(isPlayerOnALadder(currentPlayerNewPosition)){
            currentPlayer.setNewPosition(ladderCurrPlayerIsOn.getPositionsToAdvance());
            setLadderCurrPlayerIsOn(null);
        }
        if(!dices.equalNumbers()){playerIterator();}

    }



}
