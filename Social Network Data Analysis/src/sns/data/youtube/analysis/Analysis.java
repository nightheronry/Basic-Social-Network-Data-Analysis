package sns.data.youtube.analysis;

import java.io.*;
import java.lang.String;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;


public class Analysis{
	  
    static String file_path="./data/NodeFromTo.txt";//資料路徑
		  
	public static void main(String args[])throws IOException
      {
		
		
		Pretreatment snsPretreatment = new Pretreatment(file_path, "DO_OTHER");
		
		//snsPretreatment.printGraph();//print出graph的結構
		
		
		
		 //snsPretreatment.printEdgeCount();
		 //snsPretreatment.printVertexCount();
		 //snsPretreatment.printAverageDegree();
		 //snsPretreatment.printDimiter();
		 //snsPretreatment.getClusteringCoefficientMN();
		 //snsPretreatment.printDegreeDisbution();
		 //snsPretreatment.getNetworkResilience();
		 //GraphVitulization2D twodView = new GraphVitulization2D(snsPretreatment.graph);
		
		 //GraphVitulization threedView = new GraphVitulization(snsPretreatment.graph);

    }
}
