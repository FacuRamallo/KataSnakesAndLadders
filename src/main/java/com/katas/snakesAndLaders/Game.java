package com.katas.snakesAndLaders;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Game {
    private Integer boardSize = 100;
    private Integer players;
    private ArrayList<Player> playersArray = new ArrayList<Player>();
    private ArrayList<Ladder> laddersArray =new ArrayList<Ladder>();
    private ArrayList<Snake> snakeArray = new ArrayList<>();
    private Ladder ladderCurrPlayerIsOn;
    private Snake snakeCurrPlayerIsOn;
    private Integer currentPlayerID = 0;
    private Player currentPlayer;
    private Dices dices;
    private Player winner = null;

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

    public Integer getCurrentPlayerID() {
        return currentPlayerID;
    }

    public void generatePlayers(){
        for (Integer i = 0; i < this.players ; i++) {
            playersArray.add(i,new Player(i));
        }
    }

    public void generateLadder(int initialPos, int finalPos){
        laddersArray.add(new Ladder(initialPos,finalPos));
    }

    public void generateSnake(int initialPos, int finalPos) {snakeArray.add(new Snake(initialPos,finalPos));}

    public void playerIterator(){
        if(currentPlayerID == (playersArray.size() - 1)) {
            currentPlayerID = 0;
        }else {
            currentPlayerID += 1;
        }
        currentPlayer = playersArray.get(currentPlayerID);
    }


    public Boolean isPlayerOnALadder(int currentPlayerNewPosition){
        for (Ladder ladder : laddersArray) {
                if(ladder.getInitialPosition()== currentPlayerNewPosition){
                    ladderCurrPlayerIsOn = ladder;
                    return true;
                }
        }
        return false;
    }

    public Boolean isPlayerOnASnake(int currentPlayerNewPosition){
        for (Snake snake: snakeArray) {
            if(snake.getFinalPosition() == currentPlayerNewPosition){
                snakeCurrPlayerIsOn = snake;
                return true;
            }
        }
        return false;
    }

    public Boolean isWinner(int playerPosition){
        if(playerPosition==boardSize){

            return true;
        }
        return false;
    }

    public int getPlayerNewPosition(int sumOfDices) {
        int playerPosition = currentPlayer.getPosition();
        int playerNewPosition = (playerPosition + sumOfDices > boardSize)
               ?boardSize - (playerPosition + sumOfDices - boardSize)
               :playerPosition + sumOfDices;

        if (isPlayerOnALadder(playerNewPosition)) {
            int playerNewPositionPostLadder = playerNewPosition + ladderCurrPlayerIsOn.getPositionsToAdvance();
            ladderCurrPlayerIsOn = null;
            return playerNewPositionPostLadder;
        }
        if (isPlayerOnASnake(playerNewPosition)) {
            int playerNewPositionPostSnake = playerNewPosition - snakeCurrPlayerIsOn.getPositionsToGoBack();
            ladderCurrPlayerIsOn = null;
            return playerNewPositionPostSnake;
        }

        return playerNewPosition;
    }

    public String getStringResponse(){
        if(winner != null){return "Game Over!";}
        if(isWinner(currentPlayer.getPosition())){
            winner = currentPlayer;
            return ("Player "+winner.getId()+" Wins!");
        }
        return ("Player "+ currentPlayer.getId() +" is on square "+ currentPlayer.getPosition());
    }


    public String play(){

        dices.throwDices();
        int sumOfDices = dices.sumOf();
        currentPlayer = playersArray.get(currentPlayerID);
        currentPlayer.setNewPosition(getPlayerNewPosition(sumOfDices));

        String response = getStringResponse();

        if(!dices.equalNumbers()){playerIterator();}

        return response;
    }



}
