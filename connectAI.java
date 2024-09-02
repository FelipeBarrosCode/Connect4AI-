import java.util.InputMismatchException;
import java.util.Scanner;

//javac -source 8 -target 8 connectAI.java
//java connectAI
/*
 * Barros Moura, Felipe
 * CPSC 1150
 * Langara College
 * 
 * 
 * This is a connect 4 game
 * Use a 2d array to play the game with an AI
 * 
 * Instructor: Ryan Zier-Vogel
 * 
 * Again Compiler Broke
 * 

 * 
 */



public class connectAI {
    // Main method
    public static void main(String[] args) {
        // String array for the connect 4 table

        System.out.println((byte)(134));
        String[][] table = new String[7][7];
        // Starting player Letter
        String letter = "R";
        // Array used to register the most recent play in order to check if player won
        // This will be used later trust me
        int[] holdvalues;

        // Create the array for that we will use as the connect 4 table
        createArray(table);
        // Print the connect 4 table

        do {

            // This will change what player is currently playing
            if (letter.equals("R")) {
                letter = "Y";
            } else {
                letter = "R";
            }

            if (letter.equals("Y")) {

                holdvalues = applyAI(table);
            } else {
                printConnect(table);
                holdvalues = plays(letter, table);
            }

            // This will be the actual playing process

            // This loop will happen until we reach the win -- More details below
        } while (checkWin(letter, holdvalues[1], holdvalues[0], table));
        // Win message
        printConnect(table);
        print("The player UHUUL " + letter + " Won");

    }

    // This method is used to check if a player won the game by contoling the loop
    // in the main method
    // Input:charCheck: String character that we are going to check for; int
    // column/row position of the last play; String[][] of our connect 4 table
    // Output: boolean if the player of the most recent play won or lost

    public static boolean checkWin(String charCheck, int column, int row, String[][] connect4) {

        if (checkWinHorizontal(charCheck, column, row, connect4) || checkWinVertical(charCheck, column, row, connect4)
                || checkWinDiagonalRight(charCheck, column, row, connect4)
                || checkWinDiagonalLeft(charCheck, column, row, connect4)) {
            return false;
        } else {
            return true;
        }

    }

