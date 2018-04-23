import java.io.IOException;

/**
 * Driver class containing main method
 *
 * Jonathan Nieblas
 */
public class DijkstraDriver {
    public static void main(String args[]) throws IOException{

        TopoValidator validator = new TopoValidator();

        validator.ValidateTopoTxt();
    }
}