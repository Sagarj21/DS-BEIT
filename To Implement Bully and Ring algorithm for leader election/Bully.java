import java.util.*;

public class Bully {
    int coordinator; // Variable to store the current coordinator process ID
    int max_processes; // Maximum number of processes in the system
    boolean processes[]; // Array to represent the availability of each process


    public Bully(int max) { // Constructor to initialize the Bully object
        max_processes = max; // Assigning the maximum number of processes
        processes = new boolean[max_processes]; // Initializing the processes array
        coordinator = max; // Initializing the coordinator as the highest process ID

        System.out.println("Creating processes..");
        for(int i = 0; i < max; i++) { // Loop to create processes
            processes[i] = true; // Setting the process as up
            System.out.println("P"+ (i+1) + " created");
        }
        System.out.println("Process P" + coordinator + " is the coordinator"); // Displaying the initial coordinator
    }

    void displayProcesses() { // Method to display the status of each process
        for(int i = 0; i < max_processes; i++) { // Loop through each process
            if(processes[i]) { // Check if the process is up
                System.out.println("P" + (i+1) + " is up");
            } else {
                System.out.println("P" + (i+1) + " is down");
            }
        }
        System.out.println("Process P" + coordinator + " is the coordinator"); // Display the coordinator
    }

    void upProcess(int process_id) { // Method to bring a process up
        if(!processes[process_id - 1]) { // Check if the process is down
            processes[process_id - 1] = true; // Set the process as up
            System.out.println("Process " + process_id + " is now up.");
        } else {
            System.out.println("Process " + process_id + " is already up.");
        }
    }

    void downProcess(int process_id) { // Method to bring a process down
        if(!processes[process_id - 1]) { // Check if the process is already down
            System.out.println("Process " + process_id + " is already down.");
        } else {
            processes[process_id - 1] = false; // Set the process as down
            System.out.println("Process " + process_id + " is down.");
        }
    }

    void runElection(int process_id) { // Method to initiate the election algorithm
        coordinator = process_id; // Set the current process as the coordinator
        boolean keepGoing = true;

        for(int i = process_id; i < max_processes && keepGoing; i++) { // Loop through processes to send election message
            System.out.println("Election message sent from process " + process_id + " to process " + (i+1));

            if(processes[i]) { // Check if the process is up
                keepGoing = false; // Stop sending election messages if an up process with higher ID is found
                runElection(i + 1); // Recursive call to continue the election process
            }
        }
    }

    public static void main(String args[]) { // Main method to execute the Bully algorithm
        Bully bully = null; // Bully object
        int max_processes = 0, process_id = 0; // Variables to store maximum processes and process IDs
        int choice = 0; // Variable to store user choice
        Scanner sc = new Scanner(System.in); // Scanner object for user input

        while(true) { // Main menu loop
            System.out.println("Bully Algorithm");
            System.out.println("1. Create processes");
            System.out.println("2. Display processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice:- ");
            choice = sc.nextInt(); // Read user choice

            switch(choice) { // Switch statement based on user choice
                case 1: // Create processes
                    System.out.print("Enter the number of processes:- ");
                    max_processes = sc.nextInt(); // Read the number of processes
                    bully = new Bully(max_processes); // Create a new Bully object
                    break;
                case 2: // Display processes
                    bully.displayProcesses(); // Call the displayProcesses method
                    break;
                case 3: // Bring a process up
                    System.out.print("Enter the process number to up:- ");
                    process_id = sc.nextInt(); // Read the process ID
                    bully.upProcess(process_id); // Call the upProcess method
                    break;
                case 4: // Bring a process down
                    System.out.print("Enter the process number to down:- ");
                    process_id = sc.nextInt(); // Read the process ID
                    bully.downProcess(process_id); // Call the downProcess method
                    break;
                case 5: // Run the election algorithm
                    System.out.print("Enter the process number which will perform election:- ");
                    process_id = sc.nextInt(); // Read the process ID
                    bully.runElection(process_id); // Call the runElection method
                    bully.displayProcesses(); // Display the status of processes after election
                    break;
                case 6: // Exit the program
                    System.exit(0); // Exit the program
                    break;
                default: // Invalid choice
                    System.out.println("Error in choice. Please try again.");
                    break;
            }
        }
    }
}


// This Java program implements the Bully algorithm for leader election in a distributed system. It allows users to create processes, display process status, bring processes up or down, and initiate the election algorithm. Here's a breakdown of the code:

// Bully Class: Represents the Bully algorithm and contains methods for process manipulation and election.
// Main Method: Provides a menu-driven interface for users to interact with the Bully algorithm.
// Methods:
// displayProcesses(): Displays the status of each process.
// upProcess(int process_id): Brings a process up.
// downProcess(int process_id): Brings a process down.
// runElection(int process_id): Initiates the election algorithm.
// User Input Handling: Allows users to choose options from the menu to interact with the algorithm.
// Recursion: Util