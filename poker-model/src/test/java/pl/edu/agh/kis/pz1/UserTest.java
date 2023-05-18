package pl.edu.agh.kis.pz1;

import junit.framework.TestCase;

import java.net.Socket;

public class UserTest extends TestCase {

    public void testTestSetName() {
        User user = new User(new Socket());
        user.setName("imie");
        assertEquals(user.getName(),"imie");
    }

    public void testPass() {
        User user = new User(new Socket());
        user.pass();
        assertEquals(true, user.getPass());
    }
}