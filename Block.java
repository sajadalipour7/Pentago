/**
 * This is a class for block details
 */
public class Block {
    private Cell[][] cells = new Cell[3][3];

    public Block() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /**
     * This is a method to rotate the block with a specific direction of rotation
     *
     * @param direction
     */
    public void rotate(int direction) {
        //if it is clockwise
        if (direction == 1) {
            //rotating the array
            for (int i = 0; i < 2; i++) {
                Cell tmp = new Cell(cells[2 - i][0].getMode());
                cells[2 - i][0] = cells[2][2 - i];
                cells[2][2 - i] = cells[i][2];
                cells[i][2] = cells[0][i];
                cells[0][i] = tmp;
            }
            //if it is anti-clockwise
        } else if (direction == 2) {
            //rotating the array
            for (int i = 0; i < 2; i++) {
                Cell tmp = new Cell(cells[2 - i][0].getMode());
                cells[2 - i][0] = cells[0][i];
                cells[0][i] = cells[i][2];
                cells[i][2] = cells[2][2 - i];
                cells[2][2 - i] = tmp;
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
