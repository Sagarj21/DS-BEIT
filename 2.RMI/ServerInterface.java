import java.rmi.*;

// Define an interface for the remote object
public interface ServerInterface extends Remote {//Remote interface. This interface defines the methods that can be invoked remotely.

    // Declare a method that will be called remotely
    String concat(String a, String b) throws RemoteException;
}
