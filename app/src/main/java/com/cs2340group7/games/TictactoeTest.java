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
    public void testSelectedSpriteResId() {
        // Test case for setting and retrieving the selected sprite resource ID
        // ...

        // Arrange
        SelectedSpriteViewModel viewModel = new SelectedSpriteViewModel();
        int spriteResId = R.drawable.duck;

        // Act
        viewModel.setSelectedSpriteResId(spriteResId);
        int selectedSpriteResId = viewModel.getSelectedSpriteResId();

        // Assert
        assertEquals(spriteResId, selectedSpriteResId);
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



}
