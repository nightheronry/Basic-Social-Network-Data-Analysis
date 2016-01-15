package sns.data.youtube.analysis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class GraphVitulization2D extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graph<String, Number> demoGraph;
	//Graph<String, Number> oneComponentGraph = TestGraphs.getOneComponentGraph();
	Map<String, Graph<String, Number>> graphMap = new HashMap<String, Graph<String, Number>>();
	JComboBox layoutBox, graphBox;
	
	public GraphVitulization2D(Graph<String, Number> graphtest) {
		JFrame jFrame=new JFrame("Graph");
		Dimension preferredGraphSize=new Dimension(2500,2500);
		Layout<String, Number> layout=new FRLayout<>(graphtest, preferredGraphSize);
		VisualizationViewer<String, Number> visualizationViewer=new VisualizationViewer<>(layout, preferredGraphSize);
		visualizationViewer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		ScrollPane scrollPane=new ScrollPane();
		visualizationViewer.setGraphMouse(new DefaultModalGraphMouse<String, String>());
		scrollPane.add(visualizationViewer);
		jFrame.getContentPane().add(scrollPane);
		
		jFrame.setVisible(true);
	}
	
	
}