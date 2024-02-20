import java.util.Random;

public class Game {

    //print the 4*4 game board, take a 2D array as parameter
    public void printBoard (int[][] array) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //if an element is 0, it means this grid is empty, then we put a "*" in it
                if (array[i][j] == 0) {
                    System.out.print("*       ");
                } else {
                    //get the number of digit of the number in this grid
                    int numDigits = String.valueOf(array[i][j]).length();
                    //number of spaces follow the number decreases as the number of digit increases
                    //this way, thr game broad will be organized
                    //since game ends when 2048 occurs, the maximum digit is four
                    if (numDigits == 1) {
                        System.out.print(array[i][j] + "       ");
                    } else if (numDigits == 2) {
                        System.out.print(array[i][j] + "      ");
                    } else if (numDigits == 3) {
                        System.out.print(array[i][j] + "     ");
                    } else {
                        System.out.print(array[i][j] + "    ");
                    }
                }
            }
            System.out.println();
        }

    }

    //refresh board by printing 15 empty lines, no parameter is needed
    public void refreshBoard () {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }


    //generate 2 or 4 on random empty position
    public void randomNumGenerator (int[][] array) {
        //generate 2 or 4 with 80% and 20% chance respectively
        Random random = new Random();
        int probability = random.nextInt(10) + 1;
        int newNum;
        int index;
        int occupied;
        String[] emptySpace;
        int randomIndex;
        int row;
        int column;

        if (probability <= 8) {
            newNum = 2;
        } else {
            newNum = 4;
        }

        //establish a String array with 16 elements in order to store the position of each empty grid
        emptySpace = new String[16];
        index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //if a grid is empty, store the row number and column number in the array
                if (array[i][j] == 0) {
                    emptySpace[index++] = Integer.toString(i) + Integer.toString(j);
                }
            }
        }

        //keep track of how many spaces in the String array are actually used
        //In other words, how many cells are empty which can be the option of inputting new number
        occupied = 0;
        for (int i = 0; i < 16; i++) {
            if (emptySpace[i] != null) {
                occupied++;
            }
        }

        //choose a random element from the String array and take the row and column number
        randomIndex = random.nextInt(occupied);
        String cell = emptySpace[randomIndex];
        row = Integer.parseInt(cell.substring(0,1));
        column = Integer.parseInt(cell.substring(1));

        //assign new number to the chosen cell
        array[row][column] = newNum;

    }

    //preform the game board when a new game starts
    public void initializeBoard (int[][] array) {
        //insert two numbers and print the board
        randomNumGenerator(array);
        randomNumGenerator(array);
        printBoard(array);
    }

    //returns a boolean which check if the user inputs a meaningful command
    public boolean commandValid (String command) {
        //only directions (a, s, d, w), quit (q), and restart (r) are considered meaningful
        boolean isValid = command.equals("a") || command.equals("s") || command.equals("d")
                || command.equals("w") || command.equals("q") || command.equals("r");
        return isValid;
    }

    //check if two adjacent numbers are identical and thus can be added when a left direction is inputted
    public void leftCollide (int[][] array) {
        //going from row to row
        for (int i = 0; i < 4; i++) {
            //from left to right, so that the calculation order will be correct
            for (int j = 0; j < 3; j++) {
                //if two numbers can be added, the sum will be displayed on the left side grid
                if (array[i][j] == array[i][j+1]) {
                    array[i][j] += array[i][j];
                    //and the right side grid becomes zero
                    array[i][j+1] = 0;
                }
            }
        }
    }

    //the idea is same as leftCollide(), but the calculation order is different
    public void rightCollide (int[][] array) {
        for (int i = 0; i < 4; i++) {
            //going from right to left
            for (int j = 3; j > 0; j--) {
                if (array[i][j] == array[i][j-1]) {
                    array[i][j] += array[i][j];
                    array[i][j-1] = 0;
                }
            }
        }
    }

    //the idea is same as leftCollide(), but the calculation order is different
    public void upCollide (int[][] array) {
        //going from column to column
        for (int j = 0; j < 4; j++) {
            //from top to bottom
            for (int i = 0; i < 3; i++) {
                if (array[i][j] == array[i+1][j]) {
                    array[i][j] += array[i][j];
                    array[i+1][j] = 0;
                }
            }
        }
    }

    //the idea is same as leftCollide(), but the calculation order is different
    public void downCollide (int[][] array) {
        for (int j = 0; j < 4; j++) {
            //going from bottom to top
            for (int i = 3; i > 0; i--) {
                if (array[i][j] == array[i-1][j]) {
                    array[i][j] += array[i][j];
                    array[i-1][j] = 0;
                }
            }
        }
    }

    //slide all numbers to the left of there is any empty spaces on the left
    public void leftSlide (int[][] array) {
        int count;
        for (int i = 0; i < 4; i++) {
            count = 0;
            for (int j = 0; j < 4; j++) {
                //for any non-zero numbers, assign them to the head of the row
                if (array[i][j] != 0) {
                    array[i][count++] = array[i][j];
                }
            }
            //then assign the remaining grids to zero
            while (count < 4) {
                array[i][count++] = 0;
            }
        }
    }

    //the idea is same as leftSlide(), but the moving direction is different
    public void rightSlide (int[][] array) {
        int count;
        for (int i = 0; i < 4; i++) {
            count = 3;
            for (int j = 3; j >= 0; j--) {
                if (array[i][j] != 0) {
                    array[i][count--] = array[i][j];
                }
            }
            while (count >= 0) {
                array[i][count--] = 0;
            }
        }
    }

    //the idea is same as leftSlide(), but the moving direction is different
    public void downSlide (int[][] array) {
        int count;
        for (int j = 0; j < 4; j++) {
            count = 3;
            for (int i = 3; i >= 0; i--) {
                if (array[i][j] != 0) {
                    array[count--][j] = array[i][j];
                }
            }
            while (count >= 0) {
                array[count--][j] = 0;
            }
        }
    }

    //the idea is same as leftSlide(), but the moving direction is different
    public void upSlide (int[][] array) {
        int count;
        for (int j = 0; j < 4; j++) {
            count = 0;
            for (int i = 0; i < 4; i++) {
                if (array[i][j] != 0) {
                    array[count++][j] = array[i][j];
                }
            }
            while (count < 4) {
                array[count++][j] = 0;
            }
        }
    }

    //win meaning the player obtains a 2048; check if any number equals to 2048
    public boolean ifWin (int[][] array) {
        boolean ifWin = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] == 2048) {
                    ifWin = true;
                    break;
                }
            }
        }
        return ifWin;
    }

    //check if any two identical numbers are adjacent, which means there could be more move
    public boolean moveAvailable (int[][] array) {
        boolean moveAvailable = false;
        //check if any identical numbers are adjacent horizontally
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (array[i][j] == array[i][j-1]) {
                    moveAvailable = true;
                    break;
                }
            }
        }
        //check if any identical numbers are adjacent vertically
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if (array[i][j] == array[i-1][j]) {
                    moveAvailable = true;
                    break;
                }
            }
        }
        return moveAvailable;
    }

    //check if all grids on the game broad are occupied
    public boolean spaceFull (int[][] array) {
        boolean spaceFull = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //if any grid has 0, then there is more space
                if (array[i][j] == 0) {
                    spaceFull = false;
                    break;
                }
            }
        }
        return spaceFull;
    }

    //determine if the game ends
    public boolean ifEnd (int[][] array) {
        boolean ifEnd = false;
        //game ends if player wins (obtain 2048)
        if (ifWin(array)) {
            ifEnd = true;
        //or game ends if all spaces are occupied and there isn't any possible move available
        } else if ((spaceFull(array)) && (! moveAvailable(array))) {
            ifEnd = true;
        }
        return ifEnd;
    }

    //copy the game board 2D array
    public void makeCopy (int[][] original, int[][] copy) {
       for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++) {
               copy[i][j] = original[i][j];
           }
       }
    }

    //compare two arrays to see if all of their elements match
    //this method comes in handy when the program needs to determine whether a move is a valid move
    //if none of the elements change or move, this is an invalid move
    public boolean makeComparison (int[][] original, int[][] copy) {
        boolean identical = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (original[i][j] != copy[i][j]) {
                    identical = false;
                    break;
                }
            }
        }
        return identical;
    }

    //find the max number in the array
    public int findMax (int[][] array) {
        int maxNum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] > maxNum) {
                    maxNum = array[i][j];
                }
            }
        }
        return maxNum;
    }

    //restart the game by remove all numbers on the board currently and start a new game (using initializeBoard())
    public void restart (int[][] array) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                array[i][j] = 0;
            }
        }
        refreshBoard();
        initializeBoard(array);
    }

}
