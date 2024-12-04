import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class day4 {
    public static void main(String[] args) throws Exception{
        File file = new File("input/day4.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<String> readData = new ArrayList<String>();
        String readLine;
        while((readLine = br.readLine()) != null){
            readData.add(readLine);
        }

        br.close();

        //can check top, bottom, left, right
        boolean[] canCheck = {false, false, false, false};

        int xmasCounter = 0;

        for(int i = 0; i < readData.size(); i++){
            for(int j = 0; j < readData.get(i).length(); j++){
                //check top
                canCheck[0] = (i < 3) ? false : true;
                //check bottom
                canCheck[1] = (i + 4 > readData.size()) ? false : true;
                //check left
                canCheck[2] = (j < 3) ? false : true;
                //check right
                canCheck[3] = (j + 4 > readData.get(i).length()) ? false : true;

                String check = "XMAS";
                //to the right
                if(canCheck[3]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i).substring(j+k,j+k+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];
                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }

                //to the left
                if(canCheck[2]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i).substring(j-k,j-k+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];
                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }

                //upwards
                if(canCheck[0]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i-k).substring(j,j+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];
                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }

                //downwards
                if(canCheck[1]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i+k).substring(j,j+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];
                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }

                //diagnal up left
                if(canCheck[0] && canCheck[2]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i-k).substring(j-k,j-k+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];
                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }

                //diagnal up right
                if(canCheck[0] && canCheck[3]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i-k).substring(j+k,j+k+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];
                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }

                //diagnal down left
                if(canCheck[1] && canCheck[2]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i+k).substring(j-k,j-k+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];
                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }

                //diagnal down right
                if(canCheck[1] && canCheck[3]){
                    String[] makeReadable = new String[4];
                    for(int k = 0; k < 4; k++){
                        makeReadable[k] = readData.get(i+k).substring(j+k,j+k+1);
                    }
                    String toCheck = makeReadable[0] + makeReadable[1] + makeReadable[2] + makeReadable[3];

                    if(check.equals(toCheck)){
                        xmasCounter++;
                    }
                }
            }
        }

        System.out.println("Answer part one: " + xmasCounter);
    }
}
