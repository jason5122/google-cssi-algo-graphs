import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Week2Problem2 {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj, boolean[] visited, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++)
            map.put(i, postorder[i]);
        Map<Integer, Integer> reverseSortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        ArrayList<Integer> reversePostorder = new ArrayList<Integer>(reverseSortedMap.keySet());
        return reversePostorder;
    }

    private static void dfsPlus(ArrayList<Integer>[] adj, boolean[] visited, int[] clock, int[] preorder, int[] postorder) {
        for (int i = 0; i < adj.length; i++)
        {
            if (!visited[i])
                explorePlus(adj, i, visited, clock, preorder, postorder);
        }
    }

    private static void explorePlus(ArrayList<Integer>[] adj, int v, boolean[] visited, int[] clock, int[] preorder, int[] postorder) {
        visited[v] = true;
        preorder[v] = clock[0];
        clock[0]++;
        for (int i = 0; i < adj[v].size(); i++) {
            int w = adj[v].get(i);
            if (!visited[w])
                explorePlus(adj, w, visited, clock, preorder, postorder);
        }
        postorder[v] = clock[0];
        clock[0]++;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();

            //adj[y - 1].add(x - 1); //Reverse add
            adj[x - 1].add(y - 1); //Normal add
        }

        int v = 0;
        boolean visited[] = new boolean[adj.length];
        int[] clock = {1};
        int[] preorder = new int[adj.length];
        int[] postorder = new int[adj.length];
        dfsPlus(adj, visited, clock, preorder, postorder);
        visited = new boolean[adj.length];
        ArrayList<Integer> order = toposort(adj, visited, postorder);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}
