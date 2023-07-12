package uk.ac.ebi.intact.style.mapper.booleans;


import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.style.model.shapes.EdgeShape;
@Component
public class InteractionExpansionMapper extends BooleanMapper<EdgeShape> {
    @Override
    public EdgeShape getStyleOf(Boolean key) {
        return key ? EdgeShape.DASHED_LINE : EdgeShape.SOLID_LINE;
    }

    @Override
    public String getTrueLabel() {
        return "spoke expansion";
    }

    @Override
    public String getFalseLabel() {
        return "no expansion";
    }
}
