package com.katas.snakesAndLaders;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class gameTest {
    private Dices mockDices;
    private Game game;
    private ArrayList<Player> players;

    @BeforeEach
    public void before(){
        mockDices = mock(Dices.class);
        game = new Game(2,mockDices);
        players = game.getPlayersArray();
    }

    @Test
    void thereAreTwoPlayersAndBothStartOffTheBoardOnSquare0() {

        int initialPosition = 0;

        assertEquals(2,players.size());
        assertEquals(initialPosition,players.get(0).getPosition());
        assertEquals(initialPosition,players.get(1).getPosition());
    }

    @Test
    void player1StartsAndAlternatesWithPlayer2(){

        when(mockDices.equalNumbers()).thenReturn(false);

        assertEquals(0,game.getCurrentPlayerID());
        game.play();
        assertEquals(1,game.getCurrentPlayerID());
        game.play();
        assertEquals(0,game.getCurrentPlayerID());
    }

    @Test
    void youFollowTheNumbersUpTheBoardInOrder1To100(){

        when(mockDices.sumOf()).thenReturn(4);

        int expectedPosition1 = 4;
        assertEquals(0,players.get(0).getPosition());
        game.play();
        game.play();
        assertEquals(expectedPosition1,players.get(0).getPosition());
        game.play();
        game.play();
        int expectedPosition2 = 8;
        assertEquals(expectedPosition2,players.get(0).getPosition());
    }

    @Test
    void ifTheValueOfBothDieAreTheSameThenThatPlayerWillHaveAnotherGo(){

        when(mockDices.equalNumbers()).thenReturn(true);

        assertEquals(0,game.getCurrentPlayerID());
        game.play();
        assertEquals(0,game.getCurrentPlayerID());

    }

    @Test
    void ifAPlayerLandsInALaderStartItMoveUpwardsToPositionAtTheEndOfLader(){

        game.generateLadder(4,10);
        when(mockDices.sumOf()).thenReturn(4);


        assertEquals(0,players.get(0).getPosition());
        game.play();
        assertEquals(10,players.get(0).getPosition());
    }

    @Test
    void ifAPlayerLandsInASnakeStartItMoveDownwardsToPositionAtTheStartOfSnake(){

        game.generateSnake(2,10);
        when(mockDices.sumOf()).thenReturn(10);

        assertEquals(0,players.get(0).getPosition());
        game.play();
        assertEquals(2,players.get(0).getPosition());
    }

    @Test
    void toWinAPlayerHasToLandExactlyOnTheLastBoardSquare(){

        when(mockDices.sumOf()).thenReturn(100);

        var sut = game.play();

        assertEquals("Player 0 Wins!", sut);
    }

    @Test
    void ifThePlayerReachTheEndOfTheGameWithExtraMovesItRollBackTheMovesLeft(){

        Player player = players.get(0);
        when(mockDices.sumOf()).thenReturn(105);

        game.play();

        var sut = player.getPosition();

        assertEquals(95,sut);
    }
}

