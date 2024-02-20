import java.util.Scanner;

public class Main extends Game {

    public static void main(String[] args) {
        //establish all needed variables
        Game test = new Game();
        Scanner scanner = new Scanner(System.in);
        int[][] game = {{0, 0, 0, 0},{0, 0, 0, 0}, {0, 0, 0, 0},{0, 0, 0, 0}};
        int[][] compare = {{0, 0, 0, 0},{0, 0, 0, 0}, {0, 0, 0, 0},{0, 0, 0, 0}};
        int validMove = 0;
        String userInput;
        String confirmation;

        //print out instructions and the initial game board
        System.out.println("Welcome to 2048!");
        System.out.println("Enter 'a' to go left, 'd' to go right, 's' to go down, and 'w' to go up.");
        System.out.println("Enter 'q' to quit, and 'r' to restart.");
        System.out.println();
        test.initializeBoard(game);

        //while(true) loop with the break conditions inside (quit or game ends)
        while (true) {
            userInput = scanner.nextLine();
            //check if the input is valid, if not, ask the player to enter input again
            if (test.commandValid(userInput)) {
                //if the input is a direction, enter the switch branch with four directions
                if ((userInput.equals("a")) || (userInput.equals("s")) ||
                        (userInput.equals("d")) || (userInput.equals("w"))) {
                    //before make any changes to the current game board, make a copy of it
                    test.makeCopy(game, compare);
                    switch (userInput) {
                        //"a" meaning go left
                        case "a" -> {
                            //slide to the left to eliminate any gaps between number tiles
                            test.leftSlide(game);
                            //combine the identical numbers that are adjacent
                            test.leftCollide(game);
                            //slide again to eliminate the possible gap produced by the combination
                            test.leftSlide(game);
                        }
                        //"d" meaning go right
                        case "d" -> {
                            //the idea is same as case "a", only the direction is different
                            test.rightSlide(game);
                            test.rightCollide(game);
                            test.rightSlide(game);
                        }
                        //"w" meaning go up
                        case "w" -> {
                            //the idea is same as case "a", only the direction is different
                            test.upSlide(game);
                            test.upCollide(game);
                            test.upSlide(game);
                        }
                        //"s" meaning go down
                        case "s" -> {
                            //the idea is same as case "a", only the direction is different
                            test.downSlide(game);
                            test.downCollide(game);
                            test.downSlide(game);
                        }
                    }
                    //after any move, refresh the board
                    test.refreshBoard();
                    //compare the board after move with the copied board before move
                    //if they are identical, this is not a valid move
                    if (! test.makeComparison(game, compare)) {
                        //if valid, add a new number to the board, counter of valid move plus one, print the moved direction
                        test.randomNumGenerator(game);
                        validMove++;
                        System.out.println("Valid move: " + userInput);
                    } else {
                        //if invalid, print invalid and the moved direction
                        System.out.println("Invalid move: " + userInput);
                    }
                    //print the board after move and the info (max number and # of valid move) with it
                    test.printBoard(game);
                    System.out.println("Maximum number: " + test.findMax(game) + "; number of valid move: " + validMove);
                } else  if (userInput.equals("q")) {
                    //if the input is "q" (quit), confirm the request
                    System.out.println("Are you sure you want to quit the game? (Enter yes or no)");
                    confirmation = scanner.nextLine();
                    if (confirmation.equals("yes")) {
                        //if user inputs "yes", game ends and print the info
                        System.out.println("GAME ENDS!");
                        System.out.println("Maximum number: " + test.findMax(game) + "; number of valid move: " + validMove);
                        //break from the while loop
                        break;
                    } else {
                        //if the response is not "yes", the game continues
                        System.out.println("Please enter your next move.");
                        continue;
                    }
                } else {
                    //if the input is "r" (restart), confirm the request
                    System.out.println("Are you sure you want to restart the game? (Enter yes or no)");
                    confirmation = scanner.nextLine();
                    if (confirmation.equals("yes")) {
                        //if the user inputs"yes", the game restarts
                        test.restart(game);
                    } else {
                        //if the response is not "yes", the game continues
                        System.out.println("Please enter your next move.");
                        continue;
                    }
                }

            } else {
                //if not a valid input, ask the player to enter again
                System.out.println("Invalid input. Please enter again.");
            }

            //check if the game ends after every move
            if (test.ifEnd(game)) {
                //if ends, prints "Game Ends" and the info
                System.out.println();
                System.out.println("GAME ENDS!");
                System.out.println("Maximum number: " + test.findMax(game) + "; number of valid move: " + validMove);
                //break away from the while loop
                break;
            }
        }

    }
}
