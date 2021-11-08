package uk.ac.ebi.intact.style.mapper.booleans;


import java.awt.*;

public class InteractionNegativeMapper extends BooleanMapper<Color> {

    @Override
    public Color getStyleOf(Boolean key) {
        return key ? Color.RED : null;
    }

    @Override
    public String getTrueLabel() {
        return "Negative interaction";
    }

    @Override
    public String getFalseLabel() {
        return "Positive interaction";
    }
}
