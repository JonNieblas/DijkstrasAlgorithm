import java.io.*;
import java.util.HashMap;

public class NodeFactory {

    BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
    private int numOfRouters;
    private int[] numsInLine;
    private int lineCount;
    private int manualEntryCounter = 0;
    private String fileName = "topo.txt";
    private HashMap<String, Integer> costMap = new HashMap<>();

    public NodeFactory(int numFromUser){
        this.numOfRouters = numFromUser;
    }

    public void ExtractNumsInLine(String line){
        int counter = 0;
        String[] numberSeparator = line.split("\\t");
        numsInLine = new int[numberSeparator.length];
        for (String i : numberSeparator) {
            numsInLine[counter] = Integer.parseInt(i);
            counter++;}
        if(counter < 3 || counter > 3){
            System.out.println("Incorrect number of values at line " + lineCount);
            manualEntryCounter++; }
    }

    public void ValidateTopoTxt() throws IOException{
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null){
                if(manualEntryCounter > 0){
                    fileReader.close();
                    break;
                }
                lineCount++;
                ExtractNumsInLine(currentLine);
                ValidateRouterNums();
            }
            if(manualEntryCounter > 0){
                RestartFileRead();
            }
            fileReader.close();//for now, will be elsewhere later
        } catch (FileNotFoundException ex){
            System.out.println(fileName + " does not exist.");
            RestartFileRead();
        }
    }

    public void RestartFileRead() throws IOException{
        System.out.print("Please input the name of the cost input file: ");
        fileName = sysIn.readLine();
        lineCount = 0;
        manualEntryCounter = 0;
        ValidateTopoTxt();
    }

    public void ValidateRouterNums(){
        int routerOne = 0;
        int routerTwo = 0;
        int cost = 0;
        for(int i = 0; i < 3; i++){
            int currentNum = numsInLine[i];
            if(i < 2) {
                if ((currentNum > numOfRouters - 1) || (currentNum < 0)) {
                    System.out.println("Invalid router value " + currentNum + " at line " + lineCount);
                    manualEntryCounter++;
                    break;
                } else{
                    if(i == 0){
                        routerOne = currentNum;
                    } else{
                        routerTwo = currentNum;
                    }
                }
            } else{
                if (currentNum < 1){
                    System.out.println("Invalid cost value at line " + lineCount);
                    manualEntryCounter++;
                    break;
                } else{
                    cost = currentNum;
                }
            }
        }
        if(manualEntryCounter == 0) {
            AddNumsToMatrix(routerOne, routerTwo, cost);
        }
    }

    public void AddNumsToMatrix(int r1, int r2, int cost){
        costMap.put(r1 + "-" + r2, cost);
    }

    public int getNumOfRouters() {
        return numOfRouters;
    }

    public HashMap<String, Integer> getCostMap() {
        return costMap;
    }
}