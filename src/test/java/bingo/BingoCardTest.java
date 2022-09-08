package bingo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class BingoCardTest {
    BingoCard card;
    BingoUtils utils;

    @Before
    public void reset() {
        utils = new BingoUtils();
        card = new BingoCard(utils);
    }

    @Test
    public void testClearCard() {
        card.clearCard();

        Assert.assertTrue(card.getCard().size() == 3);
        Assert.assertTrue(card.getCard().get(0) == null);
    }

    @Test
    public void testSizeSpreadTheNumbersToStrips() {
        Map<Integer, List<Integer>> strips = card.spreadTheNumbersToStrips();

        Assert.assertEquals(strips.size(), 6);
        for (int i = 1; i <= 6; i++) {
            Assert.assertEquals(strips.get(i).size(), 15);
        }
    }

    @Test
    public void testUniqueSpreadTheNumbersToStrips() {
        Map<Integer, List<Integer>> strips = card.spreadTheNumbersToStrips();

        int aRandomStripNumber = utils.getRandom(1, 7);
        int aRandomCardValue = utils.getRandom(0, 15);
        int aValue = strips.get(aRandomStripNumber).get(aRandomCardValue);
        Assert.assertTrue(aValue > 0);
        Assert.assertTrue(aValue <= 90);
        int aValueCount = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j < strips.get(i).size(); j++) {
                if (strips.get(i).get(j) == aValue) {
                    aValueCount++;
                }
            }
        }

        Assert.assertEquals(aValueCount, 1);
    }

    @Test
    public void testPrepareCard() {
        Map<Integer, List<Integer>> strips = card.spreadTheNumbersToStrips();

        card.prepareCard(strips);

        Assert.assertEquals(card.getCard().size(), 3);
        Assert.assertEquals(card.getCard().get(1).size(), 5);
        Assert.assertEquals(card.getCard().get(2).size(), 5);
        Assert.assertEquals(card.getCard().get(3).size(), 5);
    }

    @Test
    public void testDuration() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            Map<Integer, List<Integer>> strips = card.spreadTheNumbersToStrips();
            //bingoUtils.printStripMap(strips);
            card.prepareCard(strips);
            card.printCard();
            card.clearCard();
        }
        long end = System.currentTimeMillis();
        Assert.assertTrue((end - start) < 1000);
    }
}
