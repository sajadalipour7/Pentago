import java.util.Random;
import java.util.Scanner;

/**
 * This is a class for handling the game and core
 * This class controls and handles the core of the game
 */
public class GameHandler {
    private Board board;
    private Player playerBlue;
    private Player playerRed;
    private Cell[][] map;
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();

    public GameHandler() {
        board = new Board();
        map = board.getMap();
    }

    /**
     * This is a method to initialize the player in single player mode
     */
    public void initializeOnePlayer() {
        System.out.println("Enter your name ");
        String nameOfPlayerBlue = sc.next();
        playerBlue = new Player(nameOfPlayerBlue, "blue");
        playerRed = new BotPlayer("AI", "red");
    }

    /**
     * This is a method to initialize the player in multi player mode
     */
    public void initializePlayers() {
        System.out.println("Please enter your names ");
        String name1, name2;
        name1 = sc.next();
        name2 = sc.next();
        Random random = new Random();
        String nameOfPlayerBlue, nameOfPlayerRed;
        if (random.nextInt(2) == 1) {
            nameOfPlayerBlue = name1;
            nameOfPlayerRed = name2;
        } else {
            nameOfPlayerBlue = name2;
            nameOfPlayerRed = name1;
        }
        //nameOfPlayerBlue="Player blue";
        //nameOfPlayerRed="Player red";
        System.out.println(nameOfPlayerBlue + " is blue");
        System.out.println(nameOfPlayerRed + " is red");
        playerBlue = new Player(nameOfPlayerBlue, "blue");
        playerRed = new Player(nameOfPlayerRed, "red");
    }

    /**
     * This is the play method of multi player mode
     * it controls the loop of the game
     */
    public void playMultiPlayer() {
        do {
            playerBlue.setTaws(map);
            playerTurn(playerBlue);
            if (endGame()) {
                break;
            }
            playerRed.setTaws(map);
            playerTurn(playerRed);
        } while (!endGame());
        sayWhoIsWinner();
    }

    /**
     * This is the play method of single player mode
     * it controls the loop of the game
     */
    public void playSinglePlayer() {
        do {
            playerBlue.setTaws(map);
            playerTurn(playerBlue);
            if (endGame()) {
                break;
            }
            playerRed.setTaws(map);
            botPlayerTurn((BotPlayer) playerRed);
        } while (!endGame());
        sayWhoIsWinner();
    }

    /**
     * This is a method to show who is the winner in terminal
     */
    public void sayWhoIsWinner() {
        if (playerBlue.isWinner() && playerRed.isWinner()) {
            System.out.println("Draw !");
        } else if (playerRed.isWinner()) {
            System.out.println(playerRed.getName() + " Won !");
        } else if (playerBlue.isWinner()) {
            System.out.println(playerBlue.getName() + " Won !");
        } else if (board.isMapFull()) {
            System.out.println("Draw !");
        }
    }

    /**
     * This is a method to handle the bot's movements
     */
    public void botPlayerTurn(BotPlayer player) {
        player.setMainMap(map);
        board.showMap();
        System.out.println("AI turn !\n");
        int x = player.findBestPlaceAndBestRotation().charAt(0) - '0';
        int y = player.findBestPlaceAndBestRotation().charAt(1) - '0';
        System.out.println("AI chose this position : (" + y + "," + x + ")");
        map[x][y].setMode(-1);
        board.updateBoardMap();
        board.showMap();
        player.setTaws(map);
        player.setMainMap(map);
        if (player.isWinner()) {
            return;
        }
        if (board.isMapFull()) {
            return;
        }
        //rotating a random block between all the blocks except the block which taw is in it
        int inBlockNum = player.getBlockWhereImIn(x, y) - 1;
        int properBlockNum = inBlockNum;
        while (properBlockNum == inBlockNum) {
            properBlockNum = random.nextInt(4);
        }
        String direction;
        int direct;
        //random direction of rotation
        int randDirect = random.nextInt(2);
        if (randDirect == 1) {
            direct = 1;
            direction = "clockwise";
        } else {
            direct = 2;
            direction = "anti-clockwise";
        }
        System.out.println("AI chose block " + (properBlockNum + 1) + " to rotate " + direction);
        board.rotateBlockByNum(properBlockNum, direct);
        board.updateBoardMap();
        player.setTaws(map);
        player.setMainMap(map);
        if (player.isWinner()) {
            board.showMap();
            return;
        }
        if (board.isMapFull()) {
            board.showMap();
            return;
        }
    }

    /**
     * This is a method to handle the player's movements for the player which is it's round
     */
    public void playerTurn(Player player) {
        board.showMap();
        int mode;
        //setting mode
        if (player.getColor().equals("blue")) {
            mode = 1;
        } else {
            mode = -1;
        }
        System.out.println(player.getName() + " it's your turn !\n");
        System.out.println("Enter position of the cell you want to put your taw");
        int y = sc.nextInt();
        int x = sc.nextInt();
        //handling invalid inputs
        while (map[x][y].getMode() != 0) {
            System.out.println("That place is filled. please retry another place !");
            y = sc.nextInt();
            x = sc.nextInt();
        }
        map[x][y].setMode(mode);
        board.updateBoardMap();
        board.showMap();
        player.setTaws(map);
        if (player.isWinner()) {
            return;
        }
        if (board.isMapFull()) {
            return;
        }
        System.out.println("Enter which block do you want to rotate (1 or 2 or 3 or 4)");
        int blockNum = sc.nextInt() - 1;
        //handling invalid inputs
        while (blockNum != 0 && blockNum != 1 && blockNum != 2 && blockNum != 3) {
            System.out.println("Wrong input ! . please retry");
            blockNum = sc.nextInt() - 1;
        }
        System.out.println("1)Clockwise\n2)Anti clockwise");
        int direction = sc.nextInt();
        //handling invalid inputs
        while (direction != 1 && direction != 2) {
            System.out.println("Wrong input ! . please retry");
            direction = sc.nextInt();
        }
        board.rotateBlockByNum(blockNum, direction);
        board.updateBoardMap();
        player.setTaws(map);
        if (player.isWinner()) {
            board.showMap();
            return;
        }
        if (board.isMapFull()) {
            board.showMap();
            return;
        }

    }

    /**
     * This is a method to check if the game is finished
     *
     * @return
     */
    public boolean endGame() {
        playerRed.setTaws(map);
        playerBlue.setTaws(map);
        if (playerRed.isWinner() || playerBlue.isWinner()) {
            return true;
        }
        if (board.isMapFull()) {
            return true;
        }
        return false;
    }

    /**
     * This is a method to show the menu in a proper way
     */
    public void showMenu() {
        System.out.println("******************************");
        System.out.println("---------Pentago Game---------\n");
        System.out.println("1) SinglePlayer Mode");
        System.out.println("2) MultiPlayer Mode");
        System.out.println("3) Exit");
    }
}
