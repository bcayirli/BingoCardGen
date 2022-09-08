package bingo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BingoUtilsTest {
    BingoUtils bu;

    @Before
    public void reset() {
        bu = new BingoUtils();
    }

    @Test
    public void testShuffleNumbers() {
        int[] result = bu.shuffleNumbers();

        assertEquals(result.length, 10);
        assertEquals(result[result.length - 1], 0);
        assertNotEquals(result[0], 0);
    }

    @Test
    public void testGetRandom() {
        int min = 1;
        int max = 10;

        for (int i = 0; i < 100; i++) {
            int random = bu.getRandom(min, max);

            assertTrue(random > 0);
            assertTrue(random < 10);
        }
    }
}
