import java.util.Random;

public class test extends Game {
    public static void main(String[] args) {
        Game test = new Game();
        int[][] game = {{2, 0, 2, 0},{0, 2, 0, 0}, {0, 2, 0, 0},{0, 2, 0, 0}};
        Random random = new Random();

        String[] emptySpace = new String[16];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game[i][j] == 0) {
                    emptySpace[index++] = Integer.toString(i) + Integer.toString(j);
                }
            }
        }
        int occupied = 0;
        for (int i = 0; i < 16; i++) {
            if (emptySpace[i] != null) {
                occupied++;
            }
        }

        int randomIndex = random.nextInt(occupied);
        String cell = emptySpace[randomIndex];
        System.out.println(cell);
        int row = Integer.parseInt(cell.substring(0,1));
        System.out.println(row);
        int column = Integer.parseInt(cell.substring(1));
        System.out.println(column);
    }
}
