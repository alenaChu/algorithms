package queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by loster on 30.10.15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size = 0;

    public RandomizedQueue() {                // construct an empty randomized queue
        array = (Item[]) new Object[2];
    }

    public boolean isEmpty() {               // is the queue empty?
        return size == 0;
    }

    public int size() {                        // return the number of items on the queue
        return size;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void enqueue(Item item) {          // add the item
        if (array.length == size) resize(2 * size);
        array[size] = item;
        size++;
    }

    private void shuffle(Item[] a) {
        StdRandom.shuffle(a);
    }

    public Item dequeue() {                   // remove and return a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (array.length > 4 * size) {
            resize(2 * size);
        }
        int num = StdRandom.uniform(size);
        Item item = array[num];
        array[num] = array[size - 1];
        array[size - 1] = null;
        size--;

        return item;
    }

    public Item sample() {                     // return (but do not remove) a random item
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return array[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new RandQIterator();
    }

    private class RandQIterator implements Iterator<Item> {
        private int current;
        private Item [] copyArray;
        public RandQIterator(){
       //     System.out.println("Constructor");
            current = 0;
            copyArray = (Item []) new Object [size];

            for (int i = 0; i < size; i++ ){
                copyArray [i] = array[i];
            }
            shuffle(copyArray);
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
       //     System.out.println("next, current = " + current);

            if (current >= size) {
                throw new java.util.NoSuchElementException();
            }

            Item item = copyArray[current];
            current++;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {  // unit testing
    }
}
