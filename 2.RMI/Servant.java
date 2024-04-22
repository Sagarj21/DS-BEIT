import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Implement the remote interface
public class Servant extends UnicastRemoteObject implements ServerInterface {
    // Constructor
    protected Servant() throws RemoteException {
        super();
    }

    // Implementation of the remote method
    @Override
    public String concat(String a, String b) throws RemoteException {
        System.out.println("Received String 1 from Client, " + a );
        System.out.println("Received String 2 from Client, " +  b);
        return a + b;
    }
}
//class Servant extends UnicastRemoteObject implements ServerInterface: Defines a class Servant that implements the ServerInterface interface and extends UnicastRemoteObject. This class represents the remote object.