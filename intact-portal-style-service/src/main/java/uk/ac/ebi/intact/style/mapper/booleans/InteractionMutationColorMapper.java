package uk.ac.ebi.intact.style.mapper.booleans;

import org.springframework.stereotype.Component;

import java.awt.*;
@Component
public class InteractionMutationColorMapper extends BooleanMapper<Color>{
    public static final Color MUTATION_COLOR = new Color(255, 0, 161);
    public static final Color WILD_TYPE_COLOR = new Color(126, 131, 137);

    @Override
    public Color getStyleOf(Boolean key) {
        return key ? MUTATION_COLOR : WILD_TYPE_COLOR;
    }

    @Override
    public String getTrueLabel() {
        return "Affected by mutation";
    }

    @Override
    public String getFalseLabel() {
        return "Not affected by mutation";
    }
}
