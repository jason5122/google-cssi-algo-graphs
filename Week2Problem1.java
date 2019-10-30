import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Week2Problem1 {
    private static int acyclic(ArrayList<Integer>[] adj, ArrayList<Integer> order, ArrayList<Integer> noIncoming) {
        while (noIncoming.size() > 0) {
            int v = noIncoming.remove(0);
            order.add(v);
            while (adj[v].size() > 0) {
                int w = adj[v].get(0);
                adj[v].remove(0);
                if (hasNoIncoming(adj, w))
                    noIncoming.add(w);
            }
        }
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].size() > 0)
                return 1;
        }
        return 0;
    }

    private static boolean hasNoIncoming(ArrayList<Integer>[] adj, int v) {
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                if (adj[i].get(j) == v)
                    return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer>[] removeVertex(ArrayList<Integer>[] adj, int v)
    {
        if (adj == null
                || v < 0
                || v >= adj.length) {

            return adj;
        }
        ArrayList<Integer>[] arr = (ArrayList<Integer>[])new ArrayList[adj.length - 1];
        System.arraycopy(adj, 0, arr, 0, v);
        System.arraycopy(adj, v + 1, arr, v, adj.length - v - 1);

        //Remove mention of vertex from other vertices
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                if (arr[i].get(j) == v)
                    arr[i].remove(j);
            }
        }
        return arr;
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
        }

        ArrayList<Integer> order = new ArrayList<Integer>(adj.length);
        ArrayList<Integer> noIncoming = new ArrayList<Integer>(adj.length);
        for (int i = 0; i < adj.length; i++)
            if (hasNoIncoming(adj, i))
                noIncoming.add(i);
        System.out.println(acyclic(adj, order, noIncoming));
    }
}
