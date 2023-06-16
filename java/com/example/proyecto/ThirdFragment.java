package com.example.proyecto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    private WebView webView1;
    private WebView webView2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);

        webView1 = rootView.findViewById(R.id.webView1);
        webView1.getSettings().setJavaScriptEnabled(true);

        webView2 = rootView.findViewById(R.id.webView2);
        webView2.getSettings().setJavaScriptEnabled(true);

        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri","Sat","Sun"};
        int[] dataPointsX = {10, 20, 15, 12, 18, 0, 9};

        String[] categories = {"Category 1", "Category 2", "Category 3"};
        int[] dataPoints = {30, 40, 30};

        String html1 = generateChartHtml(daysOfWeek, dataPointsX);
        String html2 = generateDonutChartHtml(categories, dataPoints);

        webView1.loadData(html1, "text/html", "UTF-8");
        webView2.loadData(html2, "text/html", "UTF-8");

        return rootView;
    }

    private String generateDonutChartHtml(String[] categories, int[] dataPoints) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head>")
                .append("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>")
                .append("<script type=\"text/javascript\">")
                .append("google.charts.load('current', {packages: ['corechart']});")
                .append("google.charts.setOnLoadCallback(drawChart);")
                .append("function drawChart() {")
                .append("  var data = new google.visualization.DataTable();")
                .append("  data.addColumn('string', 'Category');")
                .append("  data.addColumn('number', 'Value');")
                .append("  data.addRows([");

        for (int i = 0; i < categories.length; i++) {
            htmlBuilder.append("    ['").append(categories[i]).append("', ").append(dataPoints[i]).append("],");
        }

        htmlBuilder.append("  ]);")
                .append("  var options = {")
                .append("    title: 'Data Distribution',")
                .append("    pieHole: 0.4,") // Controla el tamaño del agujero central
                .append("    legend: { position: 'bottom' }")
                .append("  };")
                .append("  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));")
                .append("  chart.draw(data, options);")
                .append("}")
                .append("</script></head><body>")
                .append("<div id=\"chart_div\" style=\"width: 100%; height: 100%;\"></div>")
                .append("</body></html>");

        return htmlBuilder.toString();
    }

    private String generateChartHtml(String[] xAxisLabels, int[] dataPoints) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head>")
                .append("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>")
                .append("<script type=\"text/javascript\">")
                .append("google.charts.load('current', {packages: ['corechart']});")
                .append("google.charts.setOnLoadCallback(drawChart);")
                .append("function drawChart() {")
                .append("  var data = new google.visualization.DataTable();")
                .append("  data.addColumn('string', 'Day of the Week');")
                .append("  data.addColumn('number', 'Data Point');")
                .append("  data.addRows([");

        for (int i = 0; i < xAxisLabels.length; i++) {
            htmlBuilder.append("    ['").append(xAxisLabels[i]).append("', ").append(dataPoints[i]).append("],");
        }

        htmlBuilder.append("  ]);")
                .append("  var options = {")
                .append("    title: 'Data Points',")
                .append("    curveType: 'function',")
                .append("    legend: { position: 'bottom' }")
                .append("  };")
                .append("  var chart = new google.visualization.LineChart(document.getElementById('chart_div'));")
                .append("  chart.draw(data, options);")
                .append("}")
                .append("</script></head><body>")
                .append("<div id=\"chart_div\" style=\"width: 100%; height: 100%;\"></div>")
                .append("</body></html>");

        return htmlBuilder.toString();
    }

}

