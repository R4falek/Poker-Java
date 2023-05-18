package pl.edu.agh.kis.pz1;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Server class implements a game server
 *
 * @author  Rafał
 * @version 1.0
 */
public class Server {
    /**
     * parameters
     */
    private static final Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(Server.class.getName());
    }

    /**
     * main method
     */
    public static void main(String[] args) throws IOException {
        final int PORT = 4040;
        do {
            Scanner scanner = new Scanner(System.in);
            ServerSocket serverSocket = new ServerSocket(PORT);
            ServerHandler handler;

            logger.log(Level.INFO, "Server started...");
            logger.log(Level.INFO, "How many players: ");
            handler = new ServerHandler(scanner.nextInt());

            handler.startServer(scanner, serverSocket, logger);
            handler.setUpPlayersNames(logger);

            while (true) {
                handler.gameCircle(logger);
                handler.equalBetsHandler(logger);

                if (handler.game.getGamePartString().equals("finish") && handler.winner != null) {
                    handler.winner.addReward(handler.game.getMoneyPool());
                    handler.game.reset(handler.users);
                    handler.rounds--;
                    if (handler.rounds == 0) {
                        break;
                    }
                    handler.nextRoundStart();
                }
            }
            handler.endGame(logger,scanner);
            serverSocket.close();
            if(handler.next == 0){
                break;
            }
        } while (true);
    }
}
