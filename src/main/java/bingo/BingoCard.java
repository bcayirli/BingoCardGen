package bingo;

import java.util.*;

public class BingoCard {
    private final BingoUtils utils;
    Map<Integer, Map<Integer, Integer>> card;

    public BingoCard(BingoUtils utils) {
        this.utils = utils;

        card = new HashMap<>();
        for (int j = 0; j < BingoUtils.ROW_COUNT; j++) {
            card.put(j + 1, new HashMap<>());
        }
    }

    public void clearCard() {
        card.clear();
        for (int j = 0; j < 3; j++) {
            card.put(j + 1, new HashMap<>());
        }
    }

    public Map<Integer, Map<Integer, Integer>> getCard() {
        return card;
    }

    private void fillEachStrip(Map<Integer, List<Integer>> strips, int[] shuffledNumbers, int columnNumber) {
        // First, fill each strip row with random numbers
        for (int i = 0; i < BingoUtils.STRIP_COUNT; i++) {
            int val = shuffledNumbers[i] + (columnNumber * 10);
            // Omit 0
            if (val > 0) {
                strips.get(i + 1).add(val);
            }
        }
    }

    /*
     * Find the values that are not added to strip cards yet.
     * */
    private void findRemnantValues(List<Integer> remnants, int[] shuffledNumbers, int columnNumber) {
        for (int i = BingoUtils.STRIP_COUNT; i <= BingoUtils.COLUMN_COUNT; i++) {
            int val = shuffledNumbers[i] + (columnNumber * 10);
            if (val > 0) {
                remnants.add(val);
            }
        }

    }

    private void addRemnantsToStrips(Map<Integer, List<Integer>> strips, List<Integer> remnants) {
        for (int remnant : remnants) {
            int stripsIndex = utils.getRandom(0, 6) + 1;
            int minSize = strips.get(stripsIndex).size();
            for (int i = 1; i <= BingoUtils.STRIP_COUNT; i++) {
                if (strips.get(i).size() < minSize) {
                    stripsIndex = i;
                }
            }

            strips.get(stripsIndex).add(remnant);
            Collections.sort(strips.get(stripsIndex));
        }
    }

    public Map<Integer, List<Integer>> spreadTheNumbersToStrips() {
        Map<Integer, List<Integer>> strips = new HashMap<>();
        List<Integer> remnants = new ArrayList<>();

        // Create a map for each strip
        for (int i = 1; i <= BingoUtils.STRIP_COUNT; i++) {
            strips.put(i, new ArrayList<>());
        }

        int col = 0;
        while (col < BingoUtils.COLUMN_COUNT) {
            int[] shuffledNumbers = utils.shuffleNumbers();
            // First, fill each strip row with random numbers
            fillEachStrip(strips, shuffledNumbers, col);

            findRemnantValues(remnants, shuffledNumbers, col);
            col++;
        }
        // Add the final 90, cannot reach through shuffled array
        remnants.add(90);

        addRemnantsToStrips(strips, remnants);

        return strips;
    }

    public void prepareCard(Map<Integer, List<Integer>> strips) {
        for (int i = 1; i <= BingoUtils.STRIP_COUNT; i++) {
            List<Integer> line = strips.get(i);

            for (int j = line.size() - 1; j >= 0; j--) {
                int column = line.get(j) / 10;
            }

            for (int value : line) {
                int column = value / 10;

                for (int j = card.size(); j > 0; j--) {
                    if (!card.get(j).containsKey(column)) {
                        if (card.get(j).size() < 5) {
                            card.get(j).put(column, value);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void printCard() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < card.size(); j++) {
            //System.out.println(card.get(j + 1) + " .. " + card.get(j + 1).size());
            for (int column = 0; column <= 9; column++) {
                if (card.get(j + 1).containsKey(column)) {
                    sb.append(String.format("%02d", card.get(j + 1).get(column))).append(" ");
                } else {
                    sb.append("XX ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}