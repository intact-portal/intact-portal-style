package uk.ac.ebi.intact.style.model.shapes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EdgeShape {
    DASHED_LINE("dashed"),
    SOLID_LINE("solid");

    @JsonValue
    private final String label;

    EdgeShape(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
