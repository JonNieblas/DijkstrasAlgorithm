import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Driver class containing main method
 *
 * Jonathan Nieblas
 */
public class DijkstraDriver {
    public static void main(String args[]) throws IOException{
        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        int numOfRouters = 0;
        while(numOfRouters < 2){
            System.out.println("Please enter the number of numOfRouters (must be >= 2): ");
            numOfRouters = Integer.parseInt(sysIn.readLine());
        }
        NodeFactory nodes = new NodeFactory(numOfRouters);
        nodes.ValidateTopoTxt();

        ShortestPathFinder spf1 = new ShortestPathFinder(nodes);
        spf1.FindBestPath(0, 3);//currently testing to find best path to node 3

    }
}