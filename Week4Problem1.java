import java.util.*;
import java.util.stream.Collectors;

public class Week4Problem1 {
    private static int distance(ArrayList<Integer>[] adj, int[] dist, ArrayList<Integer>[] cost, int[] prev, Map<Integer, Integer> queue, int x, int y) {
        for (int i = 0; i < dist.length; i++)
            queue.put(i, dist[i]);
        while (queue.size() > 0) {
            int u = (int) queue.keySet().toArray()[0];
            queue = queue.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            queue.remove(u);
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i);
                int theCost = cost[u].get(i);
                if (dist[v] > dist[u] + theCost) {
                    dist[v] = dist[u] + theCost;
                    prev[v] = u;
                    queue.put(v, dist[v]);
                }
            }
        }
        if (dist[y] >= Integer.MAX_VALUE -2000)
            return -1;
        return dist[y];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;

        int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE -1000;
            prev[i] = -1;
        }
        dist[x] = 0;
        Map<Integer, Integer> queue = new HashMap<>();
        boolean[] visited = new boolean[adj.length];

        System.out.println(distance(adj, dist, cost, prev, queue, x, y));
    }
}
