import java.lang.reflect.Array;
import java.util.*;

public class Week3Problem1 {
    public static int distance(ArrayList<Integer>[] adj, int[] dist, int v, int w, Queue<Integer> queue, boolean[] visited)
    {
        queue.add(v);
        visited[v] = true;
        while (!queue.isEmpty())
        {
            int element = queue.remove();
            ArrayList<Integer> neighbors = adj[element];
            for (int i = 0; i < neighbors.size(); i++) {
                int n = neighbors.get(i);
                if(dist[n] == -1 && !visited[n])
                {
                    queue.add(n);
                    visited[n] = true;
                    dist[n] = dist[element]+1;
                }
            }
        }
        return dist[w];
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
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        int[] dist = new int[adj.length];
        for (int i = 0; i < dist.length; i++)
            dist[i] = -1;
        dist[x] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[adj.length];
        System.out.println(distance(adj, dist, x, y, queue, visited));
    }
}

