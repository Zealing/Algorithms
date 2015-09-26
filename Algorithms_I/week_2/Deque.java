package Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
	private int size;
	private Node top;
	private Node bottom;
	
	private class Node {
		public Node(Item item2) {
			// TODO Auto-generated constructor stub
			this.item = item2;
		}
		public Node() {
			// TODO Auto-generated constructor stub
		}
		Item item;
		Node next;
		Node previous;
	}
	
	public Deque() {
		top = new Node();
		bottom = top;
		size = 0;
	}
	
	public boolean isEmpty() {
		return top.item == null;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("null item");
		}
		
		if (top.item == null) {
			top.item = item;
			bottom = top;
			size++;
		} else {
		
			Node temp = new Node(item);
			temp.next = top;
			top.previous = temp;
			top = temp;
			size++;
		}
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("null item");
		}
		
		if (bottom.item == null) {
			bottom.item = item;
			top = bottom;
			size++;
		} else {
			Node temp = new Node(item);
			bottom.next = temp;
			temp.previous = bottom;
			bottom = temp;
			size++;
		}
	}
	
	public Item removeFirst() {
		if (top == null) {
			throw new NoSuchElementException("deque is empty right now!");
		}
		
		Item temp = top.item;
		top.next.previous = null;
		top = top.next;
		size--;
		
		return temp;
	}
	
	public Item removeLast() {
		if (bottom == null) {
			throw new NoSuchElementException("deque is empty right now!");
		}
		
		Item temp = bottom.item;
		bottom.previous.next = null;
		bottom = bottom.previous;
		size--;
		
		return temp;
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item>{

		private Node current = top;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.next != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException ("unsupported Operation!");
		}
		
		@Override
		public Item next() {
			if (current.next == null) {
				throw new NoSuchElementException("no more items!");
			}
			// TODO Auto-generated method stub
			Item item = current.item;
			current = current.next;
			
			return item;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque test = new Deque();
		System.out.println(test.top.next);
		test.addFirst(1);

		test.addFirst(2);

		test.addFirst(3);
		test.addFirst(4);
		test.addFirst(5);
		System.out.println(test.removeFirst());
		System.out.println(test.top.item);
		System.out.println(test.bottom.item);
		System.out.println(test.size);
	}
}
