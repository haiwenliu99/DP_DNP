package util;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JFreeCharUtil {
    //配置文件参数

    public String title;//曲线标题
    public int xAxis_column;//X轴数据类型为数据文件中的第几列
    public String picName;//生成图片路径
    public String yAxis_fileName;//Y轴数据文件名称
    public int curve_num;//曲线数目
    public String[] curve_yAxis_name = new String[5];//曲线名称
    public int[] curve_yAxis_column = new int[5];//Y轴数据在数据文件中的列号
    public String xAxis_name;//x轴坐标名称
    public String yAxis_name;//y轴坐标名称
    public int lineCount = 1;





    public JFreeCharUtil() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getxAxis_column() {
        return xAxis_column;
    }

    public void setxAxis_column(int xAxis_column) {
        this.xAxis_column = xAxis_column;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getyAxis_fileName() {
        return yAxis_fileName;
    }

    public void setyAxis_fileName(String yAxis_fileName) {
        this.yAxis_fileName = yAxis_fileName;
    }

    public int getCurve_num() {
        return curve_num;
    }

    public void setCurve_num(int curve_num) {
        this.curve_num = curve_num;
    }

    public String[] getCurve_yAxis_name() {
        return curve_yAxis_name;
    }

    public void setCurve_yAxis_name(String[] curve_yAxis_name) {
        this.curve_yAxis_name = curve_yAxis_name;
    }

    public int[] getCurve_yAxis_column() {
        return curve_yAxis_column;
    }

    public void setCurve_yAxis_column(int[] curve_yAxis_column) {
        this.curve_yAxis_column = curve_yAxis_column;
    }

    public String getxAxis_name() {
        return xAxis_name;
    }

    public void setxAxis_name(String xAxis_name) {
        this.xAxis_name = xAxis_name;
    }

    public String getyAxis_name() {
        return yAxis_name;
    }

    public void setyAxis_name(String yAxis_name) {
        this.yAxis_name = yAxis_name;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public JFreeCharUtil(String title, int xAxis_column, String picName, String yAxis_fileName, int curve_num, String[] curve_yAxis_name, int[] curve_yAxis_column, String xAxis_name, String yAxis_name, int lineCount) {
        this.title = title;
        this.xAxis_column = xAxis_column;
        this.picName = picName;
        this.yAxis_fileName = yAxis_fileName;
        this.curve_num = curve_num;
        this.curve_yAxis_name = curve_yAxis_name;
        this.curve_yAxis_column = curve_yAxis_column;
        this.xAxis_name = xAxis_name;
        this.yAxis_name = yAxis_name;
        this.lineCount = lineCount;



    }

    public void run() {

//		     title="曲线标题";
//		     xAxis_name="x轴";
//		     yAxis_name="y轴";
//		     yAxis_fileName="D:\\users\\IDEA\\POIUtils\\数据\\航速为9.8000的计算结果\\船舶统计结果\\船舶位移.TXT";//数据文件名称
//
//		     picName="D:\\users\\IDEA\\POIUtils\\数据\\juzhen.png";//生成图片路径
//			 curve_num=2;//曲线数目
//			 xAxis_column=0;//X轴数据在数据文件中的列号
//
//			 //第一条曲线（角标从1开始）
//			 curve_yAxis_name[1]="船舶垂荡运动时历1";//曲线名称
//			 curve_yAxis_column[1]=1;//Y轴数据在数据文件中的列号
//
//
//		    //步骤1：获取数据，生成数组，配置（画图准备）
//		    double[][] rows=Arrayread();
//
//	        //步骤2：创建XYDataset对象（准备数据）
//
//	        XYDataset dataset = createXYDataset();
//
//	        //步骤3：根据Dataset 生成JFreeChart对象，以及做相应的设置
//
//	        JFreeChart freeChart = createChart(dataset);
//
//	        //步骤4：将JFreeChart对象输出到文件，Servlet输出流等
//
//	        saveAsFile(freeChart,picName, 600, 400);

    }


    // 保存为文件

    public void saveAsFile(JFreeChart chart, String outputPath,

                           int weight, int height) {

        FileOutputStream out = null;

        try {

            File outFile = new File(outputPath);

            if (!outFile.getParentFile().exists()) {

                outFile.getParentFile().mkdirs();

            }

            out = new FileOutputStream(outputPath);

            // 保存为PNG

            ChartUtilities.writeChartAsPNG(out, chart, weight, height);

            // 保存为JPEG

            // ChartUtilities.writeChartAsJPEG(out, chart, weight, height);

            out.flush();
        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (out != null) {

                try {

                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    public JFreeChart createChart3(XYZDataset dataset) {

        JFreeChart jfreechart = ChartFactory.createXYLineChart(
                "",
                xAxis_name,
                yAxis_name,
                dataset,
                PlotOrientation.VERTICAL,
                false, false, false);

        XYPlot xyplot = ( XYPlot )jfreechart.getPlot( );

        XYItemRenderer xyitemrenderer = xyplot.getRenderer( );
        xyitemrenderer.setSeriesPaint( 0 , Color.red );
        xyplot.setBackgroundPaint(Color.white);

        ValueAxis domainAxis = xyplot.getDomainAxis();
        domainAxis.setMinorTickMarksVisible(false);
        domainAxis.setAxisLineVisible(false);
      //  domainAxis.setTickMarkPaint(Color.red);           //标记线颜色
        domainAxis.setTickMarkInsideLength(3);
     //   domainAxis.setAxisLinePaint(Color.red);
//        domainAxis.setTickLabelsVisible(true);
        ValueAxis rAxis = xyplot.getRangeAxis();
        rAxis.setMinorTickMarksVisible(false);
        rAxis.setAxisLineVisible(false);
      //  domainAxis.setTickMarkPaint(Color.red);           //标记线颜色
        rAxis.setTickMarkInsideLength(3);
        return jfreechart;
    }

    // 根据XYDataset创建JFreeChart对象
    public JFreeChart createChart2(XYDataset dataset) {

        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);
        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
                xAxis_name, // categoryAxisLabel （category轴，横轴，X轴标签）
                yAxis_name, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tooltips
                false); // URLs


        XYPlot plot = (XYPlot) jfreechart.getPlot();

        plot.setBackgroundAlpha(0.2f);

        plot.setBackgroundPaint(Color.white);

        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setMinorTickMarksVisible(false);
        domainAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        domainAxis.setTickMarkInsideLength(3);
//        domainAxis.setAxisLinePaint(Color.black);
//        domainAxis.setTickLabelsVisible(true);
        ValueAxis rAxis = plot.getRangeAxis();
        rAxis.setMinorTickMarksVisible(false);
        rAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        rAxis.setTickMarkInsideLength(3);
        return jfreechart;
    }

    // 根据XYDataset创建JFreeChart对象
    public JFreeChart createChart1(XYDataset dataset) {

        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);

        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createScatterPlot(title, // 标题
                xAxis_name, // categoryAxisLabel （category轴，横轴，X轴标签）
                yAxis_name, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL,
                false, // legend
                false, // tooltips
                false); // URLs

        // jfreechart.setBorderStroke(new BasicStroke(0.5f));
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
//        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        //    renderer.setSeriesPaint(1,Color.BLUE);
        //  renderer.setSeriesPaint(2,Color.RED);
//        renderer.setSeriesPaint(2,Color.YELLOW);
//        renderer.setSeriesPaint(3,Color.BLACK);

        // 背景色 透明度
        plot.setBackgroundAlpha(0.2f);
        // 前景色 透明度
        //    plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        plot.setBackgroundPaint(Color.white);


        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setMinorTickMarksVisible(false);
        domainAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        domainAxis.setTickMarkInsideLength(3);
//        domainAxis.setAxisLinePaint(Color.black);
//        domainAxis.setTickLabelsVisible(true);
        ValueAxis rAxis = plot.getRangeAxis();
        rAxis.setMinorTickMarksVisible(false);
        rAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        rAxis.setTickMarkInsideLength(3);
        return jfreechart;
    }

    public JFreeChart createChart_ledend_true(XYDataset dataset) {

        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);

        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
                xAxis_name, // categoryAxisLabel （category轴，横轴，X轴标签）
                yAxis_name, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tooltips
                false); // URLs

        jfreechart.setBorderStroke(new BasicStroke(0.1f));
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
//        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        //    renderer.setSeriesPaint(1,Color.BLUE);
        //  renderer.setSeriesPaint(2,Color.RED);
//        renderer.setSeriesPaint(2,Color.YELLOW);
//        renderer.setSeriesPaint(3,Color.BLACK);

        // 背景色 透明度
        plot.setBackgroundAlpha(0.2f);
        // 前景色 透明度
        //    plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        plot.setBackgroundPaint(Color.white);

        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setMinorTickMarksVisible(false);
        domainAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        domainAxis.setTickMarkInsideLength(3);
//        domainAxis.setAxisLinePaint(Color.black);
//        domainAxis.setTickLabelsVisible(true);
        ValueAxis rAxis = plot.getRangeAxis();
        rAxis.setMinorTickMarksVisible(false);
        rAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        rAxis.setTickMarkInsideLength(3);

        return jfreechart;
    }

    public JFreeChart createChart(XYDataset dataset , int a) {

        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
//        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);

        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
                xAxis_name, // categoryAxisLabel （category轴，横轴，X轴标签）
                yAxis_name, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL,
                false, // legend
                false, // tooltips
                false); // URLs

        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.2f);
        // 前景色 透明度
        //    plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        plot.setBackgroundPaint(null);

        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setMinorTickMarksVisible(false);
        domainAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        domainAxis.setTickMarkInsideLength(3);
//        domainAxis.setAxisLinePaint(Color.black);
//        domainAxis.setTickLabelsVisible(true);
        ValueAxis rAxis = plot.getRangeAxis();
        rAxis.setMinorTickMarksVisible(false);
        rAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        rAxis.setTickMarkInsideLength(3);

        return jfreechart;
    }

    public JFreeChart createChart(XYDataset dataset) {

        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);

        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
                xAxis_name, // categoryAxisLabel （category轴，横轴，X轴标签）
                yAxis_name, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL,
                false, // legend
                false, // tooltips
                false); // URLs

        jfreechart.setBorderStroke(new BasicStroke(0.1f));
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
//        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        //    renderer.setSeriesPaint(1,Color.BLUE);
        //  renderer.setSeriesPaint(2,Color.RED);
