package uk.ac.ebi.intact.style.ws.controller.model.edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ebi.intact.style.model.shapes.EdgeShape;

import java.awt.*;

public class GlobalEdgeStyle {
    @JsonProperty("summary_color")
    private Color summaryColor;
    @JsonProperty("summary_width")
    private int summaryWidth;

    @JsonProperty("evidence_color")
    private Color evidenceColor;
    private EdgeShape shape;

    @JsonProperty("mutation_color")
    private Color mutationColor;
    @JsonProperty("mutation_width")
    private int mutationWidth;
    @JsonProperty("negative_symbol_color")
    private Color negativeSymbolColor;

    public Color getSummaryColor() {
        return summaryColor;
    }

    public void setSummaryColor(Color summaryColor) {
        this.summaryColor = summaryColor;
    }

    public int getSummaryWidth() {
        return summaryWidth;
    }

    public void setSummaryWidth(int summaryWidth) {
        this.summaryWidth = summaryWidth;
    }

    public Color getEvidenceColor() {
        return evidenceColor;
    }

    public void setEvidenceColor(Color evidenceColor) {
        this.evidenceColor = evidenceColor;
    }

    public EdgeShape getShape() {
        return shape;
    }

    public void setShape(EdgeShape shape) {
        this.shape = shape;
    }

    public Color getMutationColor() {
        return mutationColor;
    }

    public void setMutationColor(Color mutationColor) {
        this.mutationColor = mutationColor;
    }

    public int getMutationWidth() {
        return mutationWidth;
    }

    public void setMutationWidth(int mutationWidth) {
        this.mutationWidth = mutationWidth;
    }

    public Color getNegativeSymbolColor() {
        return negativeSymbolColor;
    }

    public void setNegativeSymbolColor(Color negativeSymbolColor) {
        this.negativeSymbolColor = negativeSymbolColor;
    }
}
