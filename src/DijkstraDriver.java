import java.io.IOException;

/**
 * Driver class containing main method.
 * For the info in the topo.txt included,
 * 6 can be used as a default number of routers.
 *
 * Jonathan Nieblas
 */
public class DijkstraDriver{
    public static void main(String args[]) throws IOException{
        NodeFactory nodes = new NodeFactory();
        nodes.ValidateTopoTxt();

        DijkstrasAlgorithm spf1 = new DijkstrasAlgorithm(nodes);
        spf1.DijkstraLoop();
        spf1.BuildForwardingTable();
    }
}