//        renderer.setSeriesPaint(2,Color.YELLOW);
//        renderer.setSeriesPaint(3,Color.BLACK);

        // 背景色 透明度
        plot.setBackgroundAlpha(0.2f);
        // 前景色 透明度
        //    plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        plot.setBackgroundPaint(Color.white);

        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setMinorTickMarksVisible(false);
        domainAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        domainAxis.setTickMarkInsideLength(3);
//        domainAxis.setAxisLinePaint(Color.black);
//        domainAxis.setTickLabelsVisible(true);
        ValueAxis rAxis = plot.getRangeAxis();
        rAxis.setMinorTickMarksVisible(false);
        rAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
        rAxis.setTickMarkInsideLength(3);

        return jfreechart;
    }


    /**
     * 创建XYDataset对象
     */

    public XYDataset createXYDataset(Matrix matrix) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();

        int rowNum = matrix.getRowNum();

        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < rowNum; j++) {
            double x = Double.parseDouble(matrix.getColumn(0)[j]);
            double y = Double.parseDouble(matrix.getColumn(1)[j]);
            xy.add(x, y);

        }
        xySeriesCollection.addSeries(xy);


        return xySeriesCollection;
    }

    public JFreeChart createMultiYAxisChart(XYSeriesCollection xyeriesCollection, String xname, String yname, String title) {
        int lineCount = xyeriesCollection.getSeriesCount();
        List list = xyeriesCollection.getSeries();


//        List<Object> list1 = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            XYSeries ss = (XYSeries) list.get(i);
//            XYSeriesCollection seriesCollection = new XYSeriesCollection();
//            seriesCollection.addSeries(ss);
//            list1.add(seriesCollection);
//        }


        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);

        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
                xname, // categoryAxisLabel （category轴，横轴，X轴标签）
                yname, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                // (XYDataset) list1.get(0), // dataset
                xyeriesCollection,
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tooltips
                false); // URLs

        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.2f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        plot.setBackgroundPaint(Color.gray);

        Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.BLACK};
