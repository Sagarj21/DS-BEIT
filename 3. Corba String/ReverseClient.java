import ReverseModule.*; 
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*; 
import java.io.*;

class ReverseClient {
    public static void main(String args[]) {
        Reverse ReverseImpl = null;
        try {
            // Initialize the ORB 
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
            // Resolve the NameService reference
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); 
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Reverse";
            // Resolve the Reverse object reference
            ReverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Enter String="); 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
            String str = br.readLine();
            // Invoke the reverse_string method on the Reverse object
            String tempStr = ReverseImpl.reverse_string(str);
            System.out.println(tempStr);

            System.out.println("Enter String 1: "); 
            String str1 = br.readLine();
            System.out.println("Enter String 2: "); 
            String str2 = br.readLine();
            String tempStr = ReverseImpl.concat_strings(str1, str2);
            System.out.println(tempStr);



        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}
