import mpi.*;
import java.util.Scanner;

public class ArrSum {
    public static void main(String[] args) throws Exception {
        // Initialize MPI
        MPI.Init(args);

        // Get rank and size of the current process
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        // Define variables for data distribution
        int unitsize = 3; // Number of elements each process handles
        int root = 0; // Rank of the root process
        int send_buffer[] = null; // Array to hold data to be sent
        send_buffer = new int[unitsize * size]; // Initialize send_buffer
        int receive_buffer[] = new int[unitsize]; // Array to hold received data
        int new_receive_buffer[] = new int[size]; // Array to store aggregated results

        // Set data for distribution
        if (rank == root) {
            // If root process, prompt user to enter elements
            int total_elements = unitsize * size;
            System.out.println("Enter " + total_elements + " elements");
            for (int i = 0; i < total_elements; i++) {
                // Store entered elements in send_buffer
                System.out.println("Element " + i + "\t = " + i);
                send_buffer[i] = i;
            }
        }

        // Scatter data to processes
        MPI.COMM_WORLD.Scatter(
                send_buffer, // Send buffer
                0, // Starting index of send buffer
                unitsize, // Number of elements to send to each process
                MPI.INT, // Data type of elements
                receive_buffer, // Receive buffer
                0, // Starting index of receive buffer
                unitsize, // Number of elements to receive
                MPI.INT, // Data type of elements
                root // Rank of root process
        );

        // Calculate sum at non-root processes and store result in first index of array
        for (int i = 1; i < unitsize; i++) {
            receive_buffer[0] += receive_buffer[i];
        }
        System.out.println(
                "Intermediate sum at process " + rank + " is " + receive_buffer[0]
        );

        // Gather data from processes
        MPI.COMM_WORLD.Gather(
                receive_buffer, // Send buffer
                0, // Starting index of send buffer
                1, // Number of elements to send from each process
                MPI.INT, // Data type of elements
                new_receive_buffer, // Receive buffer
                0, // Starting index of receive buffer
                1, // Number of elements to receive from each process
                MPI.INT, // Data type of elements
                root // Rank of root process
        );

        // Aggregate output from all non-root processes
        if (rank == root) {
            int total_sum = 0;
            for (int i = 0; i < size; i++) {
                total_sum += new_receive_buffer[i];
            }
            System.out.println("Final sum : " + total_sum);
        }

        // Finalize MPI
        MPI.Finalize();
    }
}



// In MPI (Message Passing Interface) programming, MPI.COMM_WORLD is a predefined communicator representing 
// the group of all processes in the MPI program. It's essentially a handle that allows processes to 
// communicate and coordinate with each other.




// Import Statements:
// import mpi.*: Imports the MPI classes necessary for MPI programming.
// import java.util.Scanner: Imports the Scanner class for user input.
// Main Method:
// The main method is the entry point of the program.
// MPI Initialization and Finalization:
// MPI.Init(args): Initializes MPI with command line arguments.
// MPI.Finalize(): Finalizes MPI at the end of the program.
// Rank and Size:
// int rank = MPI.COMM_WORLD.Rank(): Gets the rank (identifier) of the current process.
// int size = MPI.COMM_WORLD.Size(): Gets the total number of processes in the MPI program.
// Data Distribution:
// int unitsize = 3;: Defines the number of elements each process handles.
// int root = 0;: Defines the rank of the root process.
// int send_buffer[]: Array to hold data to be sent.
// int receive_buffer[]: Array to hold received data.
// int new_receive_buffer[]: Array to store aggregated results.
// User Input (Root Process):
// If the current process is the root process (rank 0), it prompts the user to enter elements and stores them in send_buffer.
// Scatter Operation:
// MPI.COMM_WORLD.Scatter(): Distributes data from the root process to all processes using scatter operation.
// Calculation (Non-Root Processes):
// Non-root processes calculate the sum of their received elements and store it in the first index of receive_buffer.
// Print Intermediate Sums:
// Each process prints its intermediate sum.
// Gather Operation:
// MPI.COMM_WORLD.Gather(): Gathers data from all processes to the root process using gather operation.
// Aggregate Output:
// The root process aggregates the received sums from all processes and prints the final sum.
// Finalization:
// MPI.Finalize(): Finalizes MPI at the end of the program.