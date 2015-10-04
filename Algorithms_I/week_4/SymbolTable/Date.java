package SymbolTable;

public class Date implements Comparable<Date>{

	private final int year;
	private final int month;
	private final int day;
	
	public Date(int year, int month, int day) {
		// TODO Auto-generated constructor stub
		this.year = year;
		this.month = month;
		this.day = day;
	}

	//equals method in every JAVA class
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		
		if (y.getClass() != this.getClass()) return false;
		
		Date that = (Date) y;
		if (this.year != that.year) 	return false;
		if (this.month != that.month) 	return false;
		if (this.day != that.day) 		return false;
		
		return true;
	}
	
	@Override
	public int compareTo(Date o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
