import ReverseModule.*; 
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*; 
import org.omg.PortableServer.*;

class ReverseServer
{
	public static void main(String[] args)
	{
		try
		{ 	// initialize the ORB
			org.omg.CORBA. ORB orb = org.omg.CORBA.ORB.init(args, null);
			// initialize the BOA/POA // Obtain a reference to the root POA
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")); 
			rootPOA.the_POAManager().activate();
			// creating the ReverseImpl object 
			ReverseImpl rvr = new ReverseImpl();
			// get the object reference from the servant class 
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);
	
			System.out.println("Step1");
			// Narrow the object reference to the Reverse interface
			Reverse h_ref = ReverseModule.ReverseHelper.narrow(ref); 

			System.out.println("Step2");
			 // Resolve the Naming Service reference
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			System.out.println("Step3");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			System.out.println("Step4");
			// Bind the Reverse object reference to a name in the Naming Service
			String name = "Reverse";
			NameComponent path[] = ncRef.to_name(name); 
			ncRef.rebind(path,h_ref);

			System.out.println("Reverse Server reading and waiting....");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




// CORBA String Example: This code demonstrates a simple CORBA application for reversing a string.
// Server: The ReverseServer class initializes the ORB, creates a ReverseImpl object, obtains an object reference, binds it to the Naming Service, and runs the ORB event loop.
// Implementation: The ReverseImpl class implements the Reverse interface defined in IDL. It contains the logic for reversing a string.
// Client: The ReverseClient class initializes the ORB, resolves the Naming Service reference, resolves the Reverse object reference, prompts the user to enter a string, invokes the reverse_string method on the Reverse object, and prints the reversed string.
// IDL Interface: The IDL interface Reverse defines the reverse_string method, which takes a string as input and returns a reversed string.
// Operation: The client sends a string to the server, the server reverses it, and sends back the reversed string to the client.
// Communication: CORBA handles the communication between the client and server transparently, allowing objects to interact across a network as if they were local objects