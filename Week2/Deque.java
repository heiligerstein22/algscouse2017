import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node first = null;
	private Node last = null;
	private int size = 0;

	// construct an empty deque
	public Deque() {
	}

	private class Node {
		Item item;
		Node prev;
		Node next;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {

		if (item == null)
			throw new java.lang.IllegalArgumentException();

		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;

		if (isEmpty())
			last = first;
		else 
			oldFirst.prev = first;

		size++;

	}

	// add the item to the end
	public void addLast(Item item) {

		if (item == null)
			throw new java.lang.IllegalArgumentException();

		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.prev = oldLast;
		// last.next = null;

		if (isEmpty())
			first = last;
		else 
			oldLast.next = last;

		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {

		if (isEmpty())
			throw new java.util.NoSuchElementException();

		Item item = first.item;
		first = first.next;
		size--;

		if (isEmpty())
			last = null;

		return item;
	}

	// remove and return the item from the end
	public Item removeLast() {

		if (this.isEmpty())
			throw new java.util.NoSuchElementException();

		Item item = last.item;
		last = last.prev;
		size--;

		if (isEmpty())
			first = null;

		return item;
	}


	// return an iterator over items in order from front to end	
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterable<Item> {

		private Node current = first;

		public boolean hasNext() {
			return current != null; 
		}

		// disable method
		public void remove() { 
			throw new java.lang.UnsupportedOperationException(); 
		}

		public Item next() {
			Item item = current.item;	// save
			current = current.next;		// walk
			return item;
		}

	}



	// unit testing (optional)
	public static void main(String[] args) {

		/*

		Deque<String> myDeque = new Deque<String>();
		myDeque.addFirst("a");
		myDeque.addFirst("b");
		myDeque.addFirst("c");
		myDeque.addFirst("d");

		for(String item : myDeque) {
			System.out.println(""+item);
		}

		for(String item : myDeque) {
			System.out.println(""+item);
		}

		System.out.printf(myDeque.removeLast()+"\n");
		System.out.printf(myDeque.isEmpty()+"\n");
		System.out.printf(myDeque.size()+"\n");
		System.out.printf(myDeque.removeLast()+"\n");
		System.out.printf(myDeque.isEmpty()+"\n");
		System.out.printf(myDeque.size()+"\n");
		System.out.printf(myDeque.removeLast()+"\n");
		System.out.printf(myDeque.isEmpty()+"\n");
		System.out.printf(myDeque.size()+"\n");
		System.out.printf(myDeque.removeLast()+"\n");
		System.out.printf(myDeque.isEmpty()+"\n");
		System.out.printf(myDeque.size()+"\n");
		System.out.printf(myDeque.removeFirst()+"\n");
		System.out.printf(myDeque.isEmpty()+"\n");
		System.out.printf(myDeque.size()+"\n");
		*/
	}

}
