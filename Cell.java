/**
 * This is class for cell blocks of the game board
 * <p>
 * if the mode is 0 ---> empty cell
 * if the mode is 1 ---> blue Taw
 * if the mode is -1 ---> red Taw
 */
public class Cell {
    private int mode;

    public Cell() {
        mode = 0;
    }

    public Cell(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
