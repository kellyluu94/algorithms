// Client. Write a client program Permutation.java that takes an integer k as a command-line argument; reads a sequence of strings from standard input using StdIn.readString(); and prints exactly k of them, uniformly at random. Print each item from the sequence at most once.

// Command-line argument.  You may assume that 0 ≤ k ≤ n, where n is the number of string on standard input. Note that you are not given n.

// Performance requirements.  The running time of Permutation must be linear in the size of the input. You may use only a constant amount of memory plus either one Deque or RandomizedQueue object of maximum size at most n. (For an extra challenge and a small amount of extra credit, use only one Deque or RandomizedQueue object of maximum size at most k.)

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
      // if (args.length != 2) {
      //    System.out.println("Needs 2 argements");
      //    return;
      // };

      int k = Integer.parseInt(args[0]);
      if (k < 0) {
         System.out.println("k must be non-negative");
         return;
      }

      RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

      while (!StdIn.isEmpty()) {
         String item = StdIn.readString();
         randomizedQueue.enqueue(item);
      }

      for (int i = 0; i < k; i++) {
         System.out.println(randomizedQueue.dequeue());
      }
   }
}