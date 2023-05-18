package pl.edu.agh.kis.pz1;
import junit.framework.TestCase;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerHandlerTest extends TestCase {

    public void testIfPlayerPassed() {
        ServerHandler serverHandler = new ServerHandler(2);
        serverHandler.game.start();
        serverHandler.users.add(new User(new Socket()));
        serverHandler.ifPlayerPassed("pass",serverHandler.getUsers().get(0));
        assertEquals(true, serverHandler.ifPlayerPassed("pass",serverHandler.getUsers().get(0)));
    }

    public void testWhoseTurnInform() throws IOException {
        ServerHandler serverHandler = new ServerHandler(2);
        User user = new User(new Socket());
        assertEquals(true,serverHandler.whoseTurnInform(user));
    }

    public void testCurrentMoveInform() throws IOException {
        ServerHandler serverHandler = new ServerHandler(2);
        User user = new User(new Socket());
        assertEquals(true,serverHandler.currentMoveInform(user, "",""));
    }

    public void testIsOnlyOnePlayerInGame() {
        ServerHandler serverHandler = new ServerHandler(2);
        serverHandler.game.start();
        serverHandler.users.add(new User(new Socket()));
        serverHandler.users.add(new User(new Socket()));
        serverHandler.users.get(0).pass();

        assertTrue(serverHandler.isOnlyOnePlayerInGame());
    }
}