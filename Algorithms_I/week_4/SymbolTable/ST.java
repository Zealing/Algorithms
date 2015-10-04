package SymbolTable;

public class ST<Key, Value> {

	public ST() {
		// TODO Auto-generated constructor stub
	}
	
	public void put(Key key, Value value) {
		
	}
	
	public Value get(Key key) {
		
	}
	
	public void delete(Key key) {
		put(key, null);
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public boolean isEmpty() {
		
	}
	
	public int size() {
		
	}
	
	public Iterable<Key> keys() {
		
	}

}
