import java.util.Arrays;
import java.util.Scanner;

public class DistanceVectorRouting {

    private int[][] costMatrix; // Adjacency matrix representing the network
    private int[][] distance;   // Distance vector for each node
    private int[][] nextHop;    // Next hop for each node
    private int numberOfNodes;

    // Constructor
    public DistanceVectorRouting(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        costMatrix = new int[numberOfNodes][numberOfNodes];
        distance = new int[numberOfNodes][numberOfNodes];
        nextHop = new int[numberOfNodes][numberOfNodes];
    }

    // Method to initialize the cost matrix
    public void initializeCostMatrix(int[][] matrix) {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                costMatrix[i][j] = matrix[i][j];
                distance[i][j] = matrix[i][j];
                if (i == j) {
                    nextHop[i][j] = i; // Same node
                } else if (matrix[i][j] != 999) {
                    nextHop[i][j] = j; // Directly connected node
                } else {
                    nextHop[i][j] = -1; // No path available
                }
            }
        }
    }

    // Bellman-Ford algorithm for distance vector routing
    public void calculateRoutes() {
        boolean changed;
        do {
            changed = false;

            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    for (int k = 0; k < numberOfNodes; k++) {
                        if (distance[i][k] + distance[k][j] < distance[i][j]) {
                            distance[i][j] = distance[i][k] + distance[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            changed = true;
                        }
                    }
                }
            }

        } while (changed);
    }

    // Method to display the routing table
    public void displayRoutingTable() {
        System.out.println("Routing Table:");
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println("Node " + i + ":");
            System.out.println("Destination\tCost\tNext Hop");
            for (int j = 0; j < numberOfNodes; j++) {
                System.out.println(j + "\t\t" + distance[i][j] + "\t\t" + nextHop[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int nodes = scanner.nextInt();

        DistanceVectorRouting dvr = new DistanceVectorRouting(nodes);

        // Input the cost matrix
        int[][] costMatrix = new int[nodes][nodes];
        System.out.println("Enter the cost matrix (use 999 for infinity): ");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }

        dvr.initializeCostMatrix(costMatrix);
        dvr.calculateRoutes();
        dvr.displayRoutingTable();
    }
}
