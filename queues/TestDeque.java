package queues;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by loster on 1.11.15.
 */
public class TestDeque{
    @Test
    public void testAddFirst(){
        int N = 20;
        Deque <Integer> deq = new Deque <Integer>();
        for (int i = 0; i < N; i++ ) {
            deq.addFirst(i);
        }
        for (int i = 0; i < N; i++) {
            int a = deq.removeLast();
            assertEquals(a, i);
        }
    }
    @Test
    public void testAddLast(){
        int N = 20;
        Deque <Integer> deq = new Deque <Integer>();
        for (int i = 0; i < N; i++ ) {
            deq.addLast(i);
        }
        for (int i = 0; i < N; i++) {
            int a = deq.removeFirst();
            assertEquals(a, i);
        }
    }
    @Test
    public void testIterator(){
        int N = 5;
        Deque <Integer> deq = new Deque <Integer>();
        for (int i = 0; i < N; i++ ) {
            deq.addFirst(i);
        }
        for (Integer num : deq) {
            System.out.println(num);
        }
    }
}
