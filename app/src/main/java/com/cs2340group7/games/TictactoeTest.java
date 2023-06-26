package com.cs2340group7.games;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertNotEquals;

import org.junit.Test;


public class TictactoeTest {
    char[][] emptyBoard = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    @Test
    public void testPlayerIconDisplay() {
        // Test case for player icon display in the Tic Tac Toe game
        // ...

        // Arrange
        SelectedSpriteViewModel viewModel = new SelectedSpriteViewModel();
        viewModel.setSelectedSpriteResId(R.drawable.blobfish);

        // Act
        int selectedSpriteResId = viewModel.getSelectedSpriteResId();

        // Assert
        assertEquals(R.drawable.blobfish, selectedSpriteResId);
    }


    @Test
    public void testPlayerNameNotNull() {
        // Test case for player name not null in the Tic Tac Toe game
        // ...

        // Arrange
        String playerName = "Maymunah";
        GameConfigurationScreen gameConfigurationScreen = new GameConfigurationScreen();

        // Act
        gameConfigurationScreen.setPlayerName(playerName);
        String retrievedPlayerName = gameConfigurationScreen.getPlayerName();

        // Assert
        assertNotNull(retrievedPlayerName);
        assertEquals(playerName, retrievedPlayerName);
    }

    @Test
    public void testPlayerNameEmpty() {
        // Test case for an empty player name in the Tic Tac Toe game
        // ...

        // Arrange
        String playerName = "";
        GameConfigurationScreen gameConfigurationScreen = new GameConfigurationScreen();

        // Act
        gameConfigurationScreen.setPlayerName(playerName);
        String retrievedPlayerName = gameConfigurationScreen.getPlayerName();

        // Assert
        assertNotNull(retrievedPlayerName);
        assertEquals(playerName, retrievedPlayerName);
    }


    @Test
    public void testGameConfigurationScreenNotNull() {
        // Test case for ensuring the GameConfigurationScreen is not null
        // ...

        // Arrange
        GameConfigurationScreen gameConfigurationScreen = new GameConfigurationScreen();

        // Act

        // Assert
        assertNotNull(gameConfigurationScreen);
    }

    @Test
    public void testGetSelectedSpriteResId() {
        // Test case for getting the selected sprite resource ID in the SelectedSpriteViewModel
        // ...

        // Arrange
        int expectedResId = R.drawable.duck;

        // Act
        SelectedSpriteViewModel viewModel = new SelectedSpriteViewModel();
        viewModel.setSelectedSpriteResId(expectedResId);
        int actualResId = viewModel.getSelectedSpriteResId();

        // Assert
        assertEquals(expectedResId, actualResId);
    }

    @Test
    public void testSetSelectedSpriteResId() {
        // Test case for setting the selected sprite resource ID in the SelectedSpriteViewModel
        // ...

        // Arrange
        int expectedResId = R.drawable.duck;

        // Act
        SelectedSpriteViewModel viewModel = new SelectedSpriteViewModel();
        viewModel.setSelectedSpriteResId(expectedResId);
        int actualResId = viewModel.getSelectedSpriteResId();

        // Assert
        assertEquals(expectedResId, actualResId);
    }

    @Test
    public void testBoardReset() {
        TictactoeManager.startNoUI();
        //    Asserts that the board is not null
        assertNotNull(TictactoeManager.board);
        //    Asserts that there’s an empty board at the start of the game
        assertEquals(TictactoeManager.board, emptyBoard);
        //    Asserts that if a move is played, the board is no longer an empty board
        TictactoeManager.putAtSpot(0, 'x');
        assertNotEquals(TictactoeManager.board, emptyBoard);
        //    Restarts game
        TictactoeManager.restartNoUI();
        //    Asserts that empty board is equal to the reset board
        assertEquals(TictactoeManager.board, emptyBoard);
    }

    @Test
    public void testPutAtSpot() {
        TictactoeManager.startNoUI();
        //    Asserts that the board is not null
        assertNotNull(TictactoeManager.board);
        //    Asserts that there’s an empty board at the start of the game
        assertEquals(TictactoeManager.board, emptyBoard);
        //    Asserts that putAtSpot(0, ‘x’) updates the board with the correct nested structure
        TictactoeManager.putAtSpot(0, 'x');
        assertEquals(TictactoeManager.board, new char[][]{{'x', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}});
        //    Asserts that putAtSpot(0, ‘y’) updates the board with the correct nested structure
        TictactoeManager.putAtSpot(0, 'y');
        assertEquals(TictactoeManager.board, new char[][]{{'y', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}});
        //    Asserts that putAtSpot(5, ‘x’) updates the board with the correct nested structure
        TictactoeManager.putAtSpot(5, 'x');
        assertEquals(TictactoeManager.board, new char[][]{{'y', ' ', ' '}, {' ', ' ', ' '}, {' ', 'x', ' '}});
        //    Asserts that putAtSpot(5, ‘y’) updates the board with the correct nested structure
        TictactoeManager.putAtSpot(5, 'y');
        assertEquals(TictactoeManager.board, new char[][]{{'y', ' ', ' '}, {' ', ' ', ' '}, {' ', 'y', ' '}});
    }

    @Test
    public void testCheckWinner() {
        TictactoeManager.startNoUI();

        TictactoeManager.putAtSpot(0, 'x');
        TictactoeManager.putAtSpot(1, 'x');


        assertEquals(TictactoeManager.findWinningMove('x'), 2);
    }

    @Test
    public void testCheckPositionOverride() {
        TictactoeManager.startNoUI();

        TictactoeManager.putAtSpot(0, 'x');
        TictactoeManager.putAtSpot(0, 'y');

        assertEquals(TictactoeManager.board[0][0], 'y');
    }
}
