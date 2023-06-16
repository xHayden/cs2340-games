package com.cs2340group7.games;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class TictactoeTest {

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


}
