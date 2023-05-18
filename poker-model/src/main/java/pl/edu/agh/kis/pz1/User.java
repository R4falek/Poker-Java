package pl.edu.agh.kis.pz1;

import java.net.Socket;

/**
 * The User class implements a user of a server
 *
 * @author  Rafał
 * @version 1.0
 */
public class User {
    /**
     * parameters
     */
    private String name;
    private final Socket socket;
    private boolean pass = false;

    /**
     * constructor
     * @param s - user's socket
     */
    public User(Socket s){
        socket = s;
    }

    /**
     * sets username
     * @param n - name
     */
    public void setName(String n){
        name = n;
    }

    /**
     * returns user's socket
     * @return Socket
     */
    public Socket getSocket(){
        return socket;
    }

    /**
     * returns username
     */
    public String getName() {
        return name;
    }

    /**
     * marks user if he/she passed
     */
    public void pass(){pass = true;}

    /**
     * checks if puser passed
     * @return boolean
     */
    public boolean getPass(){return pass;}

    /**
     * resets user parameters after round
     */
    public void reset(){
        pass = false;
    }
}
