import java.util.Scanner;

/**
 * In the name of god
 * <p>
 * --------------------------
 * The Pentago Game
 * --------------------------
 * This Project is the pentago game in computer
 * It includes Main,GameHandler,Board,Cell,Player,BotPlayer Classes
 * <p>
 * This class is the driver of project
 *
 * @author MohammadSajad Alipour
 * @version 1.0    2020
 */
public class Main {
    /**
     * Driver or main Function
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //A Menu infinity loop
        do {
            //Creating a gameHandler to handle the game
            GameHandler gameHandler = new GameHandler();
            gameHandler.showMenu();
            int mode = sc.nextInt();
            switch (mode) {
                //SinglePlayer mode
                case 1:
                    gameHandler.initializeOnePlayer();
                    gameHandler.playSinglePlayer();
                    break;
                // MultiPlayer mode
                case 2:
                    gameHandler.initializePlayers();
                    gameHandler.playMultiPlayer();
                    break;
                //Exit mode
                case 3:
                    return;
                //handling Wrong or invalid input
                default:
                    System.out.println("Invalid input!");
                    break;
            }

        } while (true);


    }
}
