package thefinal.SceneSystem;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class ActorObject {
    public Point transform;
    public Point.Double scale;
    public double rotation;
    Point size;
    Shape shape;

    /**
     * @param shape     The shape to render.
     * @param transform The postion of the shape. Unity style huh? Don't say that
     *                  you don't know that engine.
     * @param size      Shape's size. For the basic Actor, the size is unknown. So
     *                  for the user need to define the size to make sure that the
     *                  double anchors position and
     *                  other fucking shit are fucking right.
     */
    public ActorObject(Shape shape, Point transform, Point size) {
        this.shape = shape;
        this.transform = transform;
        this.size = size;
        this.scale = new Point.Double(1, 1);
        rotation = 0;
    }

    public Shape getShape() {
        return shape;
    }

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

    public AffineTransform getActorTransform() {
        AffineTransform res = new AffineTransform();

        res.translate(transform.x
                + size.x * scale.x / 2, transform.y + size.y * scale.y / 2);
        res.rotate(rotation);
        // To make sure the rotation does'nt change the shape's center.
        // But it's also FUCKING WEIRD!!!!
        res.translate(-size.x * scale.x / 2, -size.y * scale.y / 2);
        res.scale(scale.x, scale.y);
        // Fucking weird. Why the scale is after translate??
        return res;
    }
}
