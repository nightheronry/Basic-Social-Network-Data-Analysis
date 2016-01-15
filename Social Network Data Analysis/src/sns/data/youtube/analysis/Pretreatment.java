package sns.data.youtube.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.management.monitor.StringMonitor;

import edu.uci.ics.jung.algorithms.metrics.Metrics;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.algorithms.scoring.DegreeScorer;
import edu.uci.ics.jung.algorithms.cluster.WeakComponentClusterer;
public class Pretreatment {
	
	int Nodes = 0;
	int Edges = 0;  
	String file_path = null;
	Graph<String, Number> graph = new SparseMultigraph<String, Number>();
	
	
	public Pretreatment(String path, String command) {
		// TODO Auto-generated constructor stub
		if  (path!=null){
			file_path = path; 
			doPretreatment(0);
			
		    }
		else 
			System.out.print("The file cannot be recognized");
		   
	} 
	
	public Pretreatment(String path, int time) {
		// TODO Auto-generated constructor stub
		if  (path!=null){
			file_path = path; 
			doPretreatment(time);
			
		    }
		else 
			System.out.print("The file cannot be recognized");
		   
	} 
	
	public void doPretreatment(int times){
		try {
		       File file = new File(file_path);
		       Scanner scanner = new Scanner(file);
		       Integer temp = null;
		       boolean check=true;
		       int timer=0;
		       for(int i=0; i<times+1; i++)
		       {   
		    	   ///////////////讀取#的檔案
		    	   /*
		    	   while(check)
		    	   {
			    	   if (scanner.hasNext("#"))
			    	   {
			    		   scanner.next();
			    		   if (scanner.next().contentEquals("Nodes:"))
			        	   {
			    			   Nodes = scanner.nextInt();
			    			   System.out.println("Nodes: "+ Nodes ); 
			    			   if (scanner.next().contentEquals("Edges:"))
			    			   {   
			    				   Edges = scanner.nextInt();
			    				   System.out.println("Edges: "+Edges); 
			    			   }
			        	   }
			    		   scanner.nextLine();
			    	   }else{ check=false;
			    	   
			    	   }
			    	     
		    	   } 
		    		   */
	    		   if(times == 0)//若time是0則讀完所有資料
	    		   { 
				    	 while(scanner.hasNext()){
				    		 String v1= scanner.next();
				    		 
				    		 String v2= scanner.next();
				    		 
				    		 if(graph.findEdge(v2,v1)==null && graph.findEdge(v1,v2)==null){
				    		    graph.addEdge(timer, v1, v2, EdgeType.UNDIRECTED);
				    		    timer++;
				    		   }
				    		 scanner.next();
				    	 }
	    		    }
	    		    else{//不是0則只讀前time行的資料(測試用)
	    		    	 String v1= scanner.next();
			    		 
			    		 String v2= scanner.next();
			    		 if(graph.findEdge(v2,v1)==null && graph.findEdge(v1,v2)==null){
			    		 graph.addEdge(timer, v1, v2, EdgeType.UNDIRECTED);
			    		 timer++;
			    		 }
			    		 scanner.next();
			    		 
	    		    }
 	        }
             scanner.close();
		           
		     }catch (FileNotFoundException e){
		             e.printStackTrace();
		            }
		
	}
	public void printGraph() {
		System.out.println(graph);
	}
	public void printEdgeCount() {
		System.out.println("EdgeCount: "+graph.getEdgeCount());
	}
	public void printVertexCount() {
	    System.out.println("VertexCount: "+ graph.getVertexCount());
	}
	
	public void printAverageDegree() {

		Iterator<?> iterator =  graph.getVertices().iterator();
		double temp=0;
		
		while(iterator.hasNext())
		     {
			    temp+=graph.degree((String)iterator.next());
		     }
		
		System.out.println("AverageDegree: "+ temp/graph.getVertexCount());
		
	}
	
