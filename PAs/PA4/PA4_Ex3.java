package PA4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class PA4_Ex3 {
    // Author: Aiden Wang

    // Helper class for OS Jobs
    static class Job {
        int id;
        int units;
        Job(int id, int units) {
            this.id = id;
            this.units = units;
        }
    }

    // Custom Linked Queue Implementation (No ADT allowed for list internals, just Node)
    static class Node {
        Job data;
        Node next;
        Node(Job data) {
            this.data = data;
            this.next = null;
        }
    }

    static class MyLinkedQueue {
        private Node head = null;
        private Node tail = null;

        public void enqueue(Job j) {
            Node newNode = new Node(j);
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        public Job dequeue() {
            if (isEmpty()) return null;
            Job res = head.data;
            head = head.next;
            if (head == null) tail = null;
            return res;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Modified by : Aiden Wang\n");

        // ---------------------------------------------------------
        // 1. Simulation using MyLinkedQueue (Hardcoded 2, 1, 2, 1, 1)
        // ---------------------------------------------------------
        // Strictly matching the teacher's sample output format
        System.out.println("Using MyLinkedQueue with 2, 1, 2, 1, 1.");

        MyLinkedQueue myQueue = new MyLinkedQueue();
        int[] hardcoded = {2, 1, 2, 1, 1};

        for (int i = 0; i < 5; i++) {
            myQueue.enqueue(new Job(i + 1, hardcoded[i]));
            // Strictly matching: "Enqueue job 1 with 2 units"
            System.out.println("Enqueue job " + (i + 1) + " with " + hardcoded[i] + " units");
        }

        System.out.println(); // Blank line for readability

        int cycle = 1;
        while (!myQueue.isEmpty()) {
            Job currentJob = myQueue.dequeue();
            // Strictly matching: Label each processing cycle and job number
            System.out.println( cycle + " Processing job " + currentJob.id);

            currentJob.units--; // Serve it for 1 unit

            if (currentJob.units > 0) {
                myQueue.enqueue(currentJob); // Still needs time, put back in queue
            } else {
                System.out.println("Done with job " + currentJob.id);
            }
            cycle++;
        }

        System.out.println("\n-------------------------------------------------\n");

        // ---------------------------------------------------------
        // 2. Simulation using java.util.LinkedList (Random numbers)
        // ---------------------------------------------------------
        System.out.println("Modified by : Aiden Wang\n");
        System.out.println("Using Java LinkedList Queue with random numbers.");
        Queue<Job> javaQueue = new LinkedList<>();
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            int randomUnits = rand.nextInt(5) + 1; // Generate random units (1 to 5)
            javaQueue.add(new Job(i + 1, randomUnits));
            System.out.println("Enqueue job " + (i + 1) + " with " + randomUnits + " units");
        }

        System.out.println(); // Blank line for readability

        cycle = 1;
        while (!javaQueue.isEmpty()) {
            Job currentJob = javaQueue.remove();
            System.out.println( cycle + " Processing job " + currentJob.id);

            currentJob.units--; // Serve it for 1 unit

            if (currentJob.units > 0) {
                javaQueue.add(currentJob);
            } else {
                System.out.println("Done with job " + currentJob.id);
            }
            cycle++;
        }
    }
}