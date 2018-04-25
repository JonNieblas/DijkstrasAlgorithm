import java.util.HashMap;

public class ShortestPathFinder {

    NodeFactory nodes;
    private int numOfNodes;
    private HashMap<String, Integer> costMap;
    private HashMap<String, Integer> costsPerShortestTree;
    HashMap<Integer, String> shortestTreesByNodeName = new HashMap<>();

    int bestPath = 0;
    String bestPathName;

    public ShortestPathFinder(NodeFactory nodes){
        this.nodes = nodes;
        numOfNodes = nodes.getNumOfRouters();
        costMap = nodes.getCostMap();
    }

    public void FindBestPath(int j, int testNode){
        String currentNode;
        int compoundCost = 0;
        String compoundName = "";
        for(int i = j; i < testNode; i++){
            for(int k = 0; k <= testNode; i++){
                currentNode = i + "-" + k;
                if(costMap.get(currentNode) != null){
                    compoundCost = costMap.get(currentNode) + compoundCost;
                    compoundName = currentNode + ",";
                    ComparePaths(k, testNode, compoundCost, compoundName);
                    FindBestPath(k, testNode);
                }
            }
        }
    }

    public void ComparePaths(int k, int testNode, int compoundCost, String compoundName){
        if(k == testNode){
            if((bestPath == 0) || (compoundCost < bestPath)){
                bestPath = compoundCost;
                shortestTreesByNodeName.put(testNode, compoundName);
                costsPerShortestTree.put(compoundName, compoundCost);
            }
        }
    }
}