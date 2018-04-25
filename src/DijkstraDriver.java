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
            System.out.println("Please enter the number of routers (must be >= 2): ");
            numOfRouters = Integer.parseInt(sysIn.readLine());
        }
        NodeFactory nodes = new NodeFactory(numOfRouters);
        nodes.ValidateTopoTxt();

        DijkstrasAlgorithm spf1 = new DijkstrasAlgorithm(nodes);
        spf1.DijkstraLoop();
    }
}