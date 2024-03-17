import java.util.*;
import java.util.Queue;
import java.awt.*;

public class Graph {
    private ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(16); // Adjacency List
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<Integer> path = new ArrayList<Integer>();
    private boolean reached = true;
    private int index = 0;

    public Graph() {
        for (int i = 0; i < 16; i++)
            adj.add(new ArrayList<Integer>());
        // Node Creation
        nodes.add(new Node(412, 100));
        nodes.add(new Node(512, 100));
        nodes.add(new Node(212, 250));
        nodes.add(new Node(312, 250));
        nodes.add(new Node(412, 250));
        nodes.add(new Node(512, 250));
        nodes.add(new Node(612, 250));
        nodes.add(new Node(712, 250));
        nodes.add(new Node(112, 400));
        nodes.add(new Node(212, 400));
        nodes.add(new Node(312, 400));
        nodes.add(new Node(412, 400));
        nodes.add(new Node(512, 400));
        nodes.add(new Node(612, 400));
        nodes.add(new Node(712, 400));
        nodes.add(new Node(812, 400));

        // Edge Adding
        addEdge(0, 1);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(4, 5);
        addEdge(5, 6);
        addEdge(6, 7);
        addEdge(8, 9);
        addEdge(9, 10);
        addEdge(10, 11);
        addEdge(11, 12);
        addEdge(12, 13);
        addEdge(13, 14);
        addEdge(14, 15);
        addEdge(0, 3);
        addEdge(1, 6);
        addEdge(2, 8);
        addEdge(7, 15);
        addEdge(4, 11);
        addEdge(5, 12);
    }

    private void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private ArrayList<Integer> bfs(int s, int e) {
        Integer[] prev = solve(s);
        return reconstructPath(s, e, prev);
    }

    private Integer[] solve(int s) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);

        boolean[] visited = new boolean[16];
        for (int i = 0; i < 16; i++)
            visited[i] = false;
        visited[s] = true;

        Integer[] prev = new Integer[16];
        for (int i = 0; i < 16; i++)
            prev[i] = -1;

        while (!q.isEmpty()) {
            int node = q.poll();
            ArrayList<Integer> neighbours = adj.get(node);

            for (int next : neighbours) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                    prev[next] = node;
                }
            }
        }
        return prev;
    }

    private ArrayList<Integer> reconstructPath(int s, int e, Integer[] prev) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        for (Integer at = e; at != -1; at = prev[at]) {
            path.add(at);
        }

        Collections.reverse(path);

        if (path.get(0) == s)
            return path;
        return new ArrayList<Integer>();
    }

    private int closestNode(Warrior w) {
        double distance = 1000;
        int closest = 0;
        for (int i = 0; i < 16; i++) {
            double newDist = findDist(w, nodes.get(i));
            if (newDist < distance) {
                distance = newDist;
                closest = i;
            }
        }
        return closest;
    }

    private double findDist(Warrior w, Node n) {
        return Math.sqrt(Math.pow(w.getX() - n.getCenterX(), 2) + Math.pow(w.getY() - n.getCenterY(), 2));
    }

    public double getLocation(Computer c, Warrior w) {
        if (reached) {
            path = bfs(closestNode(c), closestNode(w));
            reached = false;
        } else if (findDist(c, nodes.get(path.get(index))) < 70) {
            if (index + 1 < path.size())
                index++;
            else {
                reached = true;
                index = 0;
            }
        }
        System.out.println(reached + " " + index);

        return nodes.get(path.get(index)).getCenterX();
    }

    public void draw(Graphics g, Camera c) {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).draw(g, c, i);
        }
    }
}
