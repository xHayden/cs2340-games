package com.cs2340group7.games;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.testng.annotations.Test;

public class WordleTest {


    @Test
    public void testCheckWordAllCorrect() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("TABLE");
        Character[] guess = {'T', 'A', 'B', 'L', 'E'}; // "TABLE"
        int[] result = controller.checkWord(guess);
        int[] expected = {2, 2, 2, 2, 2};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCheckWordAllCorrect2() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("FAVOR");
        Character[] guess = {'F', 'A', 'V', 'O', 'R'}; // "FAVOR"
        int[] result = controller.checkWord(guess);
        int[] expected = {2, 2, 2, 2, 2};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCheckWordNoMatch() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("WORLD");
        int[] result = controller.checkWord(new Character[]{'X', 'X', 'X', 'X', 'X'});
        int[] expected = new int[]{3, 3, 3, 3, 3};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCheckWordAllYellow() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("WORLD");
        int[] result = controller.checkWord(new Character[]{'D', 'W', 'L', 'O', 'R'});
        int[] expected = new int[]{1, 1, 1, 1, 1};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCheckWordMixedGreenAndGray() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("WORLD");
        int[] result = controller.checkWord(new Character[]{'W', 'X', 'R', 'L', 'Y'});
        int[] expected = new int[]{2, 3, 2, 2, 3};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCheckWordMixedYellowAndGray() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("WORLD");
        int[] result = controller.checkWord(new Character[]{'D', 'X', 'L', 'Y', 'R'});
        int[] expected = new int[]{1, 3, 1, 3, 1};
        assertArrayEquals(expected, result);
    }


    @Test
    public void testCheckWordWithNull() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("FAVOR");
        try {
            controller.checkWord(null);
            Assert.fail("Expected an NullPointerException to be thrown");
        } catch (NullPointerException npe) {
            assertEquals(npe.getMessage(), "Answer array cannot be null");
        }
    }

    @Test
    public void testCheckWordWithEmptyArray() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("FAVOR");
        Character[] guess = {};
        int[] result = controller.checkWord(guess);
        int[] expected = {};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSetAndGetKey() {
        WordleController controller = WordleController.getInstance();
        String testKey = "HELLO";
        controller.setKey(testKey);
        assertEquals(controller.getKey(), testKey, "HELLO");
    }

    @Test
    public void testCheckWordMixedGreenYellowAndGray() {
        WordleController controller = WordleController.getInstance();
        controller.setKey("WORLD");
        int[] result = controller.checkWord(new Character[]{'W', 'R', 'X', 'D', 'L'});
        int[] expected = new int[]{2, 1, 3, 1, 1};
        assertArrayEquals(expected, result);
    }


}
