package com.snakesladers;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


class GameTests {

	private Dices mockDices;

	@Test
	void thereAreTwoPlayersAndBothStartOffTheBoardOnSquare0() {
		Game game = new Game(2);
		int initialPosition = 0;
		ArrayList<Player> players = game.getPlayersArray();

		assertEquals(2,players.size());
		assertEquals(initialPosition,players.get(0).getPosition());
		assertEquals(initialPosition,players.get(1).getPosition());
	}

	@Test
	void player1StartsAndAlternatesWithPlayer2(){
		mockDices = mock(Dices.class);
		Game game = new Game(2,mockDices);

		when(mockDices.equalNumbers()).thenReturn(false);

		assertEquals(0,game.getTurn());
		game.play();
		assertEquals(1,game.getTurn());
		game.play();
		assertEquals(0,game.getTurn());
	}

	@Test
	void youFollowTheNumbersUpTheBoardInOrder1To100(){
		mockDices = mock(Dices.class);
		Game game = new Game(2,mockDices);
		ArrayList<Player> players = game.getPlayersArray();

		when(mockDices.sumOf()).thenReturn(4);
		//doReturn(2).when(mockDices).sumOf();

		int newPosition = 4;
		assertEquals(0,players.get(0).getPosition());
		//players.get(0).setNewPosition(mockDices.sumOf());
		game.play();
		assertEquals(newPosition,players.get(0).getPosition());

	}

	@Test
	void ifTheValueOfBothDieAreTheSameThenThatPlayerWillHaveAnotherGo(){
		mockDices = mock(Dices.class);
		Game game = new Game(2,mockDices);

		when(mockDices.equalNumbers()).thenReturn(true);

		assertEquals(0,game.getTurn());
		game.play();
		assertEquals(0,game.getTurn());

	}

	@Test
	void ifAPlayerLandsInALaderStartItMoveUpwardsToPositionAtTheEndOfLader(){
		mockDices = mock(Dices.class);
		Game game = new Game(2,mockDices);
		ArrayList<Player> players = game.getPlayersArray();
		game.generateLadder(4,10);
		when(mockDices.sumOf()).thenReturn(4);


		assertEquals(0,players.get(0).getPosition());
		game.play();
		assertEquals(10,players.get(0).getPosition());
	}




}
