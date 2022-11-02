package thefinal.SceneSystem;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class ActorObject {
    public Point transform;
    public Point scale;
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
    ActorObject(Shape shape, Point transform, Point size) {
        this.shape = shape;
        this.transform = transform;
        this.size = size;
        this.scale = new Point(1, 2);
    }

    public Shape getShape() {
        return shape;
    }

    boolean isContain(Point mousePosition) {
        int x = mousePosition.x;
        int y = mousePosition.y;
        Point anchor1 = transform;
        Point anchor2 = new Point(transform.x + scale.x * size.x, transform.y + scale.y * size.y);
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
        res.translate(transform.x, transform.y);
        res.scale(scale.x, scale.y); // Fucking weird. Why the scale is after translate??
        return res;
    }
}
