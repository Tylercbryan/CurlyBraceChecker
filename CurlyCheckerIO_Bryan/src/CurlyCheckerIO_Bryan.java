import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class CurlyCheckerIO_Bryan {

    public static void main(String[] args) {

        //Checking SportsTeams1
        System.out.println("---First File---");
        String fileName1 = "SportsTeam1.java";
        Stack<Character> stack = new Stack();
        Character[] b = getArray(fileName1);
        int end = findLastElement(b);
        if (bChecker(b, stack, end)) {
            System.out.println(fileName1 + " is balanced!\n");
        } else {
            System.out.println(fileName1 + " is not balanced\n");
        }

        //Checking SportsTeam2
        System.out.println("---Second File---");
        String fileName2 = "SportsTeam2.java";
        b = getArray(fileName2);
        end = findLastElement(b);
        if (bChecker(b, stack, end)) {
            System.out.println(fileName2 + " is balanced!\n");
        } else {
            System.out.println(fileName2 + " is not balanced\n");
        }
    }

    //getArray class reads a java file and loads each token into a Character array.
    public static Character[] getArray(String f) {
        Character[] braces = new Character[100];
        File file = new File(f);
        int count = 0;
        int countOpen = 0;
        int countClose = 0;

        try {
            Scanner scan = new Scanner(file);
            System.out.println("File found and loaded");
            while (scan.hasNext()) {
                char[] currentToken = scan.next().toCharArray();

                //this loop keeps track of each "{" and "}" found in java file.
                //each brace found is loaded into a Character array and increments a count total.
                for (int i = 0; i < currentToken.length; ++i) {
                    if (currentToken[i] == '{') {
                        braces[count] = currentToken[i];
                        ++count;
                        ++countOpen;
                    } else if (currentToken[i] == '}') {
                        braces[count] = currentToken[i];
                        ++count;
                        ++countClose;
                    }
                }
            }

            //Print statements
            System.out.println("The number of '{' is " + countOpen);
            System.out.println("The number of '}' is " + countClose);
            //Stores non null values in new array with corrected index length then prints new array
            Character[] bracesFinal = new Character[count];
            for (int i = 0; i < bracesFinal.length; i++) {
                bracesFinal[i] = braces[i];
            }
            System.out.println("Contents of the array: " + Arrays.toString(bracesFinal));
            return braces;
            //
        } catch (FileNotFoundException var7) {
            System.out.println(file + " File not found");
            return null;
        }
    }

    //findLastElement iterates through an array until it reaches null items.
    //then returns the index of the last non null item.
    public static int findLastElement(Character[] arr) {
        int i = 0;
        while (arr[i] != null) {
            i++;
        }
        return i;
    }

    //bChecker uses Stacks to check if the Character array is balanced
    //if a "{" is found, it pushes to the stack
    //if a "}" is found, it pops from the stack.
    //If the stack is empty at the end of iteration is declares the braces as balanced.
    public static boolean bChecker(Character[] b, Stack s, int end) {
        for (int i = 0; i < end; i++) {
            if (b[i] == '{') {
                s.push("{");
            } else if (b[i] == '}') {
                if (!s.isEmpty()) {
                    s.pop();
                } else {
                    System.out.println("Unbalanced braces. closing brace found with no corresponding opening brace.");
                    System.exit(0);
                }
            }
        }
        if (s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


}
