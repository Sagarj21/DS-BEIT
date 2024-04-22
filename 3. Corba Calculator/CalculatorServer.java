import CalculatorModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class CalculatorServer {
    public static void main(String args[]) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);
            // Obtain a reference to the root POA (Portable Object Adapter)
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            // Activate the POA manager
            rootPOA.the_POAManager().activate();

            // Create an instance of CalculatorImpl
            CalculatorImpl calcImpl = new CalculatorImpl();
            // Obtain an object reference for the CalculatorImpl instance
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(calcImpl);

            // Obtain a reference to the Naming Service
            NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            // Bind the Calculator object reference to a name in the Naming Service
            NameComponent path[] = ncRef.to_name("Calculator");
            ncRef.rebind(path, ref);

            System.out.println("Calculator Server is ready...");
            // Start the ORB event loop
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





// IDL (Interface Definition Language): Used to define the interface for the distributed objects.
// ORB (Object Request Broker): Provides the middleware services that enable distributed objects to communicate with each other.
// POA (Portable Object Adapter): Manages the lifecycle of objects and serves as an intermediary between clients and server objects.
// Servant: The actual implementation of the CORBA object.
// Naming Service: Provides a mapping between object names and object references.
// Client: Initiates remote method invocations on the CORBA objects.
// Server: Hosts the CORBA objects and responds to remote method invocations from clients.
// Object Reference: A reference to a CORBA object that can be used by clients to invoke its methods.
// Method Invocation: The process of calling methods on remote objects as if they were local objects.