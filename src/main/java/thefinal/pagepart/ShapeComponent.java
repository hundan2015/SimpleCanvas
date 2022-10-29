package thefinal.pagepart;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class ShapeComponent {
    Shape theShape;
    public String name;
    double defaultScaleX, defaultScaleY;
    // Reflect: 整点基于Anchor的
    public Point2D.Double anchor1 = new Point2D.Double(0., 0.);
    public Point2D.Double anchor2 = new Point2D.Double(10., 10.);

    public ShapeComponent(String name, Point2D.Double anchor1, Point2D.Double anchor2, Shape shape) {
        this.name = name;
        this.anchor1 = anchor1;
        this.anchor2 = anchor2;
        this.theShape = shape;
        defaultScaleX = Math.abs(anchor1.x - anchor2.x);
        defaultScaleY = Math.abs(anchor1.y - anchor2.y);
    }

    /**
     * @return 返回对于一个基本形状形变后的样子，帮助画笔在正确的位置画出图形
     *         需要注意的是，旋转->放缩->移动
     */
    public AffineTransform getShapeTransform() {
        AffineTransform res = new AffineTransform();
        // 如此的设计可以保证跟手
        res.scale((anchor2.x - anchor1.y) / defaultScaleX, (anchor2.y - anchor1.y) / defaultScaleY);
        res.translate(anchor1.x, anchor1.y);
        return res;
    }

    /**
     * @param x 鼠标X坐标
     * @param y 鼠标Y坐标
     * @return 返回鼠标是否位于当前位置。
     */
    public boolean contains(double x, double y) {
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

    public Shape getShape() {
        return theShape;
    }
    // TODO:能不能实现一个Shape的管理类？使得shape可以依靠Anchor进行操作？
    // TODO:同时希望这个模型是MVVM的，即通过ViewModel来更新数据。
}
