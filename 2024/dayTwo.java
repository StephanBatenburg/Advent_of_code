import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
            //boolean array: decreasing, increasing, noissues
            boolean[] checks = {true, true, true};
            int previousLevel = 0;

            int falseIndex = -1;

            for(int i = 0; i < levels.length; i++){
                if(i == 0){
                }else if(levels[i-1] == levels[i]){
                    falseIndex = i;
                    checks[2] = false;
                    break;
                }else if((levels[i-1] - levels[i]) >= 4){
                    if(checks[0] && checks[1]){
                        if((i + 1) == levels.length){
                            falseIndex = i;
                        }else{
                            if(levels[i] < levels[i + 1]){
                                falseIndex = i;
                            }else if(levels[i] > levels[i + 1]){
                                falseIndex = i - 1;
                            }else{
                                falseIndex = i;
                            }
                        }
                    }else if(checks[0]){
                        falseIndex = i;
                    }else if(checks[1]){
                        falseIndex = i - 1;  
                    }
                    checks[2] = false;
                    break;
                }else if((levels[i]-levels[i-1]) >= 4){
                    if(checks[0] && checks[1]){
                        if((i + 1) == levels.length){
                            falseIndex = i;
                        }else{
                            if(levels[i] < levels[i+1]){
                                falseIndex = i - 1;
                            }else if(levels[i] > levels[i+1]){
                                if((levels[i] - levels[i+1]) >= 4){
                                    falseIndex = i;
                                }else{
                                    falseIndex = i - 1;
                                }
                            }else{
                                falseIndex = i;
                            }
                        }
                        checks[2] = false;
                        break;
                    }else{
                        falseIndex = i;
                        checks[2] = false;
                        break;
                    }
                }else if((levels[i-1] > levels[i]) && (levels[i-1] != 0)){
                    if(checks[1] == false){
                        falseIndex = i;
                        break;
                    }
                    checks[0] = false;
                }else if(levels[i-1] < levels[i] && (levels[i-1] != 0)){
                    if(checks[0] == false){
                        falseIndex = i - 1;
                        break;
                    }
                    checks[1] = false;
                }
            }

            if((checks[0] || checks[1]) && checks[2]){
                safeLevels++;
                System.out.println("Passed level: " + Arrays.toString(levels));
            }else{
                System.out.print("False index: " + falseIndex + "   ");
                //if the level is deemed unsafe, then remove the corrupt index and see if it's fixed:
                int newLevelsList[] = new int[levels.length - 1];
                int newLevelsListCounter = 0;
                for(int i = 0; i < levels.length; i++){
                    if(i != falseIndex){
                        newLevelsList[newLevelsListCounter] = levels[i];
                        newLevelsListCounter++;
                    }
                }

                System.out.print("Before: " + Arrays.toString(levels)+ "   ");
                System.out.print("To test again: " + Arrays.toString(newLevelsList));

                boolean allDecreasing = true;
                boolean allIncreasing = true;
                previousLevel = 0;
                for(int level : newLevelsList){
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
                    System.out.println("    PASS!");
                }else{
                    System.out.println("    FAIL!");
                }
            }
        }

        System.out.println("Second part answer: " + safeLevels);

    }
}