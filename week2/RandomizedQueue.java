import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
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
        if (size == array.length) {
            resize(2 * array.length);
        }
        array[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        int index = StdRandom.uniformInt(size);
        Item item = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        int index = StdRandom.uniformInt(size);
        Item item = array[index];
        return item;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = 0;
        private final Item[] randomizedItems;

        public RandomizedQueueIterator() {
            randomizedItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomizedItems[i] = array[i];
            }
            StdRandom.shuffle(randomizedItems);
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more items to return");
            }
            Item item = randomizedItems[current];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> deque = new RandomizedQueue<>();
        System.out.println("Is deque empty? " + deque.isEmpty());
        deque.enqueue(1);
        deque.enqueue(2);
        System.out.println("Is deque empty? " + deque.isEmpty());
        deque.enqueue(3);
        deque.enqueue(4);
        System.out.println("What is the size of deque? " + deque.size());
        deque.enqueue(5);
        deque.enqueue(6);
        System.out.println("Remove " + deque.dequeue());
        System.out.println("Pick a random element " + deque.sample());
        System.out.println("Deque items from front to back:");
        for (Integer item : deque) {
            System.out.println(item);
        }
    }
}