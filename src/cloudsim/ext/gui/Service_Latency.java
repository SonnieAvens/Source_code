
package cloudsim.ext.gui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class Service_Latency extends ApplicationFrame {
     public Service_Latency(final String title) throws IOException {
        super(title);  
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 250));
        setContentPane(chartPanel);
    }    private XYDataset createDataset() throws FileNotFoundException, IOException {
           XYSeries series1 = new XYSeries("");
      for(int i=250;i<=2000;i=i+250){
          double lat=0.5+(i*0.00025);
          series1.add(i,lat);
      }
              
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);   
        return dataset;

    }
  private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "PRO_Service_Latency", // chart title
                "Number of Tasks", // x axis label
                "Service_Latency", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesLinesVisible(0, true);
        //  renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setAutoRange(true);
        return chart;

    }

    public static void main(String[] args) {

         try {
             final Service_Latency ef = new Service_Latency("");
             ef.pack();
             RefineryUtilities.centerFrameOnScreen(ef);
             ef.setVisible(true);
         } catch (IOException ex) {
             Logger.getLogger(Service_Latency.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}

