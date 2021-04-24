import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int n;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeueIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() operation not available in DequeueIterator.");
        }
    }

    // construct an empty deque
    public Deque() {
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Object is NULL, cannot be added to Queue.");

        Node current = first;
        first = new Node();
        first.item = item;
        first.next = current;
        if (last == null)
            last = first;
        else
            current.prev = first;

        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
          if (item == null)
              throw new IllegalArgumentException("Object is NULL, cannot be added to Queue.");

          Node current = last;
          last = new Node();
          last.item = item;
          last.prev = current;
          if (first == null)
              first = last;
          else
              current.next = last;

          n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty.");

        Item item = first.item;

        n--;

        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty.");

        Item item = last.item;

        n--;

        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

      Deque<Integer> deque = new Deque<>();
      deque.addFirst(1);
      deque.removeLast();
      System.out.println(deque.isEmpty());
      for (int i = 0; i < 10; i++) {
          deque.addFirst(i);
          deque.addLast(i * 10);
      }
      System.out.println(deque.removeLast());
      System.out.println(deque.size());
      while (!deque.isEmpty())
          System.out.println(deque.removeFirst());
      deque.addFirst(1);
      System.out.println(deque.removeFirst());
      deque.addFirst(2);
      System.out.println(deque.removeFirst());
      deque.addLast(0);
      deque.removeFirst();
      deque.addLast(3);
      deque.addLast(4);
      deque.removeFirst();
      deque.removeLast();
      deque.addFirst(8);
    }

}
