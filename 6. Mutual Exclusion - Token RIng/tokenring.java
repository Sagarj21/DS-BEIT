import java.io.*;
import java.util.*;

class tokenring {

    public static void main(String args[]) throws Throwable {
        Scanner scan = new Scanner(System.in); // Scanner object for user input
        System.out.println("Enter the num of nodes:"); // Prompt user to enter number of nodes
        int n = scan.nextInt(); // Read the number of nodes
        int m = n - 1; // Number of edges in the ring
        int token = 0; // Initialize token to node 0
        int ch = 0, flag = 0; // Variables for user choice and flag for error handling
        
        // Display the nodes forming the ring
        for (int i = 0; i < n; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" " + 0); // Closing the ring
        
        do {
            System.out.println("Enter sender:"); // Prompt user to enter sender
            int s = scan.nextInt(); // Read sender node
            System.out.println("Enter receiver:"); // Prompt user to enter receiver
            int r = scan.nextInt(); // Read receiver node
            System.out.println("Enter Data:"); // Prompt user to enter data
            int a;
            a = scan.nextInt(); // Read data
            
            // Simulate the token passing process
            System.out.println("Sender " + s + " sending data: " + a);
            for (int i = s + 1; i != r; i = (i + 1) % n) {
                System.out.println("data  " + a + " forwarded by " + i);
            }
            System.out.println("Receiver " + r + " received data: " + a + "\n");
            token = s; // Update token to sender
            
            // Loop for user choice validation
            do {	
                try {
                    if (flag == 1)
                        System.out.print("Invalid Input!!...");
                    System.out.print("Do you want to send again?? enter 1 for Yes and 0 for No : ");
                    ch = scan.nextInt();
                    if (ch != 1 && ch != 0)
                        flag = 1;
                    else
                        flag = 0;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input");
                }
            } while (ch != 1 && ch != 0);
        } while (ch == 1); // Continue sending if user chooses to
    }
}
