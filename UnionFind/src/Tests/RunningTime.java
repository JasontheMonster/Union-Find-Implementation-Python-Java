/**
 * 
 */
package Tests;
import java.util.Random;

import Implementation.DisjointSet;
import Implementation.UF;
/**
 * @author Jason
 *
 */
public class RunningTime {

	/**Measure running time for n = 10000, 100, 10000 and k = 10, 100, 10000
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 1000000;
		int seed = 1;
		DisjointSet myUnion = new DisjointSet(n);
		UF answer = new UF(n);
		double startTime = System.currentTimeMillis();
		for (int i = 0; i <n; i++){
			Random generator = new Random(seed);
			int random1 = generator.nextInt(n);
			int random2 = generator.nextInt(n);
			int random3 = generator.nextInt(n);
			myUnion.union_by_rank(random1, random2);
			myUnion.pathCompression_find(random3);
			seed ++;
			
		}
		double endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		// TODO Auto-generated method stub

	}

}
