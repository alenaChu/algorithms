package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by loster on 30.10.15.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return (first == null);
    }

    public int size() {                        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {          // add the item to the front
        if (item == null) throw new NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) oldfirst.prev = first;
        else last = first;
        size++;
    }

    public void addLast(Item item) {           // add the item to the end
        if (item == null) {
            throw new NullPointerException();
        }

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;                      //!!! if empty???
        last.prev = oldlast;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        size++;
    }

    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;
        if (!isEmpty()) {
            first.prev = null;
        }
        size--;

        return item;
    }

    public Item removeLast() {                // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        size--;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    public static void main(String[] args) {   // unit testing

    }
}