    // check for 4 consecutive letters on the same row
    // Input:charCheck String character that we are going to check for; int
    // column/row position of the last play; String[][] of our connect 4 table
    // Output: boolean in case the row has 4 consecutive letters Y or R
    public static boolean checkWinHorizontal(String charCheck, int column, int row, String[][] connect4) {

        // Keep track of the consecutive letters
        int amount = 0;

        // Check the value on the right
        for (int i = column; i < connect4[row].length; i++) {
            if (connect4[row][i].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }
        // Check values on the left
        for (int i = column - 1; i >= 0; i--) {
            if (connect4[row][i].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }
        // In case there are 4 consecutive values it will return true
        if (amount >= 4) {
            return true;
        } else {
            return false;
        }

    }
    // check for 4 consecutive letters on the same column
    // Input:charCheck String character that we are going to check for; int
    // column/row position of the last play; String[][] of our connect 4 table
    // Output: boolean in case the row has 4 consecutive letters Y or R

    public static boolean checkWinVertical(String charCheck, int column, int row, String[][] connect4) {

        // Keep track of the consecutive letters
        int amount = 0;
        // Check for letters in the same column in rows bellow
        for (int i = row; i < connect4.length; i++) {
            if (connect4[i][column].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }
        // Check for letters in the same column in rows upwards
        for (int i = row - 1; i >= 0; i--) {
            if (connect4[i][column].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }
        // In case there are 4 consecutive values it will return true
        if (amount >= 4) {
            return true;
        } else {
            return false;
        }

    }
    // check for 4 consecutive letters on the same diagonal going from left to right
    // Input:charCheck String character that we are going to check for; int
    // column/row position of the last play; String[][] of our connect 4 table
    // Output: boolean in case the row has 4 consecutive letters Y or R

    public static boolean checkWinDiagonalRight(String charCheck, int column, int row, String[][] connect4) {
        // Keep track of consecutive letters
        int amount = 0;

        // Check the diagonal going upwards to the right
        for (int i = row, j = column; i >= 0 && j < connect4.length; i--, j++) {
            if (connect4[i][j].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }
        // Check diagonal going dowards to the left
        for (int i = row, j = column; i < connect4.length && j >= 0; i++, j--) {

            if (connect4[i][j].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }

        // Check in case there are 4 consecutive letters -- the value here is different
        // but works
        if (amount >= 5) {
            return true;
        } else {
            return false;
        }

    }

    // check for 4 consecutive letters on the same diagonal going from right to left
    // Input:charCheck String character that we are going to check for; int
    // column/row position of the last play; String[][] of our connect 4 table
    // Output: boolean in case the diagonal has 4 consecutive letters Y or R
    public static boolean checkWinDiagonalLeft(String charCheck, int column, int row, String[][] connect4) {
        // Keep track of consecutive letters
        int amount = 0;

        // Check diagonal going downwards to the left
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {

            if (connect4[i][j].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }
        // Check diagonal upwards to the right
        for (int i = row, j = column; i < connect4.length && j < connect4.length; i++, j++) {

            if (connect4[i][j].equals("| " + charCheck + " |")) {
                amount++;
            } else {
                break;
            }
        }

        // Same situation as previously check for consecutive letters on the diagonal
        if (amount >= 5) {
            return true;
        } else {
            return false;
        }

    }

    // Make possible the player to play each time
    // input: letter - letter of the player that is playing at that given time ;
    // array - our connect 4 table
    // output: Array with precise coordinates of the position from current play

    public static int[] plays(String letter, String[][] array) {

        // Scanner for input
        Scanner sc = new Scanner(System.in);
        // Input variable
        int holdVariable;
        // Variable that will hold the column number that we want to place our letter -
        // this means that when letter reach last place before the numbers we will not
        // allow the letter to be placed where the numbers are

        // Array with precise coordinate point from last play
        int[] coorDinates = new int[2];

        // Print info
        print("Pick a Column");
        print("Is the " + letter + " turn");

        // This is a infinite loop that will just stop when a input less than 7 and
        // greater thann 1 is given and differnt from a string
        while (true) {

            try {
                print("NEW value between 1 and 7: ");
                holdVariable = sc.nextInt();

                if (holdVariable < 1 || holdVariable > 7) {
                    print("Value not in range give ma a numb 1-7");
                    continue;
                }

                break;

            } catch (InputMismatchException e) {
                // This is an error that I found taht allows me to deal with non integer inputs

                print("Please pick a column between 1 and 7");

                sc.nextLine();

            }

        }
        ;
        // Variable that will allow us to stop before heating the row with numbers

        // This loop will place our play in the last available position
        for (int i = 0; i <= array.length - 1; i++) {

            if ((i == array.length - 1) || (array[i][holdVariable - 1].equals("| R |")) || (array[i][holdVariable - 1].equals("| Y |"))) {

                // Activate in case column is full
                if (i == 0) {

                    print("Column is full");

                    // This is a infinite loop that will just stop when a input <7 and >1 is given
                    // in a column that is not full
                    do {
                        // Try catch taht will allow just integers
                        try {
                            print("NEW value between 1 and 7: ");
                            holdVariable = sc.nextInt();
                            // Check if the number will be in between 1 and 7
                            if (holdVariable < 1 || holdVariable > 7) {
                                print("Value not in range give ma a numb 1-7");
                                continue;
                            }

                            break;

                        } catch (InputMismatchException e) {
                            print("Please pick a column between 1 and 7");

                            sc.nextLine();

                        }

                    } while (true);

                    // Comes back and try to place our play in another column
                    i = -1;
                    continue;

                }

                // place our play on the right position
                array[i - 1][holdVariable - 1] = "| " + letter + " |";
                // Give precise coordinate of our play
                coorDinates[0] = i - 1;
                coorDinates[1] = holdVariable - 1;
                break;

            }

        }

        // Print our new connect4 table

        return coorDinates;

    }

    // Make possible the player to play each time
    // input: letter - letter of the player that is playing at that given time ;
    // array - our connect 4 table
    // output: Array with precise coordinates of the position from current play

    public static int[] playAI(String letter, int position, String[][] array) {

        // Input variable
        int holdVariable = position;

        // Array with precise coordinate point from last play
        int[] coorDinates = new int[2];

        // Variable that will allow us to stop before heating the row with numbers

        // This loop will place our play in the last available position
        for (int i = 0; i <= array.length - 1; i++) {

            if ((i == array.length - 1) || (array[i][holdVariable].equals("| R |"))
                    || (array[i][holdVariable].equals("| Y |"))) {

                // Activate in case column is full
                if (i == 0) {

                    print("Column is full");

                    // This is a infinite loop that will just stop when a input <7 and >1 is given
                    // in a column that is not full
                    do {
                        

                        print("Value in full column ");
                        holdVariable = (int) (Math.random() * 7);
                        // Check if the number will be in between 1 and 7

                    } while (holdVariable < 1 || holdVariable > 7);

                    // Comes back and try to place our play in another column
                    i = -1;
                    continue;

                }

                // place our play on the right position
                array[i - 1][holdVariable] = "| " + letter + " |";
                // Give precise coordinate of our play
                coorDinates[0] = i - 1;
                coorDinates[1] = holdVariable;
                break;

            }

        }

        // Send the coordinate of our AI play
        return coorDinates;

    }

    // This method applies tha AI logic the 1 priority is win/2 is to block us from
    // winning and at last use/3 play ramdomly
    // Input: The 2d array with our connect 4 table
    // Output:The coordinates for our AI play decided and played in the original
    // array
    public static int[] applyAI(String[][] originalTable) {

        // Copy of original array variable
        // Array with our coordinate
        String copyTable[][];
        int[] positionTable = new int[2];
        // Check all possibilities of winning
        for (int counter = 0; counter < originalTable[0].length; counter++) {
            // Copy original array
            // Play test
            copyTable = copyArray(originalTable);
            positionTable = playAI("Y", counter, copyTable);
            // Check play test for win of Y
            if (!(checkWin("Y", positionTable[1], positionTable[0], copyTable))) {
                //Shows what was the play type
                print("Go for win");
                return playAI("Y", counter, originalTable);

            }

        }

        // Check all possibilities of blocking
        for (int counter = 0; counter < originalTable[0].length; counter++) {

            // Copy original array
            // Play test
            copyTable = copyArray(originalTable);
            positionTable = playAI("R", counter, copyTable);
            // Check play test for win of R
            if (!(checkWin("R", positionTable[1], positionTable[0], copyTable))) {
                //Shows what was the play type
                print("Block you");
                return playAI("Y", counter, originalTable);

            }

        }
        // Plays Random
        //Shows what was the play type
        print("random");
        return playAI("Y", (int) (Math.random() * 7), originalTable);

    }

    // This is a ,methdo that copies the original array so that we can use to test
    // the AI play possibilities
    // Input: 2d array with our connect 4 table
    // Output:A copy of the original array
    public static String[][] copyArray(String[][] arrayToCopy) {

        String[][] finalArray = new String[arrayToCopy.length][arrayToCopy[0].length];
        for (int i = 0; i < arrayToCopy.length; i++) {
            for (int j = 0; j < arrayToCopy[i].length; j++) {
                finalArray[i][j] = arrayToCopy[i][j];
            }
        }
        return finalArray;
    }

    // Shortcut to print my String
    // input my String to print
    // output none

    public static void print(String str) {
        System.out.println(str);
    }

    // Method to print the connect 4 table
    // input: the 2d array that will be our connect 4 table - pass by reference
    // output NONE

    public static void printConnect(String[][] arrayOfplaces) {

        for (int i = 0; i < arrayOfplaces.length; i++) {

            for (int j = 0; j < arrayOfplaces[i].length; j++) {
                System.out.print(arrayOfplaces[i][j]);
            }
            System.out.print("\n");

        }
    }

    // Method used to create the array with the values that we want to use inside
    // the 2 d array
    // input: the 2d array that we are going to use- pass by reference
    // output NONE
    public static void createArray(String[][] arr) {

        for (int i = 0; i < arr.length; i++) {

            if (i < arr.length - 1) {
                //Empty spaces
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = "| - |";
                }

            } else {
                //Numbers at the bottom of the table
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = "| " + (j + 1) + " |";
                }

            }

        }

    }
}