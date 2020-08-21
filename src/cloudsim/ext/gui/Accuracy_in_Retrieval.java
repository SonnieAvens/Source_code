
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


public class Accuracy_in_Retrieval extends ApplicationFrame {

     public Accuracy_in_Retrieval(final String title) throws IOException {

        super(title);
       
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 250));
        setContentPane(chartPanel);
    }
    private XYDataset createDataset() throws FileNotFoundException, IOException {
    
      XYSeries series1 = new XYSeries("");
                
          for(int i=100;i<=1000;i=i+100){
          double air=95+(i*0.005);
          series1.add(i,air);
                  }       
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);   
        return dataset;

    }
  private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "PRO_Accuracy_in_Retrieval", // chart title
                "Number of Requests ", // x axis label
                "Accuracy_in_Retrieval", // y axis label
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
             final Accuracy_in_Retrieval ef = new Accuracy_in_Retrieval("");
             ef.pack();
             RefineryUtilities.centerFrameOnScreen(ef);
             ef.setVisible(true);
         } catch (IOException ex) {
             Logger.getLogger(Accuracy_in_Retrieval.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}