//

        for (int i = 0; i < lineCount; i++) {
//          NumberAxis axis=new NumberAxis();
//          axis.setLabelPaint(colors[i]);
//          axis.setAxisLinePaint(colors[i]);
//          axis.setTickLabelPaint(colors[i]);
//
//          plot.setRangeAxis(i, axis);
//          plot.setDataset(i, (XYDataset) list1.get(i));
            plot.mapDatasetToRangeAxis(i, i);
            // -- 修改第2条曲线显示效果i

            XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
            render.setSeriesPaint(0, colors[i]);
            plot.setRenderer(i, render);

        }
        return jfreechart;
    }

    public XYDataset createXYDataset1(String[] strx, String[] stry) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < strx.length; j++) {
            double x = Double.parseDouble(strx[j]);
            double y = Double.parseDouble(stry[j]);
            xy.add(x, y);
        }
        xySeriesCollection.addSeries(xy);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset0(String[] strx0, String[] stry0) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < strx0.length; j++) {
            double x = Double.parseDouble(strx0[j]);
            double y = Double.parseDouble(stry0[j]);
            xy.add(x, y);
        }

        xySeriesCollection.addSeries(xy);
        return xySeriesCollection;
    }


    public XYDataset createXYDataset2(String[] strx1, String[] stry1,String[] stry2) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries("舵角");
        for (int j = 0; j < strx1.length; j++) {
            double x = Double.parseDouble(strx1[j]);
            double y1 = Double.parseDouble(stry1[j]);
            xy.add(x, y1);
        }
        XYSeries xy1 = new XYSeries("首向角");
        for (int j = 0; j < strx1.length; j++) {
            double x = Double.parseDouble(strx1[j]);
            double y2 = Double.parseDouble(stry2[j]);
            xy1.add(x, y2);
        }
        xySeriesCollection.addSeries(xy);
        xySeriesCollection.addSeries(xy1);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset3(String[] strx2, String[] stry3,String[] stry4) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries("舵角");
        for (int j = 0; j < strx2.length; j++) {
            double x = Double.parseDouble(strx2[j]);
            double y1 = Double.parseDouble(stry3[j]);
            xy.add(x, y1);
        }
        XYSeries xy1 = new XYSeries("首向角");
        for (int j = 0; j < strx2.length; j++) {
            double x = Double.parseDouble(strx2[j]);
            double y2 = Double.parseDouble(stry4[j]);
            xy1.add(x, y2);
        }
        xySeriesCollection.addSeries(xy);
        xySeriesCollection.addSeries(xy1);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset4(String[] strx, String[] stry) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < strx.length; j++) {
            double x = Double.parseDouble(strx[j]);
            double y = Double.parseDouble(stry[j]);
          //  System.out.println(y);
            xy.add(x, y);
        }
        xySeriesCollection.addSeries(xy);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset5(String[] strx1, String[] stry1) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < strx1.length; j++) {
            double x = Double.parseDouble(strx1[j]);
            double y = Double.parseDouble(stry1[j]);
            xy.add(x, y);
        }
        xySeriesCollection.addSeries(xy);
        return xySeriesCollection;
    }
    public XYDataset createXYDataset6(String[] strx2, String[] stry2) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < strx2.length; j++) {
            double x = Double.parseDouble(strx2[j]);
           // System.out.println(x);
            double y = Double.parseDouble(stry2[j]);
            //System.out.println(y);
            xy.add(x, y);
        }
        xySeriesCollection.addSeries(xy);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset7(String[] strx4, String[] stry4) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < strx4.length; j++) {
            double x = Double.parseDouble(strx4[j]);
           // System.out.println(x);
            double y = Double.parseDouble(stry4[j]);
          //  System.out.println(y);
            xy.add(x, y);
        }
        xySeriesCollection.addSeries(xy);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset8(List<Double> xData,List<Double> yData,List<Double> yData1,List<Double> yData2) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries("舵角");
        for (int j = 0; j < xData.size(); j++) {
            double x = xData.get(j);
            double y1 = yData.get(j);
            xy.add(x, y1);
        }
        XYSeries xy1 = new XYSeries("首向角");
        for (int j = 0; j < xData.size(); j++) {
            double x = xData.get(j);
            double y2 = yData1.get(j);
            xy1.add(x, y2);
        }
        XYSeries xy2 = new XYSeries("首向角");
        for (int j = 0; j < xData.size(); j++) {
            double x = xData.get(j);
            double y3 = yData2.get(j);
            xy1.add(x, y3);
        }
        xySeriesCollection.addSeries(xy);
        xySeriesCollection.addSeries(xy1);
        xySeriesCollection.addSeries(xy2);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset9(List<Double> xData,List<Double> yData) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for (int j = 0; j < xData.size(); j++) {
            double x = xData.get(j);
            double y = yData.get(j);
            xy.add(x, y);
        }
        xySeriesCollection.addSeries(xy);
        return xySeriesCollection;
    }

    public XYDataset createXYDataset10(Map<String,List<Double>> map, List<Double> yData) {

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xy = new XYSeries(curve_yAxis_name[1]);
        for(int i = 0;i<20;i++) {
            for (int j = 0; j < yData.size(); j++) {
                double x = map.get("xData"+i).get(j);
                double y = yData.get(j);
                xy.add(x, y);
            }
            xySeriesCollection.addSeries(xy);

        }
        return xySeriesCollection;
    }

    public XYZDataset createXYDataset11(double[] xData, double[] yData, double[] zData) {

        DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();
        double[][] ad3 = { xData , yData , zData };
        defaultxyzdataset.addSeries( "Series 1" , ad3 );
        return defaultxyzdataset;
    }

