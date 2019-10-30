import java.util.ArrayList;
import java.util.Scanner;

public class Week1Problem2 {
    private static int numberOfComponents(ArrayList<Integer>[] adj, boolean[] visited) {
        int result = 0;
        for (int i = 0; i < adj.length; i++)
        {
            if (!visited[i]) {
                explore(adj, i, visited);
                result++;
            }
        }
        return result;
    }

    private static void explore(ArrayList<Integer>[] adj, int v, boolean[] visited) {
        visited[v] = true;
        for (int i = 0; i < adj[v].size(); i++) {
            int w = adj[v].get(i);
            if (!visited[w])
                explore(adj, w, visited);
        }
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

        boolean visited[] = new boolean[adj.length];
        System.out.println(numberOfComponents(adj, visited));
    }
}
