import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        try {
            Scanner s = new Scanner(System.in); // Create a Scanner object for user input
            System.out.println("Enter the Server address : ");
            String server = s.nextLine(); // Read the server address from the user
            ServerInterface si = (ServerInterface) Naming.lookup("rmi://" + server + "/Server"); // Look up the remote object in the registry
            System.out.println("Enter first string : ");
            String first = s.nextLine(); // Read the first string from the user
            System.out.println("Enter second string : ");
            String second = s.nextLine(); // Read the second string from the user
            System.out.println("Concatenated String : " + si.concat(first, second)); // Invoke the remote method and print the result
            s.close(); // Close the Scanner object
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
