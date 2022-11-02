package thefinal.pagepart.ShapePart;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;

public class RectangleComponent extends ShapeComponent {

    public RectangleComponent(String name, Double anchor1, Double anchor2) {
        super(name, anchor1, anchor2,
                new Rectangle((int) anchor2.x - (int) anchor1.x, (int) anchor2.y - (int) anchor1.y));
    }

    @Override
    public void getShapeChanged() {
        
    }
}