//    public JFreeChart createMultiYAxisChartHORIZONTAL(XYSeriesCollection xyeriesCollection, String xname, String yname) {
//        int lineCount = xyeriesCollection.getSeriesCount();
//        List list = xyeriesCollection.getSeries();
//
//
//        List<Object> list1 = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            XYSeries ss = (XYSeries) list.get(i);
//            XYSeriesCollection seriesCollection = new XYSeriesCollection();
//            seriesCollection.addSeries(ss);
//            list1.add(seriesCollection);
//        }
//
//
//        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
//        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
//        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
////        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
//        ChartFactory.setChartTheme(mChartTheme);
//        // 创建JFreeChart对象：ChartFactory.createXYLineChart
//        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
//                xname, // categoryAxisLabel （category轴，横轴，X轴标签）
//                yname, // valueAxisLabel（value轴，纵轴，Y轴的标签）
//                // (XYDataset) list1.get(0), // dataset
//                xyeriesCollection,
//                PlotOrientation.HORIZONTAL,
//                false, // legend
//                false, // tooltips
//                false); // URLs
//
//        // 使用CategoryPlot设置各种参数。以下设置可以省略。
//        XYPlot plot = (XYPlot) jfreechart.getPlot();
////        lineandshaperenderer.setShapesVisible(true);
//
//        // 背景色 透明度
//        plot.setBackgroundAlpha(0.2f);
//        // 前景色 透明度
//        plot.setForegroundAlpha(0.5f);
//        // 其它设置可以参考XYPlot类
//        plot.setBackgroundPaint(null);
//        Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.BLACK,Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.BLACK,Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW};
////
//
//        for (int i = 0; i < lineCount; i++) {
////          NumberAxis axis=new NumberAxis();
////          axis.setLabelPaint(colors[i]);
////          axis.setAxisLinePaint(colors[i]);
////          axis.setTickLabelPaint(colors[i]);
////
////          plot.setRangeAxis(i, axis);
////          plot.setDataset(i, (XYDataset) list1.get(i));
//            plot.mapDatasetToRangeAxis(i, i);
//            // -- 修改第2条曲线显示效果i
//
//            XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
//            render.setSeriesPaint(0, colors[i]);
//            render.setBaseShapesVisible(false);
//            render.setBaseShapesFilled(false);
//            plot.setRenderer(i, render);
//
//        }
//        return jfreechart;
//    }
public JFreeChart createMultiYAxisChartHORIZONTAL(XYSeriesCollection xyeriesCollection, String xname, String yname ,boolean a) {
    int lineCount = xyeriesCollection.getSeriesCount();
    List list = xyeriesCollection.getSeries();


    List<Object> list1 = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
        XYSeries ss = (XYSeries) list.get(i);
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        seriesCollection.addSeries(ss);
        list1.add(seriesCollection);
    }


    StandardChartTheme mChartTheme = new StandardChartTheme("CN");
    mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
    mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
    mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
    ChartFactory.setChartTheme(mChartTheme);

    // 创建JFreeChart对象：ChartFactory.createXYLineChart
    JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
            xname, // categoryAxisLabel （category轴，横轴，X轴标签）
            yname, // valueAxisLabel（value轴，纵轴，Y轴的标签）
            // (XYDataset) list1.get(0), // dataset
            xyeriesCollection,
            PlotOrientation.HORIZONTAL,
            a, // legend
            false, // tooltips
            false); // URLs

    // 使用CategoryPlot设置各种参数。以下设置可以省略。
    XYPlot plot = (XYPlot) jfreechart.getPlot();
