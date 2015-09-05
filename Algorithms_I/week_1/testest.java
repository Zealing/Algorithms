import java.lang.reflect.Array;


public class testest {

	private int id[] = new int[]{10,11,12};
	
	private void union (int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i <id.length; i++) {
			if (id[i] == id[p]) {
				id[i] = qid;
			}
		}
		for (int i = 0; i <id.length; i++) {
			System.out.println(id[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new testest().union(1, 2);
	}

}
