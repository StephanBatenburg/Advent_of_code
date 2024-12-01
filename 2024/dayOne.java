import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class dayOne{
    public static void main(String[] args) throws Exception{
        ArrayList<Integer> leftNumbers = new ArrayList<Integer>();
        ArrayList<Integer> rightNumbers = new ArrayList<Integer>();

        try{
            File file = new File("input/dayOne.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String readLine;
            while((readLine = br.readLine()) != null){
                String[] seperateStrings = readLine.split("   ");
                leftNumbers.add(Integer.valueOf(seperateStrings[0]));
                rightNumbers.add(Integer.valueOf(seperateStrings[1]));
            }

            br.close();
        }finally{
            if(((leftNumbers.size() == 0) || (rightNumbers.size() == 0)) || (leftNumbers.size() != rightNumbers.size())){
                System.out.println("sides are inequal or empty!");
                System.exit(1);
            }
        }

        Collections.sort(leftNumbers);
        Collections.sort(rightNumbers);

        //part one:
        int totalDifference = 0;

        for(int i = 0; i < leftNumbers.size(); i++){
            int leftNumber = leftNumbers.get(i);
            int rightNumber = rightNumbers.get(i);
            if(leftNumber < rightNumber){
                totalDifference += (rightNumber - leftNumber);
            }else{
                totalDifference += (leftNumber - rightNumber);
            }
        }

        System.out.println("First part answer is: " + totalDifference);

        //part two:
        int similarityScore = 0;
        for(int i = 0; i < leftNumbers.size(); i++){

            int leftNumber = leftNumbers.get(i);
            int similarAmount = 0;
            if (rightNumbers.contains(leftNumber)){
                for(int j = 0; j < rightNumbers.size(); j++){
                    int rightNumber = rightNumbers.get(j);
                    if (leftNumber == rightNumber){
                        similarAmount++;
                    }
                }
            }

            similarityScore += (similarAmount * leftNumber);
        }

        System.out.println("Second part answer is: " + similarityScore);
    }
}