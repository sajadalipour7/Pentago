/**
 * This is a class for the players and their details and properties
 */
public class Player {
    private String name;
    private String color;
    private Cell[][] taws;

    public Player(String name, String color) {
        this.color = color;
        this.name = name;
    }

    /**
     * This is method to check if the player has won
     *
     * @return
     */
    public boolean isWinner() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (searchForFiveConsecutiveTaws(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This is a method to check if there is five or more consecutive same color taws
     *
     * @param x
     * @param y
     * @return
     */
    public boolean searchForFiveConsecutiveTaws(int x, int y) {
        int isThereAny = 0;
        isThereAny += searchByDirection(x, y, 1, 1);
        isThereAny += searchByDirection(x, y, 1, 0);
        isThereAny += searchByDirection(x, y, 1, -1);
        isThereAny += searchByDirection(x, y, 0, 1);
        isThereAny += searchByDirection(x, y, 0, -1);
        isThereAny += searchByDirection(x, y, -1, 1);
        isThereAny += searchByDirection(x, y, -1, 0);
        isThereAny += searchByDirection(x, y, -1, -1);
        if (isThereAny > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is a method to search from a specific place with a specific direction to find out if there is five or more consecutive taws
     *
     * @param x
     * @param y
     * @param xDirection
     * @param yDirection
     * @return
     */
    public int searchByDirection(int x, int y, int xDirection, int yDirection) {
        int mode;
        //setting the mode
        if (color.equals("blue")) {
            mode = 1;
        } else {
            mode = -1;
        }
        //check if it's not the same color
        if (taws[x][y].getMode() != mode) {
            return 0;
        }
        int consecutiveNum = 1;
        x += xDirection;
        y += yDirection;
        //check if it is out of range
        if (x < 0 || x > 5 || y < 0 || y > 5) {
            return 0;
        }
        while (x >= 0 && x < 6 && y >= 0 && y < 6) {
            if (taws[x][y].getMode() == mode) {
                consecutiveNum++;
                x += xDirection;
                y += yDirection;
            } else {
                break;
            }
        }
        if (consecutiveNum >= 5) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Cell[][] getTaws() {
        return taws;
    }

    public void setTaws(Cell[][] taws) {
        this.taws = taws;
    }
}
