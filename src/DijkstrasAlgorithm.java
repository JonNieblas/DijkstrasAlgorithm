import java.util.*;

public class DijkstrasAlgorithm {

    NodeFactory nodes;
    private int numOfNodes;
    private int[][] costMatrix;
    private Set pathsKnown;//N'
    private Set edgesInShortestPath;//Y'
    private HashMap<Integer, Integer> currentPathCostMatrix;//D(i)
    private HashMap<Integer, Integer> predecessorNodeMatrix;//p(i)
    private HashMap<Integer, String> edgeHistory = new HashMap<>();

    public DijkstrasAlgorithm(NodeFactory nodes){
        this.nodes = nodes;
        numOfNodes = nodes.getNumOfRouters();
        costMatrix = nodes.getCostMatrix();
        currentPathCostMatrix = new HashMap<>();
        predecessorNodeMatrix = new HashMap<>();
    }

    public void Initialization(){
        pathsKnown = new HashSet();
        pathsKnown.add("V" + 0);
        edgesInShortestPath = new HashSet();
        currentPathCostMatrix.put(0, 0);
        for (int i = 1; i < numOfNodes; i++){
            if(costMatrix[0][i] != 0){
                currentPathCostMatrix.put(i, costMatrix[0][i]);
                predecessorNodeMatrix.put(i, 0);
            } else{
                currentPathCostMatrix.put(i, 9999);
                predecessorNodeMatrix.put(i, 9999);
            }
        }
        IntermediateResults(0);
    }

    public void IntermediateResults(int loopNum){
        System.out.println("");
        System.out.println("Loop " + loopNum + " Results: ");
        System.out.println("The set N' contains: " + pathsKnown.toString());
        System.out.println("The set Y' contains: " + edgesInShortestPath.toString());
        for(int node = 1; node < numOfNodes; node++){
            System.out.println("D(" + node + ") = " + currentPathCostMatrix.get(node));
            System.out.println("P(" + node + ") = V" + predecessorNodeMatrix.get(node));
        }
    }

    public void DijkstraLoop(){
        Initialization();
        for(int currentNode = 1; currentNode < numOfNodes; currentNode++){
            if(!pathsKnown.contains("V" + currentNode)){
                FindShortestPath(currentNode);
                UpdateEdges(currentNode);
            }
            pathsKnown.add("V" + currentNode);
            UpdatePreviousAdjacentNodes(currentNode);
            IntermediateResults(currentNode);
        }
    }

    public void FindShortestPath(int currentNode){
        for(int adjacentNode = 0; adjacentNode < numOfNodes; adjacentNode++){
            if(costMatrix[currentNode][adjacentNode] != 0){
                FindBestAdjacentNode(currentNode, adjacentNode);
            }
        }
    }

    public void FindBestAdjacentNode(int currentNode, int adjacentNode){
        int currentCost = currentPathCostMatrix.get(adjacentNode) + costMatrix[currentNode][adjacentNode];
        int oldCost = currentPathCostMatrix.get(currentNode);
        if((currentCost <= oldCost) && (oldCost != 0)){
            currentPathCostMatrix.put(currentNode, currentCost);
            predecessorNodeMatrix.put(currentNode, adjacentNode);
        }
    }

    public void UpdateEdges(int currentNode){
        String edge = "(" + predecessorNodeMatrix.get(currentNode) + "," + currentNode + ")";
        String reverseEdge =  "(" + currentNode + "," + predecessorNodeMatrix.get(currentNode) + ")";
        if((edgeHistory.get(currentNode) != null) && (!edgeHistory.get(currentNode).equals(edge))
                && (!edgeHistory.get(currentNode).equals(reverseEdge))){
            edgesInShortestPath.remove(edgeHistory.get(currentNode));
        }
        edgeHistory.put(currentNode, edge);
        edgesInShortestPath.add(edge);
    }

    public void UpdatePreviousAdjacentNodes(int currentNode){
        for(int i = 1; i < numOfNodes; i++){
            if(pathsKnown.contains("V" + i)){
                if(costMatrix[i][currentNode] != 0){
                    FindShortestPath(i);
                    UpdateEdges(i);
                }
            }
        }
    }

    public void BuildForwardingTable(){
        for(int destinationNode = 1; destinationNode < numOfNodes; destinationNode++){

        }
    }
}