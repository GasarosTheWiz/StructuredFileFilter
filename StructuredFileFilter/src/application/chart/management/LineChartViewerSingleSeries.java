package application.chart.management;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class LineChartViewerSingleSeries {

	private List<String[]> series;
	private String xAxisName;
	private String yAxisName;
	private int xPosition;
	private int yPosition;

	public LineChartViewerSingleSeries(List<String[]> series, String pXAxisName, String pYAxisName, int pXPosition,
			int pYPosition) {
		this.xAxisName = pXAxisName;
		this.yAxisName = pYAxisName;
		this.series = series;
		this.xPosition = pXPosition;
		this.yPosition = pYPosition;
	}

	public XYChart getChart() {

		
		XYChart chart = new XYChartBuilder().width(800).height(600)
				
				.title(yAxisName + " over " + xAxisName).xAxisTitle(xAxisName).yAxisTitle(yAxisName).build();

		
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setPlotGridLinesVisible(false);
		chart.getStyler().setXAxisDecimalPattern("#0");// no decimals, if e.g., 4 decimals, the pattern is #0.0000

		
		List<Number> xAxisValues = new ArrayList<Number>();
		List<Double> yAxisValues = new ArrayList<Double>();
		for (String[] row : series) {
			Integer xValueInt = Integer.parseInt(row[xPosition].trim());
			xAxisValues.add(xValueInt);

			Double yValue = Double.valueOf(row[yPosition]);
			yAxisValues.add(yValue);
		}
		
		chart.addSeries("series", xAxisValues, yAxisValues);

		return chart;
	}

	public String getExampleChartName() {
		return this.yAxisName + " over " + this.xAxisName;
		
	}
}
