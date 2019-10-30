import java.util.ArrayList;
import java.util.Scanner;

public class Week5Problem1 {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0;
        int size = x.length;
        int indexI = 0;
        int indexJ = 0;
        double min = Integer.MAX_VALUE - 2000;
        boolean[] blacklistI = new boolean[size];
        boolean[] blacklistJ = new boolean[size];
        boolean[] blacklistReverseI = new boolean[size];
        boolean[] blacklistReverseJ = new boolean[size];
        for (int a = 0; a < size - 1; a++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double calc = calculateCosts(x, y, i, j);
                    if (calc != 0.0 && !blacklistI[i] && !blacklistJ[j] && !blacklistReverseI[j] && !blacklistReverseJ[i] && calc < min) {
                        min = calc;
                        indexI = i;
                        indexJ = j;
                        blacklistI[i] = blacklistJ[j] = blacklistReverseJ[i] = blacklistReverseI[j] = true;
                        result += calc;
                    }
                }
            }
            System.out.println("min value was " + min + ", between " + "(" + x[indexI] + ", " + y[indexI] + ") and (" + x[indexJ] + ", " + y[indexJ] + ")");
            indexI = indexJ = 0;
            min = Integer.MAX_VALUE - 2000;
        }
        return result;
    }

    private static double calculateCosts(int[] x, int[] y, int i, int j) {
        int x1 = x[i];
        int y1 = y[i];
        int x2 = x[j];
        int y2 = y[j];
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }

        System.out.println(minimumDistance(x, y));

    }
}

