package queues;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by loster on 30.10.15.
 */
public class Subset {
    public static void main(String[] args) {
        int k = 0;
        RandomizedQueue<String> randomQueue = new RandomizedQueue();
        if (args.length > 0) {
            k = new Integer(args[0]);
        }

  /*      while (!StdIn.isEmpty()) {
            randomQueue.enqueue(StdIn.readString());
        }*/
        String a = StdIn.readString();
        while (!a.equals("|")) {
            randomQueue.enqueue(a);
            System.out.println(a + " size = " + randomQueue.size());
            a = StdIn.readString();
        }

        for (int i = 0; i < k; i++) {
            System.out.println(randomQueue.dequeue().toString());
        }
    }
}
