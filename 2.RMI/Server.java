import java.rmi.*;
import java.net.*;

public class Server{
    public static void main(String[] args) {
        try {
            Servant s = new Servant(); // Create an instance of the remote object
            Naming.rebind("Server", s); // Bind the remote object to the registry
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


//Naming.rebind("Server", s);: Binds the remote object s to the name "Server" in the RMI registry.