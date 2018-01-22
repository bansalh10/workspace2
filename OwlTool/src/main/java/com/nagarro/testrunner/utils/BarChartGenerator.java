package com.nagarro.testrunner.utils;
import java.awt.Color;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Component;
@Component("barChartGenerator")
public class BarChartGenerator {
   
   public byte[] createSimpleBarChart( int totalNumbers, int successNum, int failureNum )throws Exception {
      final String total = "Total";
      final String  success= "Success";
      final String failure = "Fail";
      final String successRate = "Success Rate";

      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( totalNumbers , total , successRate );
      dataset.addValue( successNum , success , successRate );
      dataset.addValue( failureNum , failure , successRate );


      JFreeChart barChart = ChartFactory.createBarChart(
         "Test Cases Statistics", 
         "Number Of Test Cases", "Score", 
         dataset,PlotOrientation.VERTICAL, 
         true, true, false);
      barChart.setBackgroundPaint(Color.white);
      int width = 640;    /* Width of the image */
      int height = 480;   /* Height of the image */ 
      BufferedImage objBufferedImage=barChart.createBufferedImage(width,height);
      ByteArrayOutputStream bas = new ByteArrayOutputStream();
              try {
                  ImageIO.write(objBufferedImage, "jpeg", bas);
              } catch (IOException e) {
                  e.printStackTrace();
              }

      byte[] byteArray=bas.toByteArray();
      return byteArray;
   }
}