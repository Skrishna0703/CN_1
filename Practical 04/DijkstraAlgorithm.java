import java.util.*;

class DijkstraAlgorithm {
    // Method to find the shortest path using Dijkstra's algorithm
    public static void dijkstra(int[][] graph, int source) {
        int n = graph.length; // Number of vertices
        int[] dist = new int[n]; // Array to store the shortest distance from source
        boolean[] visited = new boolean[n]; // Array to mark visited vertices

        // Initialize distances with infinity and visited array with false
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0; // Distance to source is 0

        // Priority queue to store vertices that are being processed
        PriorityQueue<Node> pq = new PriorityQueue<>(n, Comparator.comparingInt(node -> node.distance));

        // Add the source node to the priority queue
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            // Extract the node with the smallest distance
            Node current = pq.poll();
            int u = current.vertex;

            // If vertex is already visited, skip it
            if (visited[u]) continue;
            visited[u] = true;

            // Explore all adjacent vertices of u
            for (int v = 0; v < n; v++) {
                // If there is an edge between u and v and v is not visited
                if (graph[u][v] != 0 && !visited[v]) {
                    // Calculate the new distance to v
                    int newDist = dist[u] + graph[u][v];

                    // If the new distance is shorter, update it
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.add(new Node(v, dist[v])); // Add the vertex with updated distance
                    }
                }
            }
        }

        // Print the shortest distances from the source
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    // Node class representing a vertex and its distance for the priority queue
    static class Node {
        int vertex, distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        int[][] graph = new int[vertices][vertices]; // Adjacency matrix to store edges

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        // Input the edges (u, v, weight)
        System.out.println("Enter the edges (source, destination, weight): ");
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt() ;
            graph[u][v] = weight; // Directed graph (for undirected, set graph[v][u] = weight too)
            graph[v][u] = weight;
        }

        // Input the source vertex for Dijkstra's algorithm
        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        // Run Dijkstra's algorithm
        dijkstra(graph, source);

        scanner.close();
    }
}