// Randomized queue.
// A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random among items in the data structure.

// Iterator.
// Each iterator must return the items in uniformly random order. The order of two or more iterators to the same randomized queue must be mutually independent; each iterator must maintain its own random order.

// Unit testing.  Your main() method must call directly every public constructor and method to verify that they work as prescribed (e.g., by printing results to standard output).

// Performance requirements.  Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time. That is, any intermixed sequence of m randomized queue operations (starting from an empty queue) must take at most cm steps in the worst case, for some constant c. A randomized queue containing n items must use at most 48n + 192 bytes of memory. Additionally, your iterator implementation must support operations next() and hasNext() in constant worst-case time; and construction in linear time; you may (and will need to) use a linear amount of extra memory per iterator.

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must be non-null");
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = last;
        if (last != null) {
            last.zznext = newNode;
        } else {
            first = newNode;
        }
        last = newNode;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more items to return");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation not supported");
        }

    }

    // unit testing (required)
    public static void main(String[] args)

}