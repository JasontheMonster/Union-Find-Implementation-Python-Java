/**
 * 
 */
package Tests;
import Implementation.DisjointSet;
import Implementation.UF;
/**
 * @author Jason
 *
 */
public class Correctness {

	/**Test for correctness of slow version; path compressed version and union by rank
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test for slow version correctness 
		//sample output should be:
		//Yes, No
		int n = 5;
	     DisjointSet dSet = new DisjointSet(n);
	 
	     // 0 is a friend of 2
	     dSet.union(0, 2);
	 
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
	    
	    //test for path compressed + union by rank
	    //sample output Yes, No
	    DisjointSet fast = new DisjointSet(n);
	    fast.union_by_rank(0, 2);
	    fast.union_by_rank(4, 2);
	    fast.union_by_rank(3, 1);
	    UF answer = new UF(n);
	    answer.union(0,  2);
	     answer.union(4,  2);
	     answer.union(3,  1);
	     
	     if (fast.pathCompression_find(0) == answer.find(2)){
	    	 System.out.println("Yes");
	     }else{
	    	 System.out.println("No"); 
	     }
	     if (fast.pathCompression_find(1) == answer.find(2)){
	    	 System.out.println("Yes");
	     }else{
	    	 System.out.println("No"); 
	     }

	}

}