	public void AverageClusteringCoefficient(){
	
		
		double temp=0;
        HashMap clusteringCoefficients = (HashMap) Metrics.clusteringCoefficients(graph);
        Collection collection = clusteringCoefficients.values();
        Iterator iterator = collection.iterator();
        
		while(iterator.hasNext())
		{   
		    temp += (double)iterator.next();
		}
		System.out.println("AverageClusteringCoefficient: "+ temp/graph.getVertexCount());
		
	}
	public void printDimiter(){
		/*
		 * try {
			
			DijkstraDistance dijkstraDistance = new DijkstraDistance(graph);
			double Diameter = 0.0;
			Map<String,Number> temp = null ;
			for(Iterator vertexIter = graph.getVertices().iterator(); vertexIter.hasNext();){
			temp = dijkstraDistance.getDistanceMap(vertexIter.next());
				
			
		    }
			System.out.println("Diameter= "+ temp);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/
		System.out.println("Diameter: "+DistanceStatistics.diameter(graph));
	}
	
	public void printDegreeDisbution(){
		HashMap<Integer, Integer> distbutionList = new HashMap<Integer, Integer>();
		DegreeScorer ranker = new DegreeScorer(graph);
		Iterator iterator = graph.getVertices().iterator();
		while(iterator.hasNext()){
			
			int temp = ranker.getVertexScore(iterator.next());
			
			if(distbutionList.get(temp)!=null)
			   distbutionList.replace(temp, distbutionList.get(temp)+1);
			else
			   distbutionList.put(temp, 1);
				 
		}
		List<Map.Entry<Integer, Integer>> list_Data =
	            new ArrayList<Map.Entry<Integer, Integer>>(distbutionList.entrySet());
		Collections.sort(list_Data, new Comparator<Map.Entry<Integer, Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return (o1.getKey().compareTo(o2.getKey()));
			}
        });
        for (Map.Entry<Integer, Integer> entry:list_Data) {
        	//System.out.println(entry.getKey()+ ": " + distbutionList.get(entry.getKey()));
        	System.out.println(entry.getKey() );
        	
        	
        }
        System.out.println("-------------------------------------------");
        for (Map.Entry<Integer, Integer> entry:list_Data) {
        	
        	
        	System.out.println(distbutionList.get(entry.getKey()));
        }
        
		
	}
	public void getClusteringCoefficientMN() {
        
        double clusteringCoefficientMN = 0;
        
        for (Iterator vertexIter = graph.getVertices().iterator(); vertexIter.hasNext();) {
        	double numTriangles = 0;
            double numConnectedTriples = 0;
        	String vet = (String) vertexIter.next();
            int numNeighbors = graph.degree(vet);
            numConnectedTriples += numNeighbors * (numNeighbors - 1) / 2;
            ArrayList neighbors = new ArrayList(graph.getNeighbors(vet));
            for (int i = 0; i < numNeighbors; ++i) {
            	String v1 = (String) neighbors.get(i);
                for (int j = 0; j < numNeighbors; ++j) {
                	String v2 = (String) neighbors.get(j);
                    if (graph.isNeighbor(v1, v2))
                        ++numTriangles;
                }
            }
            //System.out.println((double) (numTriangles/2) / numConnectedTriples);
            clusteringCoefficientMN += (double) (numTriangles/2) / numConnectedTriples;
        }
        
        System.out.println("AverageClusteringCoefficient="+(double)(clusteringCoefficientMN/graph.getVertexCount()));
        
    }
	public void getNetworkResilience(){
		Graph<String, Number> graphtemp = graph;
		WeakComponentClusterer<String, Number> wekC = new WeakComponentClusterer();
		int verticesAccount = graphtemp.getVertexCount();
		HashMap<String, Integer> DegreeList = new HashMap<String, Integer>();
		DegreeScorer ranker = new DegreeScorer(graphtemp);
		Iterator iterator = graph.getVertices().iterator();
		
        while(iterator.hasNext()){
        	String vtemp =  iterator.next().toString();
			int temp = ranker.getVertexScore(vtemp);
			DegreeList.put(vtemp, temp);
        }
        List<Map.Entry<String, Integer>> list_Data =
                new ArrayList<Map.Entry<String, Integer>>(DegreeList.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> entry1,
                               Map.Entry<String, Integer> entry2){
                return (entry2.getValue() - entry1.getValue());
            }
        });
        int timer2 = 1;
        Set<Set<String>> set = new HashSet<>();
        for (Map.Entry<String, Integer> entry:list_Data) {
        	//System.out.println(entry.getKey() + " " + DegreeList.get(entry.getKey()));
        	graphtemp.removeVertex(entry.getKey());
        	set = wekC.transform(graphtemp);
        	int maxComponent = 0;
        	Iterator iterator2 = set.iterator();
        	while(iterator2.hasNext()){
        		Set temp = (Set) iterator2.next();
        		
        		if(temp.size() > maxComponent)
        			maxComponent = temp.size();
        	}
        	//System.out.println(timer2 +" "+  maxComponent);
        	System.out.println(maxComponent);
        	timer2++;
        }
        
        
    }

		
	
}
