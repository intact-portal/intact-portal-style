package uk.ac.ebi.intact.style.ws.controller.model.edge;

import uk.ac.ebi.intact.style.model.shapes.EdgeShape;

import java.awt.*;

public class EdgeStyle {
    private Color color;
    private EdgeShape shape = EdgeShape.SOLID_LINE;
    private int width = 3;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EdgeShape getShape() {
        return shape;
    }

    public void setShape(EdgeShape shape) {
        this.shape = shape;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
