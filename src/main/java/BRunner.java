import bingo.BingoCard;
import bingo.BingoUtils;

import java.util.List;
import java.util.Map;

public class BRunner {
    public static void main(String[] args) {
        BingoUtils bingoUtils = new BingoUtils();

        BingoCard card = new BingoCard(bingoUtils);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            Map<Integer, List<Integer>> strips = card.spreadTheNumbersToStrips();
            //bingoUtils.printStripMap(strips);
            card.prepareCard(strips);
            card.printCard();
            card.clearCard();
        }
        long end = System.currentTimeMillis();

        System.out.println("Total time = " + (end - start));
    }
}
