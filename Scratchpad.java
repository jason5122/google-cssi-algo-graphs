import java.util.*;
import java.util.stream.Collectors;

public class Scratchpad {


    public static void main(String[] args) {
        Map<Integer, Integer> blacklist = new HashMap<Integer, Integer>();
        blacklist.put(0,0);
        blacklist.put(0,1);
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 1;
        boolean hasFirst = blacklist.containsKey(x1) && blacklist.get(x1) == y1;
        boolean hasSecond = blacklist.containsKey(x2) && blacklist.get(x2) == y2;
        System.out.println(blacklist.get(x1) == y1);
    }
}
