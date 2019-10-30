import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Week4Problem2 {

    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int n = adj.length;
        int[] dist = new int[n];
        int[] prev = new int[n];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE - 1000;
            prev[i] = -1;
        }
        dist[0] = 0;

        for (int a = 1; a < n; a++) {
            for (int i = 0; i < adj.length; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int v = adj[i].get(j);
                    int u = i;
                    if (dist[v] > dist[u] + cost[i].get(j)) {
                        dist[v] = dist[u] + cost[i].get(j);
                        prev[v] = u;
                    }
                }
            }
        }

        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int v = adj[i].get(j);
                int u = i;
                if (dist[v] > dist[u] + cost[i].get(j)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    /*
    private static int negativeCycle(ArrayList<Integer>[] adj, int[] dist, ArrayList<Integer>[] cost, int[] prev, Map<Integer, Integer> queue) {
        for (int i = 0; i < dist.length; i++)
            queue.put(i, dist[i]);
        int count = 0;
        int threshold = 0;
        for (int i = 1; i < cost.length; i++) {
            for (int j = 0; j < cost[i].size(); j++) {
                if (cost[i].get(j) < 0)
                    threshold += cost[i].get(j);
            }
        }
        while (queue.size() > 0) {
            int u = (int) queue.keySet().toArray()[0];
            queue = queue.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            queue.remove(u);
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i);
                int theCost = cost[u].get(i);
                if (theCost < 0)
                    count += theCost;
                if (count < threshold)
                    return 1;
                if (dist[v] > dist[u] + theCost) {
                    dist[v] = dist[u] + theCost;
                    prev[v] = u;
                    queue.put(v, dist[v]);
                }
            }
        }

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
                    return 1;
                }
            }
        }

        return 0;
    }
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
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
        //Map<Integer, Integer> queue = new HashMap<>();

        System.out.println(negativeCycle(adj, cost));
    /*
    /*
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist, int[] prev, ArrayList<Integer> list) {
        for (int u = 0; u < adj.length; u++) {
            for (int j = 0; j < adj[u].size(); j++) {
                int v = adj[u].get(j);
                int theCost = cost[u].get(j);
                System.out.println(dist[v] + " versus " + dist[u] + " + " + theCost);
                System.out.println(u == adj.length-1);
                if (u == adj.length-1 && dist[v] > dist[u] + theCost) {
                    list.add(v);
                    dist[v] = dist[u] + theCost;
                }
                else if (dist[v] > dist[u] + theCost) {
                    dist[v] = dist[u] + theCost;
                }
            }
            System.out.println(Arrays.toString(dist));
        }
        System.out.println("list of v runs " + list);
        return 0;
    }

    private static int negativeCycle(ArrayList<Integer>[] adj, int s, ArrayList<Integer>[] cost) {
        int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        for (int i = 0; i < adj.length; i++) { //For each vertex V in G
            dist[i] = Integer.MAX_VALUE - 1000;
            prev[i] = -1;
        }
        dist[s] = 0;
        for (int i = 0; i < adj.length; i++) { //For each vertex V in G
            int v = i;
            for (int j = 0; j < adj[v].size() - 1; j++) { //For each edge (U, V) in G
                int u = adj[v].get(j);
                int tempDistance = dist[u] + cost[v].get(j);
                if (tempDistance < dist[v]) {
                    dist[v] = tempDistance;
                    prev[v] = u;
                }
            }
        }
        for (int i = 0; i < adj.length; i++) { //For each vertex V in G
            int v = i;
            for (int j = 0; j < adj[v].size(); j++) { //For each edge (U, V) in G
                int u = adj[v].get(j);
                if (dist[u] + cost[v].get(j) < dist[v])
                    return 1;
            }
        }
        return 0;
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

        int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE -1000;
            prev[i] = -1;
        }
        dist[0] = 0;
        int x = 0;
        Map<Integer, Integer> queue = new HashMap<>();
        boolean[] visited = new boolean[adj.length];
        ArrayList<Integer> list = new ArrayList<Integer>();
        //System.out.println(negativeCycle(adj, cost, 0, dist, prev, list));
        System.out.println(negativeCycle(adj, 0, cost));

        */
    }
}

