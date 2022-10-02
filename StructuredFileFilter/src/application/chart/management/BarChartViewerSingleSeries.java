package application.chart.management;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class BarChartViewerSingleSeries {

	private List<String[]> series;
	private String xAxisName;
	private String yAxisName;
	private int xPosition;
	private int yPosition;

	public BarChartViewerSingleSeries(List<String[]> series, String pXAxisName, String pYAxisName, int pXPosition,
			int pYPosition) {
		this.xAxisName = pXAxisName;
		this.yAxisName = pYAxisName;
		this.series = series;
		this.xPosition = pXPosition;
		this.yPosition = pYPosition;
	}

	public CategoryChart getChart() {

		
		CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
				
				.title(yAxisName + " over " + xAxisName).xAxisTitle(xAxisName).yAxisTitle(yAxisName).build();

		
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setLabelsVisible(false);
		chart.getStyler().setPlotGridLinesVisible(false);

	
		List<String> xAxisValues = new ArrayList<String>();
		List<Double> yAxisValues = new ArrayList<Double>();
		for (String[] row : series) {
			String xValue = row[xPosition];
			xAxisValues.add(xValue);
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
