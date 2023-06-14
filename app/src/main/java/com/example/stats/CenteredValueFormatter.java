package com.example.stats;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class CenteredValueFormatter extends ValueFormatter {
    @Override
    public String getFormattedValue(float value) {
        return ""; // Devuelve una cadena vacía para no mostrar los valores en la gráfica de pastel
    }

    @Override
    public String getPieLabel(float value, PieEntry pieEntry) {
        return pieEntry.getLabel(); // Devuelve el label del PieEntry para mostrarlo en el centro
    }
}