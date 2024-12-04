import java.io.*;
import java.util.ArrayList;

class dayTwo{
    public static void main(String[] args) throws Exception{
        
        File file = new File("input/dayTwo.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<int[]> levelslList = new ArrayList<int[]>();

        String readLine;
        while((readLine = br.readLine()) != null){
            String[] seperateStrings = readLine.split(" ");
            int[] numbers = new int[seperateStrings.length];
            for(int i = 0; i < seperateStrings.length; i++){
                numbers[i] = Integer.valueOf(seperateStrings[i]);
            }
            levelslList.add(numbers);
        }


        br.close();

        //first part:
        int safeLevels = 0;
        for(int[] levels : levelslList){
            boolean allDecreasing = true;
            boolean allIncreasing = true;
            int previousLevel = 0;
            for(int level : levels){
                if(previousLevel == 0){
                    previousLevel = level;
                }else if(previousLevel == level){
                    allDecreasing = false;
                    allIncreasing = false;
                }else if(((previousLevel - level) >= 4) || ((level-previousLevel) >= 4)){
                    allDecreasing = false;
                    allIncreasing = false;
                }else if(previousLevel > level){
                    allIncreasing = false;
                }else if(previousLevel < level){
                    allDecreasing = false;
                }
                previousLevel = level;
            }
            if(allDecreasing || allIncreasing){
                safeLevels++;
            }
        }

        System.out.println("First part answer: " + safeLevels);

        //second part:
        safeLevels = 0;
        for(int[] levels : levelslList){
            boolean allDecreasing = true;
            boolean allIncreasing = true;
            int previousLevel = 0;
            for(int level : levels){
                if(previousLevel == 0){
                    previousLevel = level;
                }else if(previousLevel == level){
                    allDecreasing = false;
                    allIncreasing = false;
                }else if(((previousLevel - level) >= 4) || ((level-previousLevel) >= 4)){
                    allDecreasing = false;
                    allIncreasing = false;
                }else if(previousLevel > level){
                    allIncreasing = false;
                }else if(previousLevel < level){
                    allDecreasing = false;
                }
                previousLevel = level;
            }
            if(allDecreasing || allIncreasing){
                safeLevels++;
            }else{
                ArrayList<int[]> adjustedArrays = new ArrayList<int[]>();
                for(int i = 0; i<levels.length; i++){
                    int[] toAddArray = new int[levels.length - 1];
                    int Arraycounter = 0;
                    for(int j = 0; j < levels.length; j++){
                        if(j != i){
                            toAddArray[Arraycounter] = levels[j];
                            Arraycounter++;
                        }
                    }
                    adjustedArrays.add(toAddArray);
                }

                for(int[] adjustedLevels : adjustedArrays){
                    allDecreasing = true;
                    allIncreasing = true;
                    previousLevel = 0;
                    for(int level : adjustedLevels){
                        if(previousLevel == 0){
                            previousLevel = level;
                        }else if(previousLevel == level){
                            allDecreasing = false;
                            allIncreasing = false;
                        }else if(((previousLevel - level) >= 4) || ((level-previousLevel) >= 4)){
                            allDecreasing = false;
                            allIncreasing = false;
                        }else if(previousLevel > level){
                            allIncreasing = false;
                        }else if(previousLevel < level){
                            allDecreasing = false;
                        }
                        previousLevel = level;
                    }
                    if(allDecreasing || allIncreasing){
                        safeLevels++;
                        break;
                    }
                }
            }
        }

        System.out.println("Second part answer: " + safeLevels);

    }
}