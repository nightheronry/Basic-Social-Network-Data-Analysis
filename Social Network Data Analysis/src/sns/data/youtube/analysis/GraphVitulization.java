package sns.data.youtube.analysis;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout3d.Layout;
import edu.uci.ics.jung.algorithms.layout3d.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization3d.VisualizationViewer;

public class GraphVitulization extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GraphVitulization(Graph<String, Number> graphtest) {
		JFrame f = new JFrame();
		final VisualizationViewer<String, Number> vv = new VisualizationViewer<String, Number>();
		Graph<String, Number> graph = graphtest;
		vv.getRenderContext().setVertexStringer(new ToStringLabeller<String>());
		Layout<String, Number> layout = new SpringLayout<String, Number>(graph);
		vv.setGraphLayout(layout);
		f.add(vv);
		f.setSize(1600,1600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
}