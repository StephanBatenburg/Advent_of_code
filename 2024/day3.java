import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {
    public static void main(String[] args) throws Exception{
        File file = new File("input/day3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<String> readData = new ArrayList<String>();

        String readline;
        while((readline = br.readLine()) != null){
            readData.add(readline);
        }

        br.close();

        ArrayList<int[]> toMultiply = new ArrayList<int[]>();

        //part 1:
        String regex = "mul[(](\\d+)[,](\\d+)[)]";
        Pattern findPatternSums = Pattern.compile(regex, Pattern.MULTILINE);
        
        for(String toCheckString : readData){
            Matcher matchesSums = findPatternSums.matcher(toCheckString);
            while(matchesSums.find()){
                int[] newArray = {0, 0};
                newArray[0] = Integer.valueOf(matchesSums.group(1));
                newArray[1] = Integer.valueOf(matchesSums.group(2));
                toMultiply.add(newArray);
            }
        }

        int total = 0;
        for(int[] multiply : toMultiply){
            total += multiply[0] * multiply[1];
        }

        System.out.println(total);

        //part two:
        String secondRegex = "mul[(](\\d+)[,](\\d+)[)]|do[(][)]|don[']t[(][)]";
        findPatternSums = Pattern.compile(secondRegex, Pattern.MULTILINE);

        toMultiply = new ArrayList<int[]>();

        boolean addToMultiply = true;

        for(String toCheckString : readData){
            Matcher matchesRegex = findPatternSums.matcher(toCheckString);
            while(matchesRegex.find()){
                switch (matchesRegex.group(0)) {
                    case "do()":
                        addToMultiply = true;
                        break;
                    case "don't()":
                        addToMultiply = false;
                        break;
                    default:
                        if(addToMultiply){
                            int[] newArray = {0, 0};
                            newArray[0] = Integer.valueOf(matchesRegex.group(1));
                            newArray[1] = Integer.valueOf(matchesRegex.group(2));
                            toMultiply.add(newArray);
                        }
                        break;
                }
            }
        }

        total = 0;
        for(int[] multiply : toMultiply){
            total += multiply[0] * multiply[1];
        }

        System.out.println(total);
    }
}
