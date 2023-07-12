package uk.ac.ebi.intact.style.mapper.booleans;


import org.springframework.stereotype.Component;

import java.awt.*;
@Component
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
