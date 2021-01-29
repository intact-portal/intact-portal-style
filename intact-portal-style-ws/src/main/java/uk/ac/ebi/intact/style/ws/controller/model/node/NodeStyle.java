package uk.ac.ebi.intact.style.ws.controller.model.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ebi.intact.style.model.shapes.NodeShape;

import java.awt.*;

public class NodeStyle {
    @JsonProperty
    private Color fill;
    @JsonProperty
    private Color stroke;
    @JsonProperty("stroke_width")
    private Integer strokeWidth;
    @JsonProperty
    private NodeShape shape;

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    public Color getStroke() {
        return stroke;
    }

    public void setStroke(Color stroke) {
        this.stroke = stroke;
    }

    public Integer getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Integer strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public NodeShape getShape() {
        return shape;
    }

    public void setShape(NodeShape shape) {
        this.shape = shape;
    }
}