//        lineandshaperenderer.setShapesVisible(true);true

    // 背景色 透明度
    plot.setBackgroundAlpha(0.2f);
    // 前景色 透明度
    plot.setForegroundAlpha(0.5f);
    // 其它设置可以参考XYPlot类
    plot.setBackgroundPaint(null);
    ValueAxis domainAxis = plot.getDomainAxis();
    domainAxis.setMinorTickMarksVisible(false);
    domainAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
    domainAxis.setTickMarkInsideLength(3);
//        domainAxis.setAxisLinePaint(Color.black);
//        domainAxis.setTickLabelsVisible(true);
    ValueAxis rAxis = plot.getRangeAxis();
    rAxis.setMinorTickMarksVisible(false);
    rAxis.setAxisLineVisible(false);
//        domainAxis.setTickMarkPaint(Color.black);           //标记线颜色
    rAxis.setTickMarkInsideLength(3);


    Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.BLACK,Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.BLACK,Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW};
//

    for (int i = 0; i < lineCount; i++) {
//          NumberAxis axis=new NumberAxis();
//          axis.setLabelPaint(colors[i]);
//          axis.setAxisLinePaint(colors[i]);
//          axis.setTickLabelPaint(colors[i]);
//
//          plot.setRangeAxis(i, axis);
//          plot.setDataset(i, (XYDataset) list1.get(i));
        plot.mapDatasetToRangeAxis(i, i);
        // -- 修改第2条曲线显示效果i

        XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
        render.setSeriesPaint(0, colors[i]);
        render.setBaseShapesVisible(false);
        render.setBaseShapesFilled(false);
        plot.setRenderer(i, render);

    }
    return jfreechart;
}

    // 根据XYDataset创建JFreeChart对象
    public JFreeChart createMultiYAxisChart(XYSeriesCollection xyeriesCollection, String xname, String yname, String[] titl) {
        int lineCount = xyeriesCollection.getSeriesCount();
        List list = xyeriesCollection.getSeries();


        List<Object> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            XYSeries ss = (XYSeries) list.get(i);
            XYSeriesCollection seriesCollection = new XYSeriesCollection();
            seriesCollection.addSeries(ss);
            list1.add(seriesCollection);
        }


        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);

        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
                xname, // categoryAxisLabel （category轴，横轴，X轴标签）
                yname, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                // (XYDataset) list1.get(0), // dataset
                xyeriesCollection,
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tooltips
                false); // URLs

        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.2f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        plot.setBackgroundPaint(Color.gray);

        Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.BLACK};
