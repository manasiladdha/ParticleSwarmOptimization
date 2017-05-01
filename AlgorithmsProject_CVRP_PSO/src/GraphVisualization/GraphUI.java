package GraphVisualization;

import java.util.List;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;


public class GraphUI {
	
   private String styleSheet =
	        	"node { fill-color: blue; text-color: white; size: 40px; text-size: 20px;} "+
	            "node#0 { shape: box; fill-color: orange;  } "+
	        	"edge.marked {fill-color: red;}"+		
	        	"edge {text-size: 20px; text-alignment: above; size: 3px; fill-color: black; arrow-shape: arrow; arrow-size: 8px;}";

	public void displayGraph(String gName, List<Integer> edges){
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
		Graph graph = new MultiGraph(gName);
		graph.addAttribute("ui.stylesheet", styleSheet);
		//graph.addAttribute("ui.label", gName);
		graph.setAutoCreate(true);
        graph.setStrict(false);        
        graph.display();
       	
        
		for(int e=0; e<edges.size()-1;e++){					
			graph.addEdge(Integer.toString(e), Integer.toString(edges.get(e)), Integer.toString(edges.get(e+1)), true);
		}
		
		for (Node node : graph.getNodeSet())
	        node.addAttribute("ui.label", node.getId());
	    
		
		explore(graph);		
		
	}
	
	private void explore(Graph graph){
		//int tripCount = Integer.parseInt(graph.getId().split(",")[1].split(":")[1]);
		int t =1;
		int i=0;
		for(; i< graph.getEdgeCount(); i++){
			graph.getEdge(i).addAttribute("ui.class", "marked");
			if(graph.getEdge(i).getNode1().getId().equals("0"))
				graph.getEdge(i).addAttribute("ui.label", "Trip:"+t++);
			sleep();
		}
		graph.getEdge(i-1).addAttribute("ui.label", graph.getId());
	}
	
	private void sleep() {
        try { Thread.sleep(1500); } catch (Exception e) {}
    }
	
	
}
