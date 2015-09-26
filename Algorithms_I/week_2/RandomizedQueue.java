package Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int size;
	
	private class Node {
		Item item;
		Node next;
		Node previous;
		
		public Node() {
			
		}
		
		public Node(Item item) {
			this.item = item;
		}
	}
	
	public RandomizedQueue() {
		first = new Node();
		last = first;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException("null item!");
		}
		
		if (last.item == null) {
			last.item = item;
			first = last;
			size++;
		} else {
			Node temp = new Node(item);
			last.next = temp;
			temp.previous = last;
			last = temp;
			size++;
		}
	}
	
	public Item dequeue() {
		if (last == null) {
			throw new NoSuchElementException("empty queue right now!");
		}
		
		Item temp;
		int randomNum = (int) (Math.random() * size);
		
		int i = 0;
		Node current = first;
		while (i != randomNum) {
			current = current.next;
			i++;
		}
		
		temp = current.item;
		current.previous.next = current.next;
		current.next.previous = current.previous;
		size--;
		
		return temp;
	}
	
	public Item sample() {
		if (last == null) {
			throw new NoSuchElementException("empty queue right now!");
		}
		
		Item temp;
		int randomNum = (int) (Math.random() * size);
		
		int i = 0;
		Node current = first;
		while (i != randomNum) {
			current = current.next;
			i++;
		}
		
		temp = current.item;
		
		return temp;
	}
	
	private Node sampleNode() {
		int randomNum = (int) (Math.random() * size);
		
		int i = 0;
		Node current = first;
		while (i != randomNum) {
			current = current.next;
			i++;
		}
		
		return current;
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		RandomizedQueue<Item> irq;
		Node current;
		
		public RandomizedQueueIterator() {
			irq = new RandomizedQueue<Item>();
			
			irq.first = sampleNode();
			irq.last = irq.first;
			
			int i = 0;
			while (i != size) {
				irq.enqueue(sample());
				i++;
			}
			
			current = irq.first;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.next != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("unsupported Operation!");
		}
		
		@Override
		public Item next() {
			if (current.next == null) {
				throw new NoSuchElementException("no more items!");
			}
			
			Item item = current.item;
			current = current.next;
			
			return item;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue test = new RandomizedQueue();
		System.out.println(test.size);
		System.out.println(test.first.item);
		System.out.println(test.last.item);
		test.enqueue(1);
		System.out.println(test.size);
		System.out.println(test.first.item);
		System.out.println(test.last.item);
		test.enqueue(2);
		System.out.println(test.size);
		System.out.println(test.first.item);
		System.out.println(test.last.item);
	}
}
