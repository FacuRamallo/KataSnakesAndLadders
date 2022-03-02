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
		int initialPosition[][] = {{1},{1}};
		ArrayList<Player> players = game.getPlayersArray();

		assertEquals(2,players.size());
		assertArrayEquals(initialPosition,players.get(0).getPosition());
		assertArrayEquals(initialPosition,players.get(1).getPosition());
	}

	@Test
	void player1StartsAndAlternatesWithPlayer2(){
		mockDices = mock(Dices.class);
		Game game = new Game(2);

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
		Game game = new Game(2);
		ArrayList<Player> players = game.getPlayersArray();

		//when(mockDices.equalNumbers()).thenReturn(2);
		doReturn(2).when(mockDices).sumOf();

		int newPosition[][] = {{1},{3}};
		game.play();

		assertEquals(newPosition,);

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




}
