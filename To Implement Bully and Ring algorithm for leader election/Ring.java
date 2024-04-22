import java.util.Scanner;

public class Ring {
    public static void main(String[] args) {
        int temp, i, j;
        char str[] = new char[10];
        Rr proc[] = new Rr[10];
        // object initialization
        for (i = 0; i < proc.length; i++)
            proc[i] = new Rr();
        // scanner used for getting input from console
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of process : ");
        int num = in.nextInt();
        // getting input from users
        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.println("Enter the id of process : ");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // sorting the processes on the basis of id
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        for (i = 0; i < num; i++) {
            System.out.print(" [" + i + "] " + "" + proc[i].id);
        }

        int init;
        int ch;
        int temp1;
        int temp2;
        int ch1;
        int arr[] = new int[10];
        proc[num - 1].state = "inactive";
        System.out.println("\n process " + proc[num - 1].id + " select as co-ordinator");

        while (true) {
            System.out.println("\n 1.election 2.quit ");
            ch = in.nextInt();
            for (i = 0; i < num; i++) {
                proc[i].f = 0;
            }
            switch (ch) {
                case 1:
                    System.out.println("\n Enter the Process number who initialized election : ");
                    init = in.nextInt();
                    temp2 = init;
                    temp1 = init + 1;
                    i = 0;
                    while (temp2 != temp1) {
                        if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {
                            System.out.println("\nProcess " + proc[init].id + " send message to " + proc[temp1].id);
                            proc[temp1].f = 1;
                            init = temp1;
                            arr[i] = proc[temp1].id;
                            i++;
                        }
                        if (temp1 == num) {
                            temp1 = 0;
                        } else {
                            temp1++;
                        }
                    }
                    System.out.println("\nProcess " + proc[init].id + " send message to " + proc[temp1].id);
                    arr[i] = proc[temp1].id;
                    i++;
                    int max = -1;

                    // finding maximum for co-ordinator selection
                    for (j = 0; j < i; j++) {
                        if (max < arr[j]) {
                            max = arr[j];
                        }
                    }
                    // co-ordinator is found then printing on console
                    System.out.println("\n process " + max + " select as co-ordinator");
                    for (i = 0; i < num; i++) {
                        if (proc[i].id == max) {
                            proc[i].state = "inactive";
                        }
                    }
                    break;
                case 2:
                    System.out.println("Program terminated ...");
                    return ;
                default:
                    System.out.println("\n invalid response \n");
                    break;
            }
        }
    }
}

class Rr {
    public int index; // to store the index of process
    public int id; // to store id/name of process
    public int f;
    String state; // indicates whether active or inactive state of node
}


// The main method initializes an array of processes (proc[]) and then gets input from the user regarding the number of processes and their IDs.
// The processes are sorted based on their IDs to ensure that they are arranged in ascending order.
// The program simulates an election process where each process sends messages to the next process in the ring until the coordinator is elected.
// The Rr class represents a process with attributes like index, ID, and state.
// The program continuously prompts the user for options such as initiating an election, quitting the program, etc., until the user decides to quit.


// Ring Algorithm: This algorithm is used for leader election in a distributed system where processes are arranged in a logical ring. Each process sends an election message to the next process in the ring until the process with the highest ID becomes the leader.
// Process State: Each process has a state attribute indicating whether it is active or inactive. This state changes during the election process based on whether a process is participating in the election or not.
// User Input: The program takes user input to determine the number of processes, their IDs, and other actions like starting an election or quitting the program.