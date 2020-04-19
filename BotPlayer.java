/**
 * This is a class for the AI bot Player and calculations for Artificial Intelligence.
 * Also this class inherits from Player class.
 */
public class BotPlayer extends Player {
    private Cell[][] mainMap;
    private Cell[][] nextMoveMap = new Cell[6][6];

    public BotPlayer(String name, String color) {
        super(name, color);

    }

    /**
     * This is a method to find the best place to put taw
     * My Algorithm is that I go trough all the map and in every place
     * I check all the consecutive same color taws numbers in all the eight directions
     * At the end I find the place which has the maximum consecutive same color taws in most directions
     * and I return the position of that place in string format
     *
     * @return
     */
    public String findBestPlaceAndBestRotation() {
        int bestX = 0, bestY = 0, maximumSequence = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (mainMap[i][j].getMode() != 0) {
                    continue;
                }
                //I copy the main map because I don't want change the main map
                copyMainMapToNextMoveMap();
                nextMoveMap[i][j].setMode(-1);
                int tmpSeq = findMaxSequence(i, j);
                if (tmpSeq > maximumSequence) {
                    maximumSequence = tmpSeq;
                    bestX = i;
                    bestY = j;
                }

            }
        }
        return "" + bestX + "" + bestY;
    }

    /**
     * This is a method to get sum of consecutive same color taws in all the eight directions
     *
     * @param x
     * @param y
     * @return
     */
    private int findMaxSequence(int x, int y) {
        int[] maxes = new int[8];
        //going trough all eight directions
        maxes[0] = goTroughDirection(x, y, 1, 1);
        maxes[1] = goTroughDirection(x, y, 1, 0);
        maxes[2] = goTroughDirection(x, y, 1, -1);
        maxes[3] = goTroughDirection(x, y, 0, 1);
        maxes[4] = goTroughDirection(x, y, 0, -1);
        maxes[5] = goTroughDirection(x, y, -1, 1);
        maxes[6] = goTroughDirection(x, y, -1, 0);
        maxes[7] = goTroughDirection(x, y, -1, -1);
        int sumOfConsecutiveTaws = 0;
        for (int i = 0; i < 8; i++) {
            sumOfConsecutiveTaws += maxes[i];
        }
        return sumOfConsecutiveTaws;
    }

    /**
     * This is a method to get the number of consecutive same color taws with a specific direction
     *
     * @param x
     * @param y
     * @param xDirection
     * @param yDirection
     * @return
     */
    private int goTroughDirection(int x, int y, int xDirection, int yDirection) {
        int consecutiveTaws = 1;
        x += xDirection;
        y += yDirection;
        //check if it is out of range
        if (x < 0 || x > 5 || y < 0 || y > 5) {
            return 1;
        }
        while (x >= 0 && x < 6 && y >= 0 && y < 6) {
            if (nextMoveMap[x][y].getMode() == -1) {
                consecutiveTaws++;
                x += xDirection;
                y += yDirection;
            } else {
                break;
            }
        }
        return consecutiveTaws;

    }

    /**
     * This is a method to copy MainMap into nextMoveMap
     */
    private void copyMainMapToNextMoveMap() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Cell tmp = new Cell(mainMap[i][j].getMode());
                nextMoveMap[i][j] = tmp;
            }
        }
    }

    /**
     * This is a method to get the block number of a specific place
     *
     * @param x
     * @param y
     * @return
     */
    public int getBlockWhereImIn(int x, int y) {
        if (x < 3 && y < 3) {
            return 1;
        }
        if (y >= 3 && x < 3) {
            return 2;
        }
        if (y < 3 && x >= 3) {
            return 3;
        } else {
            return 4;
        }
    }

    public void setMainMap(Cell[][] mainMap) {
        this.mainMap = mainMap;
    }

}
