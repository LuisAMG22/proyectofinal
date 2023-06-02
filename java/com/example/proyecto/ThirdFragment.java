package com.example.proyecto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    private WebView webView;

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

        webView = rootView.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        String html = "<html><head>" +
                "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>" +
                "<script type=\"text/javascript\">" +
                "google.charts.load('current', {packages: ['corechart']});" +
                "google.charts.setOnLoadCallback(drawChart);" +
                "function drawChart() {" +
                "  var data = google.visualization.arrayToDataTable([" +
                "    ['Year', 'Sales', 'Expenses']," +
                "    ['2014', 1000, 400]," +
                "    ['2015', 1170, 460]," +
                "    ['2016', 660, 1120]," +
                "    ['2017', 1030, 540]" +
                "  ]);" +
                "  var options = {" +
                "    title: 'Company Performance'," +
                "    curveType: 'function'," +
                "    legend: { position: 'bottom' }" +
                "  };" +
                "  var chart = new google.visualization.LineChart(document.getElementById('chart_div'));" +
                "  chart.draw(data, options);" +
                "}" +
                "</script></head><body>" +
                "<div id=\"chart_div\" style=\"width: 100%; height: 100%;\"></div>" +
                "</body></html>";

        webView.loadData(html, "text/html", "UTF-8");

        return rootView;
    }
}
