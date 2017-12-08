package Implementation;
/**
 * 
 */

/**
 * @author Shikun Wang (Jason)
 *	Class of Disjoint Set data structure
 */
public class DisjointSet {
	private int n;//size
	private int [] uf; //disjoint set
	private int [] rank;//rank

	/**
	 * Constructor that initialized array of size n
	 * @param size
	 */
	public DisjointSet(int size){
		//create a array of size n that holds all nodes
		this.n = size;
		this.uf = new int [this.n];
		this.rank = new int [this.n];
		
		//initiallly all nodes' parents are themselves
		for (int i = 0; i<size; i++){
			this.uf[i] = i;
			this.rank[i] = 1;
		}
	}
	
	/**
	 * Find Function
	 * @return leader of x
	 */
	public int find(int x){
		if (this.uf[x] ==x) return x; //if current element is x's parent, return
		return find(this.uf[x]);	//recursively call 
	}
	/**
	 * Union Function
	 * @param x
	 */
	public void union(int x, int y){
		this.uf[find(y)] = find(x);	//put y under x leaders
	}
	
	/**
	 * path Compression version of find 
	 * @param x
	 * @return leader of x
	 */
	public int pathCompression_find(int x){
		//if the parent of x is not the parent of parent of x, set it to be parents of x
		if(this.uf[x] != this.uf[this.uf[x]]) this.uf[x] = pathCompression_find(this.uf[x]);
		return this.uf[x];
	}
	
	public boolean union_by_rank(int x, int y){
		//find the parent of x
		int xx = pathCompression_find(x);
		//find the parent of y
		int yy = pathCompression_find(y);
		// if they are the same, return false
		if (xx ==yy) return false;
		//if x has higher rank, set parent of y to be x's parent
		if (this.rank[xx] > this.rank[yy]){
			this.uf[yy] = xx; 
		//else, set parent of x to be y's parent
		}else if (this.rank[xx] < this.rank[yy]){
			this.uf[xx] = yy;
		}else{
		//else, randomly point y's parent to x's parent
			this.uf[yy] = xx;
		//increase the depth of tree by one
			this.rank[xx] ++;
		}
		return true;
	}
	
	/**
	 * get set of nodes
	 * @return
	 */
	public int [] getSet(){
		return this.uf;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int n = 5;
	     DisjointSet dSet = new DisjointSet(n);
	 
	     // 0 is a friend of 2
	     dSet.union(0, 0);
	 
	     // 4 is a friend of 2
	     dSet.union(4, 2);
	 
	     // 3 is a friend of 1
	     dSet.union(3, 1);
	 
	    // Check if 4 is a friend of 0
	     if (dSet.find(4)== dSet.find(0))
	          System.out.println("Yes");
	     else
	          System.out.println("No");
	 
	        // Check if 1 is a friend of 0
	        if (dSet.find(1) == dSet.find(0))
	            System.out.println("Yes");
	        else
	            System.out.println("No");
		

	}

}