//

        for (int i = 0; i < lineCount; i++) {
//          NumberAxis axis=new NumberAxis();
//          axis.setLabelPaint(colors[i]);
//          axis.setAxisLinePaint(colors[i]);
//          axis.setTickLabelPaint(colors[i]);
//
//          plot.setRangeAxis(i, axis);
//          plot.setDataset(i, (XYDataset) list1.get(i));
            plot.mapDatasetToRangeAxis(i, i);
            // -- 修改第2条曲线显示效果i

            XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
            render.setSeriesPaint(0, colors[i]);
            plot.setRenderer(i, render);

        }
        return jfreechart;
    }
    public JFreeChart createMultiYAxisChart(XYSeriesCollection xyeriesCollection, String xname, String yname, boolean a) {
        int lineCount = xyeriesCollection.getSeriesCount();
        List list = xyeriesCollection.getSeries();


        List<Object> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            XYSeries ss = (XYSeries) list.get(i);
            XYSeriesCollection seriesCollection = new XYSeriesCollection();
            seriesCollection.addSeries(ss);
            list1.add(seriesCollection);
        }


        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));//设置轴向字体
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));//设置标题字体
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));//设置图例字体
        ChartFactory.setChartTheme(mChartTheme);

        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, // 标题
                xname, // categoryAxisLabel （category轴，横轴，X轴标签）
                yname, // valueAxisLabel（value轴，纵轴，Y轴的标签）
                // (XYDataset) list1.get(0), // dataset
                xyeriesCollection,
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tooltips
                false); // URLs

        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.2f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        plot.setBackgroundPaint(Color.gray);

        Color[] colors = {Color.BLUE, Color.YELLOW, Color.RED};
