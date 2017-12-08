/**
 * 
 */
package Tests;
import Implementation.DisjointSet;
import Implementation.UF;
import java.io.File;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author Jason
 *
 */
public class Performance {

	/**
	 * @param args
	 */
	public static void main( String[ ] args )throws Exception {
		
		ArrayList <Double> slowTime = new ArrayList <Double> ();
		ArrayList <Double> fastTime = new ArrayList <Double> ();
		ArrayList <Double> rank = new ArrayList <Double> ();
		ArrayList <Double> path = new ArrayList <Double> ();
		ArrayList <Double> nonrank = new ArrayList <Double> ();
		ArrayList <Double> nonpath = new ArrayList <Double> ();
		//comparing my slow version and my path compression and union by rank, both
		for (int i = 1; i<10000; i+=10){
			long sTime = 0;
			long fTime = 0;
			DisjointSet slow = new DisjointSet(i);
			DisjointSet fast = new DisjointSet(i);
			
			long startTime = System.currentTimeMillis();
			//slow union
			for (int j = 0; j<10000; j+=2){
				int random1 = (int )(Math.random() * i + 0);
				int random2 = (int )(Math.random() * i + 0);
				slow.union(random1, random2);
			}
			sTime += System.currentTimeMillis() - startTime; 
			nonrank.add((double) (System.currentTimeMillis() - startTime ));
			
			startTime = System.currentTimeMillis();
			//fast union
			for (int j = 0; j<10000; j+=2){
				int random1 = (int )(Math.random() * i + 0);
				int random2 = (int )(Math.random() * i + 0);
				fast.union_by_rank(random1, random2);
			}
			fTime += System.currentTimeMillis() - startTime; 
			rank.add((double) (System.currentTimeMillis() - startTime ));
			
			//slow find
			startTime = System.currentTimeMillis();
			for (int j = 0; j<10000; j+=2){
				int random2 = (int )(Math.random() * i + 0);
				slow.find(random2);
			}
			sTime += System.currentTimeMillis() - startTime; 
			nonpath.add((double) (System.currentTimeMillis() - startTime ));
			
			startTime = System.currentTimeMillis();
			//fast find
			for (int j = 0; j<10000; j+=2){
				int random2 = (int )(Math.random() * i + 0);
				fast.pathCompression_find(random2);
			}
			fTime += System.currentTimeMillis() - startTime; 
			nonpath.add((double) (System.currentTimeMillis() - startTime ));
			
			
			slowTime.add((double) sTime);
			fastTime.add((double) fTime);		
		}

	      final XYSeries overall = new XYSeries( "Path_Compression + Union_By_Rank");
	      for(int i = 1; i<slowTime.size(); i++){
	    	  System.out.println(slowTime);
	    	  overall.add(i, slowTime.get(i));
	      }
	      
	      
	      final XYSeriesCollection dataset = new XYSeriesCollection( );
	      dataset.addSeries( overall );


	      JFreeChart xylineChart = ChartFactory.createXYLineChart(
	         "Browser usage statastics", 
	         "Category",
	         "Score", 
	         dataset,
	         PlotOrientation.VERTICAL, 
	         true, true, false);
	      
	      int width = 640;   /* Width of the image */
	      int height = 480;  /* Height of the image */ 
	      File XYChart = new File( "XYLineChart.jpeg" ); 
	      ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
	   }

}
