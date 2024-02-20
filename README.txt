# text-based-2048

The project is aimed to achieve a text based 2048, which all functions and characteristics are identical. Referring to
http://2048game.com/.

This project contains two classes: Game.java as an abstract class and Main.java as main class. All necessary methods were implemented in the Game abstract class and the Main class extends from Game.java.


Game.java
This abstraction class contains 21 methods which all applied to other methods or the main class(or both). The ideas of
all methods are commented along the code, so here I would make a overall description of them. The methods can be briefly
separated into three categories: performance, functions, and determination. Performance methods print the parts that
user needs to see, such as game board and empty lines. Function methods is the core of the game, which enable the grids
to move and combine according to the direction. Determination methods allow the program to accommodate to varies situations.
Performance methods: printBoard(), refreshBoard()
Function methods: randomNumGenerator(), initializeBoard(), leftCollide(), rightCollide(), upCollide(), downCollide(),
                    leftSlide(), rightSlide(), upSlide(), downSlide(), makeCopy()
Determination methods: commandValid(), ifWin(), moveAvailable(), spaceFull(), ifEnd(), makeComparison()


Main.java
First of all, all variables are established at the beginning and the instructions are printed. And the initial game
board with two numbers in the random positions is printed. Then the program enters an infinite while loop which only
game finishes or user quit can ends. The scanner takes user input and then an if statement determines whether the input
is valid. If it's invalid, the program ask the user to enter again. If it's valid, the program continues to determine
whether it is a directional command. If yes, then the switch branch of according direction is executed. If not, the
quit or restart branch is executed according to the input. For valid directional command, the tiles are slided to the
desired direction in order to eliminate any gap between existing numbers. Then all adjacent numbers that are equal are
combined. At the end, the tiles are slided to the same direction again to get rid of the gaps produced by combination.
At the end of each loop, the program checks if the game ends (either 2048 occurs or there is no more possible move). If
it ends, the program breaks away from the while loop and print out the final max number and number of valid move.
