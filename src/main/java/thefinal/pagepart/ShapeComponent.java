package thefinal.pagepart;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class ShapeComponent {
    Shape theShape;
    public String name;
    public double x, y; // Shape Postion
    public double lastX = 0, lastY = 0;
    public double scaleX = 1, scaleY = 1;
    public double lastScaleX = 1, lastScaleY = 1;
    AffineTransform theTransform = new AffineTransform();
    AffineTransform theTransformLast = new AffineTransform();

    // Reflect: 整点基于Anchor的
    public Point2D.Double anchor1 = new Point2D.Double(0., 0.);
    public Point2D.Double anchor2 = new Point2D.Double(10., 10.);

    public AffineTransform getShapeTransform() {
        AffineTransform res = new AffineTransform();
        res.scale(anchor2.x - anchor1.y, anchor2.y - anchor1.y);
        res.translate(anchor1.x, anchor1.y);
        return res;
    }

    public boolean contains(double x, double y) {
        return false;
    }

    public void setScale(double x, double y) {
        lastScaleX = this.scaleX;
        lastScaleY = this.scaleY;
        this.scaleX = x;
        this.scaleY = y;
    }

    public void setPos(double x, double y) {
        lastX = this.x;
        lastY = this.y;
        this.x = x;
        this.y = y;
    }

    public void setLastTransform() {

    }

    public Shape getShape() {
        return theShape;
    }

    // test
    public ShapeComponent(Shape shit) {
        theShape = shit;
        name = "test";
    }
}
