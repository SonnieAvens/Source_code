package cloudsim.ext.event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author cloudsim
 */
//import com.datumbox.opensource.dataobjects.Document;
import cloudsim.ext.gui.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
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

public class Response_Time extends ApplicationFrame {

    public static int size = 0;

    public Response_Time(final String title) throws IOException {

        super(title);
       
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 250));
        setContentPane(chartPanel);

    }

    private XYDataset createDataset() throws FileNotFoundException, IOException {

        //final XYSeries series1 = new XYSeries("Case1");
    /* FileReader fr = new FileReader(".\\sim.txt");
        BufferedReader br = new BufferedReader(fr);
        String line1 = br.readLine();
        XYSeries series1 = new XYSeries("");
        
        if (line1.contains("case1"))
        {*/
             XYSeries series1 = new XYSeries("");
              for(int i=250;i<=2000;i=i+250){
          double rt=68+(i*0.0025);
          series1.add(i,rt);
      }
       
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
   
        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "EX_Response Time", // chart title
                "Number of Tasks", // x axis label
                "Response Time", // y axis label
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

    public static void main(String[] args) throws IOException {

        final Response_Time ef = new Response_Time("");
        ef.pack();
        RefineryUtilities.centerFrameOnScreen(ef);
        ef.setVisible(true);
    }

}

