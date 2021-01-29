package uk.ac.ebi.intact.style.mapper.continuous;

import uk.ac.ebi.intact.style.mapper.Mapper;
import uk.ac.ebi.intact.style.model.legend.Range;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MIScoreMapper implements Mapper<Double, Color> {
    public static final Color[] colors = {
            new Color(255, 255, 221),
            new Color(255, 248, 171),
            new Color(254, 224, 121),
            new Color(254, 186, 51),
            new Color(254, 135, 13),
            new Color(231, 90, 0),
            new Color(193, 55, 0),
            new Color(135, 36, 0),
            new Color(83, 26, 0),
            new Color(41, 15, 2),
    };

    @Override
    public Color getStyleOf(Double key) {
        return colors[(int) Math.floor(key * 10)];
    }

    public Map<Range, Color> createLegend() {
        Map<Range, Color> legend = new LinkedHashMap<>();
        for (int i = 0; i < colors.length; i++) {
            double start = i / 10.0;
            legend.put(new Range(start, start + 0.1), colors[i]);
        }
        return legend;
    }
}
