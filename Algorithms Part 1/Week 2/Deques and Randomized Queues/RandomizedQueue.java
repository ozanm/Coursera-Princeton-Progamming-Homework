import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private int[] order;

        public RandomizedQueueIterator() {
            i = 0;
            order = new int[n];
            for (int j = 0; j < n; j++) {
                order[j] = j;
            }

            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return a[order[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() operation not available in RandomizedQueueIterator.");
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) (new Object[2]);
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item to enqueue cannot be NULL.");

        if (n == a.length)
            resize(a.length * 2);
        else if (n == 0) {
            a[n++] = item;
            return;
        }

        int i = StdRandom.uniform(n);
        Item temp = a[i];
        a[i] = item;
        a[n++] = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty.");

        if (n == a.length / 4)
            resize(a.length / 2);

        int i = StdRandom.uniform(n);
        Item item = a[i];
        a[i] = a[--n];
        a[n] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty.");

        return a[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
          RandomizedQueue<Integer> queue = new RandomizedQueue<>();

          for (int i = 0; i < 10; i++)
              queue.enqueue(i);
          System.out.println(queue.size());
          for (Integer i : queue)
              System.out.println(i);

          System.out.println("sample: " + queue.sample());
          System.out.println("dequeue");

          while (!queue.isEmpty())
              System.out.println(queue.dequeue());
          System.out.println(queue.size());
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
}
