package GraphVisualization;

import java.awt.Color;
import java.util.Map;
import java.util.Map.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class ParticlesLineGraph extends ApplicationFrame {

	public ParticlesLineGraph(final String title, Map<String, Map<Double, Double>> particleProgress) {

		super(title);

		final XYDataset dataset = createDataset(particleProgress);
		final JFreeChart chart = createChart(title, dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1500, 1270));
		setContentPane(chartPanel);

	}


	private XYDataset createDataset(Map<String, Map<Double, Double>> particleProgress) {
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		for (Entry<String, Map<Double, Double>> entry : particleProgress.entrySet()) {
		    //System.out.println(entry.getKey()+" -> "+entry.getValue());		    
			
			XYSeries series = new XYSeries(entry.getKey());
			for(Entry<Double,Double> e: entry.getValue().entrySet()){
				series.add(e.getKey(), e.getValue());				
			}
			
			dataset.addSeries(series);
		}
		


		return dataset;

	}
	
	
   private JFreeChart createChart(String title, XYDataset dataset) {
        
	   JFreeChart chart = ChartFactory.createXYLineChart(
        	title,      			  // chart title
            "Iteration Count",        // x axis label
            "pBest",                  // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tool tips
            false                     // url
        );
        chart.setBackgroundPaint(Color.white);
        
        // get a reference to the plot for further customization...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        return chart;
        
    }

}
