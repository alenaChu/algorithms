package queues;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by loster on 1.11.15.
 */
public class TestRandomizedQueue {
    @Test
    public void testEnque(){
        int N = 5;
        RandomizedQueue <Integer> randomQueue = new RandomizedQueue();
        for (int i = 0; i<N; i++) {
            randomQueue.enqueue(i);
        }
        for (int i = N; i>0; i--) {
            randomQueue.dequeue();
//            System.out.println(randomQueue.dequeue());
            assertEquals(randomQueue.size(), i - 1);
        }
    }
    @Test
    public void testSample(){
        int N = 5;
        RandomizedQueue <Integer> randomQueue = new RandomizedQueue();
        for (int i = 0; i<N; i++) {
            randomQueue.enqueue(i);
        }
        for (int i = 0; i<N; i++) {
            randomQueue.sample();
//            System.out.println(randomQueue.sample());
            assertEquals(randomQueue.size(), N);
        }
    }
    @Test
    public void testIterator(){
        int N = 3;
        RandomizedQueue <Integer> randomQueue = new RandomizedQueue<>();
        for (int i = 0; i<N; i++) {
            randomQueue.enqueue(i);
        }
        System.out.println("loop start");
        for (Integer s: randomQueue ) {
            System.out.println(s);
            for (Integer p : randomQueue) {
                System.out.println("    " + p);
            }
        }
    }

}

