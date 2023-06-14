package com.example.stats;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stats.R;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner dropdown;
    private PieChart pieChart;
    private TextView infoTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdown = findViewById(R.id.dropdown);
        pieChart = findViewById(R.id.pieChart);
        infoTextView = findViewById(R.id.infoTextView);

        // Configure the dropdown menu
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        // Display the initial chart and information
        displayChart("Hoy");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        displayChart(selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private void displayChart(String selectedItem) {
        // Clear previous data
        pieChart.clear();
        infoTextView.setText("");

        // Configure pie chart
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(40f);

        // Get data from JSON (static values for demonstration purposes)
        float calories = 21814;
        float energy = 21814.306200000003f;
        float fat = 15627.5579472000002f;
        float carbs = 3909.41195f;
        float saturated = 30045.3f;
        float fiber = 77344.3329f;
        float trans = 34450.314f;
        float sugars = 2331.234f;
        float protein = 67753.323f;
        float cholesterol = 9993.133f;
        float sodium = 13467.89f;

        // Create pie entries
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(calories, "Calories"));
        entries.add(new PieEntry(energy, "Energy"));
        entries.add(new PieEntry(fat, "Fat"));
        entries.add(new PieEntry(carbs, "Carbs"));

        // Create pie dataset
        PieDataSet dataSet = new PieDataSet(entries, "Nutrients");
        dataSet.setColors(Color.rgb(255, 127, 80), Color.rgb(176, 224, 230), Color.rgb(255, 215, 0), Color.rgb(124, 252, 0));
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // Create pie data
        PieData data = new PieData(dataSet);
        data.setValueTextSize(14f);
        CenteredValueFormatter centeredValueFormatter = new CenteredValueFormatter();
        data.setValueFormatter(centeredValueFormatter);
        pieChart.setDrawEntryLabels(false);
        data.setValueTextColor(Color.BLACK);

        // Set data to pie chart
        pieChart.setData(data);

        // Generate information text
        String infoText = "Selected: " + selectedItem + "\n\n"
                + "Calories: " + calories + " kcal\n"
                + "Energy: " + energy + " kcal\n"
                + "Fat: " + fat + " g\n"
                + "Carbs: " + carbs + " g\n"
                + "Trans: " + trans + " g\n"
                + "Fiber" + fiber + " g\n"
                + "Sugars: " + sugars + " g\n"
                + "Protein: " + protein + " g\n"
                + "Cholesterol: " + cholesterol + " g\n"
                + "Sodium: " + sodium + " g\n";
    // Invalidate the chart to refresh it
        pieChart.invalidate();

        // Display information text
        infoTextView.setText(infoText);
    }
}
