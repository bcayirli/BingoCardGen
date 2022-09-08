package bingo;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class BingoUtils {
    public static int STRIP_COUNT = 6;
    public static int COLUMN_COUNT = 9;
    public static int ROW_COUNT = 3;
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    Random random;

    public BingoUtils() {
        random = new Random();
    }

    public int[] shuffleNumbers() {
        int[] tmpArray = numbers;
        for (int i = 0; i < tmpArray.length; i++) {
            int randomIndexToSwap = random.nextInt(tmpArray.length);
            if (tmpArray[randomIndexToSwap] != 0 && tmpArray[i] != 0) {
                int temp = tmpArray[randomIndexToSwap];
                tmpArray[randomIndexToSwap] = tmpArray[i];
                tmpArray[i] = temp;
            }
        }

        return tmpArray;
    }

    public int getRandom(int min, int max) {
        return random.nextInt(min, max);
    }

    public void printStripMap(Map<Integer, List<Integer>> strips) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry e : strips.entrySet()) {
            List<Integer> list = (List<Integer>) e.getValue();
            sb.append(list.size()).append("  --  ");
            for (int i : list) {
                sb.append(String.format("%02d", i)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
