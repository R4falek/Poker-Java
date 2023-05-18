package pl.edu.agh.kis.pz1;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ServerHandler class implements a server handler
 *
 * @author  Rafał
 * @version 1.0
 */
public class ServerHandler {
    /**
     * parameters
     */
    CardGame game;
    ArrayList<User> users;
    final int PLAYERS;
    private static final Logger logger2;
    int rounds;
    int next = 1;
    Player winner = null;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger2 = Logger.getLogger(Server.class.getName());
    }

    /**
     * constructor
     * @param players - number of players
     */
    public ServerHandler(int players){
        game = new CardGame(players, 5, 100);
        PLAYERS = players;
        users = new ArrayList<>();
    }

    /**
     * returns users
     * @return ArrayList<User>
     */
    public ArrayList<User> getUsers(){ return users; }

    /**
     * sends information to all users whose turn is
     * @param u - current user on turn
     */
    public boolean whoseTurnInform(User u) throws IOException {
        for(User uu : users){
            PrintWriter helpOut = new PrintWriter(uu.getSocket().getOutputStream(), true);
            if(u.equals(uu)){
                helpOut.println("Your turn");
            }
            else{
                helpOut.println(u.getName() + "'s turn...");
            }
        }
        return true;
    }

    /**
     * sends information to all users what user on turn did
     * @param u - current user on turn
     * @param input - type of move
     * @param helpInput - parameter of move
     */
    public boolean currentMoveInform(User u, String input, String helpInput) throws IOException {
        for(User uu : users){
            PrintWriter helpOut = new PrintWriter(uu.getSocket().getOutputStream(), true);
            if(!u.equals(uu)){
                if(input.equals("bet")){
                    helpOut.println(u.getName() + ": " + input + " " + helpInput);

                }
                else{
                    helpOut.println(u.getName() + ": " + input + " " + helpInput.length());
                }
            }
        }
        return true;
    }

    /**
     * sends information to all users about his/her cards, money and money pool
     * @param u - current user on turn
     * @param out - user output
     */
    public boolean sendDataToPlayer(User u, PrintWriter out){
        out.println("start");
        out.println(game.getPlayer(users.indexOf(u)).getCardsString() + "\nSaldo: "
                + game.getPlayer(users.indexOf(u)).getMoney()
                + "\nMoney pool: " + game.getMoneyPool());
        out.println("stop");
        return true;
    }

    /**
     * handler if user passed
     * @param input - command
     * @param u - current user on turn
     * @return boolean
     */
    public boolean ifPlayerPassed(String input, User u){
        if(input.equals("pass")){
            logger2.log(Level.INFO,"[{0}] passed this deal", u.getName());
            u.pass();
            game.getPlayer(users.indexOf(u)).pass();
            return true;
        }
        return false;
    }

    /**
     * returns true if only one player didn't pass otherwise false
     * @return boolean
     */
    public boolean isOnlyOnePlayerInGame(){
        int i = 0;
        for(User u : users){
            if(!u.getPass()){
                i++;
            }
        }
        return i == 1;
    }

    /**
     * sends information about tour winner to all users
     */
    void winnerInform() throws IOException {
        for(User uu : users){
            PrintWriter helpOut = new PrintWriter(uu.getSocket().getOutputStream(), true);
            helpOut.println("\nWINNER: " + getUsers().get(game.getPlayers().indexOf(winner)).getName() + " :WINNER");
        }
    }

    /**
     * method to initialize server and connerct players
     * @param scanner - scanner
     * @param serverSocket - server socket
     * @param logger - logger
     */
    public void startServer(Scanner scanner, ServerSocket serverSocket, Logger logger) throws IOException {
        logger.log(Level.INFO, "How many round: ");
        rounds = scanner.nextInt();
        logger.log(Level.INFO, "Waiting for clients... [0/{0}]", PLAYERS);

        for (int i = 1; i < PLAYERS + 1; i++) {
            Socket clientSocket = serverSocket.accept();
            users.add(new User(clientSocket));
            logger.log(Level.INFO, "Waiting for clients... [{0}/{1}]", new String[]{String.valueOf(i), String.valueOf(PLAYERS)});
        }
        game.start();
    }

    /**
     * method to set up players names
     * @param logger - logger
     */
    public void setUpPlayersNames(Logger logger){
        for (User u : users) {
            try {
                PrintWriter out = new PrintWriter(u.getSocket().getOutputStream(), true);
                Scanner in = new Scanner(u.getSocket().getInputStream());
                String name = in.nextLine();
                u.setName(name);
                logger.log(Level.INFO, "[{0}] Connected!", name);
                out.println("Your cards " + name + ": ");
                sendDataToPlayer(u, out);
            } catch (IOException ignored) { logger.log(Level.WARNING, "ERROR");}
        }
    }

    /**
     * end hame handler
     * @param logger - logger
     * @param scanner - scanner
     */
    public void endGame(Logger logger, Scanner scanner) throws IOException {
        Player gameWinner = game.getPlayer(0);
        for (Player p : game.getPlayers()) {
            if (gameWinner.getMoney() < p.getMoney()) {
                gameWinner = p;
            }
        }
        for (User u : users) {
            PrintWriter out = new PrintWriter(u.getSocket().getOutputStream(), true);
            out.println("\nThe end...");
            out.println("THE WINNER OF THE GAME IS " + users.get(game.getPlayers().indexOf(gameWinner)).getName());
            out.println("Saldo: " + game.getPlayer(users.indexOf(u)).getMoney());
        }
        logger.log(Level.INFO, "Next game? (1 - yes     0 - no)");
        next = scanner.nextInt();
    }

    /**
     * game conditions to check before round starts
     * returns true if fulfilled otherwise false
     * @param u - current user on turn
     * @return boolean
     */
    public boolean gameConditions(User u){
        if (isOnlyOnePlayerInGame()) {
            game.setGamePartToCheck();
        }
        //Sprawdzenie czy ktos passowal
        return u.getPass() && !game.getGamePartString().equals("check");
    }

    /**
     * main game circle
     * @param logger - logger
     */
    public void gameCircle(Logger logger){
        for (User u : users) {

            if(gameConditions(u)){continue;}

            try {
                PrintWriter out = new PrintWriter(u.getSocket().getOutputStream(), true);
                Scanner in = new Scanner(u.getSocket().getInputStream());

                String input;

                if (game.getGamePartString().equals("bet")) {
                    whoseTurnInform(u);
                    out.println("How much u want to bet: ");
                    input = in.nextLine();

                    if (ifPlayerPassed(input, u)) {
                        continue;
                    }

                    game.getPlayer(users.indexOf(u)).bet(Integer.parseInt(input));
                    game.addMoneyToPool(Integer.parseInt(input));
                    out.println("end");
                    sendDataToPlayer(u, out);
                    currentMoveInform(u, "bet", input);
                    continue;
                }
                if (game.getGamePartString().equals("swap")) {
                    whoseTurnInform(u);
                    out.println("Which cards do you want to swap? (example: 01234)");
                    input = in.nextLine();

                    if (ifPlayerPassed(input, u)) {
                        continue;
                    }

                    logger.log(Level.INFO, "[{0}] swap cards {1}", new String[]{u.getName(), input});
                    game.getPlayer(users.indexOf(u)).swapCards(game.getDeck(), input);

                    out.println("end");
                    sendDataToPlayer(u, out);
                    currentMoveInform(u, "swap", input);
                    continue;
                }
                if (game.getGamePartString().equals("check")) {
                    logger.log(Level.INFO, "lets check");
                    winner = game.check();
                    winnerInform();
                    sendDataToPlayer(u, out);
                    break;
                }
            } catch (IOException ignored) { logger.log(Level.WARNING, "ERROR");}
        }
    }

    /**
     * push forward game if bets are equal
     * @param logger - logger
     */
    public void equalBetsHandler(Logger logger){
        if (game.areBetsEqual()) {
            game.nextGamePart();
            logger.log(Level.INFO,game.getGamePartString());
        }
    }

    /**
     * sends infromation to users after new round started
     */
    public void nextRoundStart() throws IOException {
        for (User u : users) {
            PrintWriter out = new PrintWriter(u.getSocket().getOutputStream(), true);
            out.println("\nNext round");
            out.println("Your cards " + u.getName() + ": ");
            sendDataToPlayer(u, out);
        }
    }
}
