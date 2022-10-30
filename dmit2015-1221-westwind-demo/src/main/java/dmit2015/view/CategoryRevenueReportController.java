package dmit2015.view;

import dmit2015.repository.CategoryRepository;
import dmit2015.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("currentCategoryRevenueReportController")
@ViewScoped
public class CategoryRevenueReportController implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryRepository _categoryRepository;
	@Inject
	private OrderRepository _orderRepository;

	@Getter
	private  Map<String, Integer> categorySelectItemMap;
	@Getter
	private List<Integer> orderYears;
	@Getter @Setter
	private Integer selectedSalesYear;

	@Getter @Setter
	private Integer selectedCategoryId;
	@Getter
	private LineChartModel currentLineChartModel;

	@PostConstruct
	void init() {
		try {
			orderYears = _orderRepository.findYearsWithOrders();
			selectedSalesYear = orderYears.get(0);
			categorySelectItemMap = _categoryRepository.selectItemMap();

			createLineChart();
		} catch(Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Error retrieving category sales report");
		}
	}

	public void onCreateReport() {
		createLineChart();
	}
	public void createLineChart() {
		currentLineChartModel = new LineChartModel();
		var currentChartData = new ChartData();

		var currentLineChartDataSet = new LineChartDataSet();
		var values = new ArrayList<Object>();
		var labels = new ArrayList<String>();
		for (int currentMonth = 1; currentMonth <= 12;currentMonth++) {
			var categoryRevenue = _categoryRepository.categoryRevenueByYearAndMonth(selectedCategoryId, selectedSalesYear, currentMonth);
			values.add(categoryRevenue);
			labels.add(String.valueOf(currentMonth));
		}
		currentLineChartDataSet.setData(values);
		currentLineChartDataSet.setLabel("Monthly Category Revenue Dataset");
		currentLineChartDataSet.setBackgroundColor("rgb(75, 192, 192)");
		currentChartData.addChartDataSet(currentLineChartDataSet);
		currentChartData.setLabels(labels);

		//Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		title.setText("Category Revenue Line Chart");
		options.setTitle(title);

		currentLineChartModel.setOptions(options);
		currentLineChartModel.setData(currentChartData);
	}


}
