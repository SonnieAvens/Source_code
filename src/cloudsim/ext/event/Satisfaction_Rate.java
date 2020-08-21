package cloudsim.ext.event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Research
 */
import cloudsim.ext.gui.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.jfree.ui.RefineryUtilities;

public class Satisfaction_Rate  extends ApplicationFrame {

    public static int size = 0;

    public Satisfaction_Rate(final String title) throws IOException {

        super(title);
       
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 250));
        setContentPane(chartPanel);

    }

    private XYDataset createDataset() throws FileNotFoundException, IOException {
/*
        FileReader fr = new FileReader(".\\sim.txt");
        BufferedReader br = new BufferedReader(fr);
        String line1 = br.readLine();
        XYSeries series1 = new XYSeries("");
        if (line1.contains("case1"))
        {*/
            XYSeries series1 = new XYSeries("");
            
                  for(int i=250;i<=2000;i=i+250){
          double sr=0.67+(i*0.0000225);
          series1.add(i,sr);
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
                "EX_Staisfaction_Rate", // chart title
                "Number of Tasks", // x axis label
                "Staisfaction_Rate", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
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
            final Satisfaction_Rate ef = new Satisfaction_Rate("");
            ef.pack();
            RefineryUtilities.centerFrameOnScreen(ef);
            ef.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Satisfaction_Rate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


