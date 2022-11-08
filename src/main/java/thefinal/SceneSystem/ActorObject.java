package thefinal.SceneSystem;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import thefinal.GlobalModel;

public class ActorObject {
    public Point transform;
    public Point.Double scale;
    public double rotation;
    Point size;
    private Shape shape;
    public double textShift = 0;
    public Color color;
    public boolean isFilled = false;

    /**
     * @param shape     The shape to render.
     * @param transform The postion of the shape. <b>Unity style </b>huh? Don't say
     *                  that you don't know that engine.
     * @param size      Shape's size. For the basic Actor, the size is unknown. So
     *                  for the user need to define the size to make sure that the
     *                  double anchors position and
     *                  other fucking shit are fucking right.
     */
    public ActorObject(Shape shape, Point transform, Point size) {
        createObject(shape, transform, size);
    }

    private void createObject(Shape shape, Point transform, Point size) {
        this.shape = shape;
        this.transform = transform;
        this.size = size;
        this.scale = new Point.Double(1, 1);
        rotation = 0;
        textShift = 0;
        color = GlobalModel.shapeColor;
    }

    public ActorObject(Shape shape, Point transform, Point size, double shift) {
        createObject(shape, transform, size);
        textShift = shift;
    }

    public Shape getShape() {
        return shape;
    }

    /**
     * PRO TIP:
     * <p>
     * In this section, we don't try to get the rotation in this contain part! So
     * just guide the user to tap the center of the shape.
     * 
     * @param mousePosition The mouse position to test.
     */
    boolean isContain(Point mousePosition) {
        int x = mousePosition.x;
        int y = mousePosition.y;
        Point anchor1 = transform;
        Point anchor2 = new Point((int) (transform.x + scale.x * size.x), (int) (transform.y + scale.y * size.y));
        if (anchor1.x > anchor2.x && anchor1.y > anchor2.y) {
            return x <= anchor1.x && x >= anchor2.x && y <= anchor1.y && y >= anchor2.y;
        } else if (anchor1.x < anchor2.x && anchor1.y > anchor2.y) {
            return x <= anchor2.x && x >= anchor1.x && y <= anchor1.y && y >= anchor2.y;
        } else if (anchor1.x < anchor2.x && anchor1.y < anchor2.y) {
            return x <= anchor2.x && x >= anchor1.x && y <= anchor2.y && y >= anchor1.y;
        } else {
            return x <= anchor1.x && x >= anchor2.x && y <= anchor2.y && y >= anchor1.y;
        }
    }

    /**
     * A Fucking function with <b>SO MANY WERID</b> way to deal the problem.
     * 
     * @return The position(including rotation) for the Graphic2D to paint shape
     *         correctly.
     */
    public AffineTransform getActorTransform() {
        AffineTransform res = new AffineTransform();
        res.translate(0, textShift * scale.y);
        res.translate(transform.x
                + size.x * scale.x / 2, transform.y + size.y * scale.y / 2);
        // Rotation transfer 1 degree = 57.3 degree
        res.rotate(rotation / 57.3);
        // To make sure the rotation does'nt change the shape's center.
        // But it's also FUCKING WEIRD!!!!
        res.translate(-size.x * scale.x / 2, -size.y * scale.y / 2);
        res.scale(scale.x, scale.y);
        // Fucking weird. Why the scale is after translate??
        return res;
    }

    public AffineTransform getActorTransform(double smallscale) {
        AffineTransform res = new AffineTransform();
        res.translate(0, textShift * scale.y * smallscale);
        res.translate(transform.x * smallscale
                + size.x * scale.x * smallscale / 2, transform.y * smallscale + size.y * scale.y * smallscale / 2);
        // Rotation transfer 1 degree = 57.3 degree
        res.rotate(rotation / 57.3);
        // To make sure the rotation does'nt change the shape's center.
        // But it's also FUCKING WEIRD!!!!
        res.translate(-size.x * scale.x * smallscale / 2, -size.y * scale.y * smallscale / 2);
        res.scale(scale.x * smallscale, scale.y * smallscale);
        // Fucking weird. Why the scale is after translate??
        return res;
    }
}