//

        for (int i = 0; i < lineCount; i++) {
//          NumberAxis axis=new NumberAxis();
//          axis.setLabelPaint(colors[i]);
//          axis.setAxisLinePaint(colors[i]);
//          axis.setTickLabelPaint(colors[i]);
//
//          plot.setRangeAxis(i, axis);
//          plot.setDataset(i, (XYDataset) list1.get(i));
            plot.mapDatasetToRangeAxis(i, i);
            // -- 修改第2条曲线显示效果i

            XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
            render.setSeriesPaint(i, new Color(70*i,70*i,0));

            render.setBaseShapesVisible(a);
            plot.setRenderer(i, render);

        }
        return jfreechart;
    }

    public XYSeriesCollection createMultiXYDataset(double[][] tt,int b,int a ) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
//        int lineCount = matrix.getColumnNum() - 1;
//        int rowNum = matrix.getRowNum() - 1;

//        String[] tt = matrix.getRow(0);
//        String[][] data = matrix.removeRow(0);
//        matrix.setMatrix(data);
        for (int i = 1; i < a; i++) {
            XYSeries xy = new XYSeries(i);
            for (int j = 0; j < b+1; j++) {
                double x = tt[j][i];
                System.out.print(x);
                System.out.print(' ');
                double y = tt[j][0];
                System.out.println(y);
                xy.add(y, x);
            }
            xySeriesCollection.addSeries(xy);
//            break;
        }
        return xySeriesCollection;
    }

    public XYSeriesCollection createMultiXYDataset(double[][] tt,int b,int a ,String s) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
//        int lineCount = matrix.getColumnNum() - 1;
//        int rowNum = matrix.getRowNum() - 1;

//        String[] tt = matrix.getRow(0);
//        String[][] data = matrix.removeRow(0);
//        matrix.setMatrix(data);
        for (int i = 1; i < a; i++) {
            XYSeries xy = new XYSeries(Double.toString(10+i*0.25-0.25)+"(s)");
            for (int j = 0; j < b+1; j++) {
                double x = tt[j][i];
                System.out.print(x);
                System.out.print(' ');
                double y = tt[j][0];
                System.out.println(y);
                xy.add(y, x);
            }
            xySeriesCollection.addSeries(xy);
//            break;
        }
        return xySeriesCollection;
    }

    public XYSeriesCollection createMultiXYDataset(Matrix matrix,double a) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        int lineCount = matrix.getColumnNum() - 1;
        int rowNum = matrix.getRowNum() - 1;

        String[] tt = matrix.getRow(0);
        String[][] data = matrix.removeRow(0);
        matrix.setMatrix(data);
        for (int i = 0; i < lineCount; i++) {
            XYSeries xy = new XYSeries(tt[i + 1]);
            for (int j = 0; j < rowNum; j++) {
                double x = Double.parseDouble(matrix.getColumn(0)[j]);
                double y = Double.parseDouble(matrix.getColumn(i + 1)[j])/a;
                xy.add(x, y);
            }
            xySeriesCollection.addSeries(xy);
        }
        return xySeriesCollection;
    }

    public XYSeriesCollection createMultiXYDataset(Matrix matrix) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        int lineCount = matrix.getColumnNum() - 1;
        int rowNum = matrix.getRowNum() - 1;

        String[] tt = matrix.getRow(0);
        String[][] data = matrix.removeRow(0);
        matrix.setMatrix(data);
        for (int i = 0; i < lineCount; i++) {
            XYSeries xy = new XYSeries(tt[i + 1]);
            for (int j = 0; j < rowNum; j++) {
                double x = Double.parseDouble(matrix.getColumn(0)[j]);
                double y = Double.parseDouble(matrix.getColumn(i + 1)[j]);
                xy.add(x, y);
            }
            xySeriesCollection.addSeries(xy);
        }
        return xySeriesCollection;
    }
    public XYSeriesCollection createMultiXYDataset(Matrix matrix,String[] s) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        int lineCount = matrix.getColumnNum() - 1;
        int rowNum = matrix.getRowNum() - 1;

        for (int i = 0; i < lineCount; i++) {
            XYSeries xy = new XYSeries(s[i + 1]);
            for (int j = 0; j < rowNum; j++) {
                double x = Double.parseDouble(matrix.getColumn(0)[j]);
                double y = Double.parseDouble(matrix.getColumn(i + 1)[j]);
                xy.add(x, y);
            }
            xySeriesCollection.addSeries(xy);
        }
        return xySeriesCollection;
    }


}
