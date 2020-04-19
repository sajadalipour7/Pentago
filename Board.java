/**
 * This is a class for the board of the game
 */
public class Board {
    private final int row = 6;
    private final int column = 6;
    private Block[] blocks = new Block[4];
    private Cell[][] map = new Cell[row][column];

    public Board() {
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block();
        }
        updateBoardMap();
    }

    /**
     * This is a method for updating whole map with blocks data
     */
    public void updateBoardMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i < 3 && j < 3) {
                    map[i][j] = blocks[0].getCells()[i][j];
                } else if (i < 3 && j >= 3) {
                    map[i][j] = blocks[1].getCells()[i][j - 3];
                } else if (i >= 3 && j < 3) {
                    map[i][j] = blocks[2].getCells()[i - 3][j];
                } else {
                    map[i][j] = blocks[3].getCells()[i - 3][j - 3];
                }
            }
        }
    }

    /**
     * This is a method to rotate a block with giving block's number and the direction of rotation
     *
     * @param blockNum
     * @param direction
     */
    public void rotateBlockByNum(int blockNum, int direction) {
        blocks[blockNum].rotate(direction);

    }

    /**
     * This is a method to check if map is full
     *
     * @return
     */
    public boolean isMapFull() {
        //counting all the taws
        int iterator = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j].getMode() != 0) {
                    iterator++;
                }
            }
        }
        if (iterator == 36) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is a method to show the map with a user friendly design
     */
    public void showMap() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                switch (map[i][j].getMode()) {
                    case 0:
                        System.out.print(ANSI_WHITE + "⚪" + ANSI_RESET);
                        break;
                    case 1:
                        System.out.print(ANSI_BLUE + "⚫" + ANSI_RESET);
                        break;
                    case -1:
                        System.out.print(ANSI_RED + "⚫" + ANSI_RESET);
                        break;
                }
                System.out.print(" ");
                if (j == 2) {
                    System.out.print(ANSI_GREEN + "| " + ANSI_RESET);
                }
            }
            if (i == 2) {
                System.out.print(ANSI_CYAN + "\n----------------" + ANSI_RESET);
            }
            System.out.println();
        }
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Cell[][] getMap() {
        return map;
    }
}
