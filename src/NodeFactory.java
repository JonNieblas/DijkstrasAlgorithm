import java.io.*;
import java.util.ArrayList;

/**
 * Class NodeFactory builds a matrix of nodes and
 * their edge costs from topo.txt, or whichever file
 * contains the cost information.
 *
 * Jonathan Nieblas
 */

public class NodeFactory {

    BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
    private int numOfRouters;
    private int[] numsInLine;
    private int lineCount;
    private int manualEntryCounter = 0;
    private String fileName = "topo.txt";
    private int[][] costMatrix;
    private int maxRouterNum = 0;

    public void CreateMatrix() throws IOException{
        while(numOfRouters < 2){
            System.out.println("Please enter the number of routers (must be >= 2): ");
            numOfRouters = Integer.parseInt(sysIn.readLine());
        }
        costMatrix = new int[numOfRouters][numOfRouters];
    }

    public void ExtractNumsInLine(String line) {
        int counter = 0;
        String[] numberSeparator = line.split("\\t");
        numsInLine = new int[numberSeparator.length];
        for (String i : numberSeparator) {
            numsInLine[counter] = Integer.parseInt(i);
            counter++;
        }
        if (counter < 3 || counter > 3) {
            System.out.println("Incorrect number of values at line " + lineCount);
            manualEntryCounter++;
        }
    }

    public void ValidateTopoTxt() throws IOException {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            CreateMatrix();
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (manualEntryCounter > 0) {
                    fileReader.close();
                    break;
                }
                lineCount++;
                ExtractNumsInLine(currentLine);
                ValidateRouterNums();
            }
            IncorrectUserGivenRegisters();
            if (manualEntryCounter > 0) {
                RestartFileRead();
            }
            fileReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(fileName + " does not exist.");
            RestartFileRead();
        }
    }

    public void IncorrectUserGivenRegisters(){
        if((maxRouterNum + 1) < numOfRouters){
            System.out.println("Router number exceeds max number of routers in the file.");
            numOfRouters = 0;
            maxRouterNum = 0;
            manualEntryCounter++;
        }
    }

    public void RestartFileRead() throws IOException {
        System.out.print("Please input the name of the cost input file: ");
        fileName = sysIn.readLine();
        numOfRouters = 0;
        lineCount = 0;
        manualEntryCounter = 0;
        ValidateTopoTxt();
    }

    public void ValidateRouterNums() throws IOException{
        int routerOne = 0;
        int routerTwo = 0;
        int cost = 0;
        for (int i = 0; i < 3; i++) {
            int currentNum = numsInLine[i];
            if (i < 2) {
                if(currentNum > maxRouterNum){
                    maxRouterNum = currentNum;
                }
                if ((currentNum > numOfRouters - 1) || (currentNum < 0)) {
                    System.out.println("Invalid router value " + currentNum + " at line " + lineCount);
                    RestartFileRead();
                    break;
                } else {
                    if (i == 0) {
                        routerOne = currentNum;
                    } else {
                        routerTwo = currentNum;
                    }
                }
            } else {
                if (currentNum < 1) {
                    System.out.println("Invalid cost value at line " + lineCount);
                    manualEntryCounter++;
                    break;
                } else {
                    cost = currentNum;
                }
            }
        }
        if (manualEntryCounter == 0) {
            AddNumsToMatrix(routerOne, routerTwo, cost);
        }
    }

    public void AddNumsToMatrix(int r1, int r2, int cost) {
        costMatrix[r1][r2] = cost;
        costMatrix[r2][r1] = cost;
    }

    public int getNumOfRouters() {
        return numOfRouters;
    }

    public int[][] getCostMatrix() {
        return costMatrix;
    }
}
