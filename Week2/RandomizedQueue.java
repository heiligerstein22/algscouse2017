import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] queue;
	private int N = 0;

	// construct an empty randomized queue
	public RandomizedQueue() {
		queue = (Item[]) new Object[2];
	}					  

	// is the queue empty?
	public boolean isEmpty() {
		return N == 0;
	}					  

	// return the number of items on the queue
	public int size() {
		return N;
	}

	private void resize(int capacity) {

		Item[] copy = (Item[]) new Object[capacity];

		for (int i = 0; i < N; i++)
			copy[i] = queue[i];

		queue = copy;
	}

	// add the item
	public void enqueue(Item item) {

		if (item == null)
			throw new java.lang.IllegalArgumentException();

		// resize
		if (queue.length == N) 
			resize(2 * queue.length);

		queue[N++] = item;
	}

	// remove and return a random item
	public Item dequeue() {

		if (isEmpty())
			throw new java.util.NoSuchElementException();

		// int random = StdRandom.uniform(0, N);
		// Item item = queue[random];

		// Item item = queue[N-1];
		// N--;

		// swap resolve dequeue aleatorio
		int rand = StdRandom.uniform(N);
        Item swap = queue[rand];
        int tmpN = --N;
        queue[rand] = queue[tmpN];
        queue[tmpN] = null;
 
        if (N > 0 && N == queue.length/4)        // se o N chega em array/4
            resize(queue.length/2);
		
		return swap;
	}						  

	// return (but do not remove) a random item
	public Item sample() {
		return null;
	}							

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

        private int current = N;
		private Item[] queueCopy = queue;	// resolve 2 iterators

        public boolean hasNext() {
            return current > 0;
        }

        // disable method
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {

			if (!hasNext()) 
				throw new java.util.NoSuchElementException();

			int rand = StdRandom.uniform(current);
            Item swap = queueCopy[rand];
            queueCopy[rand] = queueCopy[--current];
            return swap;

        }

	}

	// unit testing (optional)
	public static void main(String[] args) {

		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

		// insert
		for (int i = 0; i < 10000; i++) {	
			rq.enqueue(i);
		}

		// iterate
        for(Integer item : rq) {
            System.out.println(""+item);
        }

		/*
		System.out.println("" + rq.dequeue() + " " + rq.size());
		System.out.println("" + rq.dequeue() + " " + rq.size());
		System.out.println("" + rq.dequeue() + " " + rq.size());
		System.out.println("" + rq.dequeue() + " " + rq.size());
		System.out.println("" + rq.dequeue() + " " + rq.size());
		System.out.println("" + rq.dequeue() + " " + rq.size());
		System.out.println("" + rq.dequeue() + " " + rq.size());
		*/

		// int random[] = StdRandom.permutation(10000000);
		// System.out.println("" + StdRandom.uniform(0, 10));
		// System.out.println("" + random[0]);
	}	

}
