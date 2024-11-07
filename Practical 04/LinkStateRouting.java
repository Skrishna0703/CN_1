import java.util.*;

public class LinkStateRouting {

    private int[][] graph;
    private int numberOfNodes;

    public LinkStateRouting(int numberOfNodes, int[][] graph) {
        this.numberOfNodes = numberOfNodes;
        this.graph = graph;
    }

    public void dijkstra(int source) {
        int[] distance = new int[numberOfNodes];
        boolean[] visited = new boolean[numberOfNodes];
        int[] nextHop = new int[numberOfNodes];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int i = 0; i < numberOfNodes; i++) {

            int u = selectMinVertex(distance, visited);
            visited[u] = true;

            for (int v = 0; v < numberOfNodes; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE
                        && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    nextHop[v] = u;
                }
            }
        }

        displayRoutingTable(source, distance, nextHop);
    }

    private int selectMinVertex(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < numberOfNodes; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void displayRoutingTable(int source, int[] distance, int[] nextHop) {
        System.out.println("Routing Table for Node " + source);
        System.out.println("Destination\tCost\tNext Hop");
        for (int i = 0; i < numberOfNodes; i++) {
            if (i == source) {
                System.out.println(i + "\t\t" + distance[i] + "\t\t" + source);
            } else {
                System.out.println(i + "\t\t" + distance[i] + "\t\t" + nextHop[i]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int nodes = scanner.nextInt();

        int[][] graph = new int[nodes][nodes];
        System.out.println("Enter the cost matrix (use 999 for infinity): ");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                graph[i][j] = scanner.nextInt();
                if (graph[i][j] == 999)
                    graph[i][j] = Integer.MAX_VALUE;
            }
        }

        LinkStateRouting ls = new LinkStateRouting(nodes, graph);

        for (int i = 0; i < nodes; i++) {
            ls.dijkstra(i);
        }

        scanner.close();
    }
}
