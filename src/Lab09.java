import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class Lab09 {
    public static void main(String[] args) throws Exception {
        int findSum = sumVal("testFile7.txt");
        System.out.println(findSum);
    }

    public static boolean isOperator(String op){
        if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%")){
            return true;
        }
        return false;
    }

    public static int sumVal(String filename) throws IOException{
        Scanner scanF = null;

        try {
            FileReader fReader = new FileReader(filename);
            scanF = new Scanner(fReader);
            List<String> readList = new ArrayList<String>();
            Stack<Integer> sumStack = new Stack<Integer>();

            while (scanF.hasNext()) {
                String currT = scanF.next();
                readList.add(currT);

                if(!isOperator(currT)){
                    sumStack.add(Integer.parseInt(currT));
                }

                if(readList.size() == 3){
                    if(isOperator(readList.get(0)) || isOperator(readList.get(2)) || !isOperator(readList.get(1))){
                        System.out.println("Input Error");
                        return -1;
                    }
                    if(isOperator(readList.get(1))){
                        if(readList.get(1).equals("+")){
                            sumStack.set(0, sumStack.get(0)+sumStack.get(1));
                            sumStack.pop();
                            readList.remove(2);
                            readList.remove(1);
                        }
                        else if(readList.get(1).equals("-")){
                            sumStack.set(0, sumStack.get(0)-sumStack.get(1));
                            sumStack.pop();
                            readList.remove(2);
                            readList.remove(1);
                        }
                        else if(readList.get(1).equals("*")){
                            sumStack.set(0, sumStack.get(0)*sumStack.get(1));
                            sumStack.pop();
                            readList.remove(2);
                            readList.remove(1);
                        }
                        else if(readList.get(1).equals("/")){
                            sumStack.set(0, sumStack.get(0)/sumStack.get(1));
                            sumStack.pop();
                            readList.remove(2);
                            readList.remove(1);
                        }
                        else if(readList.get(1).equals("%")){
                            sumStack.set(0, sumStack.get(0)%sumStack.get(1));
                            sumStack.pop();
                            readList.remove(2);
                            readList.remove(1);
                        }
                        
                    }
                }
            }
            return sumStack.pop();
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("File Not Found");
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (NoSuchElementException e){
            System.out.println("Empty file");
            throw e;
        }finally {
            scanF.close();
        }
        throw new IllegalArgumentException("Failed to find max");
    }
}
