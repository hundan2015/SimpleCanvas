package thefinal.SceneSystem;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class ActorFactory {
    static Shape createCircle(int height, int width) {
        Ellipse2D res = new Ellipse2D.Double(0, 0, height, width);
        return res;
    }
}
