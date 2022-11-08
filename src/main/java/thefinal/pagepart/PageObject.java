package thefinal.pagepart;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import thefinal.pagepart.ShapePart.RectangleComponent;
import thefinal.pagepart.ShapePart.ShapeComponent;

public class PageObject {
    ArrayList<ShapeComponent> ShapeList;
    public Page thePage;

    public PageObject(Container container) {
        ShapeList = new ArrayList<>();
        thePage = new Page(ShapeList);
        thePage.setBorder(BorderFactory.createBevelBorder(0));
        thePage.setPreferredSize(new Dimension(100, 100));
        PageMouseMoveListener theListener = new PageMouseMoveListener(this);
        thePage.addMouseMotionListener(theListener);
        thePage.addMouseListener(theListener);
        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 7;
        c.gridwidth = 11;
        c.gridx = 1;
        c.gridy = 1;
        container.add(thePage, c);

        // test part
        /* ShapeList.add(
                new ShapeComponent("R1", new Point2D.Double(2., 2.), new Point2D.Double(12., 12.),
                        new Rectangle(10, 10)));
        ShapeList.add(
                new ShapeComponent("R2", new Point2D.Double(0., 0.), new Point2D.Double(20., 20.),
                        new Rectangle(20, 20))); */
        ShapeList.add(
                new RectangleComponent("Rshit", new Point2D.Double(30., 30.), new Point2D.Double(40., 40.)));
    }

    public ShapeComponent getTheShape(double x, double y) {
        for (ShapeComponent shapeComponent : ShapeList) {
            if (shapeComponent.contains(x, y)) {
                System.out.println("Get the shape " + shapeComponent.name);
                return shapeComponent;
            }
        }
        System.out.println("No Shape here.");
        return null;
    }

    public void deleteTheShape(ShapeComponent theShape) {
        for (ShapeComponent shapeComponent : ShapeList) {
            if (shapeComponent == theShape) {
                ShapeList.remove(shapeComponent);
                return;
            }
        }
        System.out.println("WARNING: No this shape!");
    }

    public void addTheShape(ShapeComponent theShape) {
        ShapeList.add(theShape);
    }

    public void upTheShape(ShapeComponent theShape) {
        int index = -1;
        for (ShapeComponent shapeComponent : ShapeList) {
            if (theShape == shapeComponent) {
                index = ShapeList.indexOf(shapeComponent);
            }
        }
        if (index != -1 && index != 0) {
            ShapeComponent tempShape = ShapeList.get(index - 1);
            ShapeList.set(index, tempShape);
            ShapeList.set(index - 1, theShape);
        }
    }

    public void downTheShape(ShapeComponent theShape) {
        int index = -1;
        for (ShapeComponent shapeComponent : ShapeList) {
            if (theShape == shapeComponent) {
                index = ShapeList.indexOf(shapeComponent);
            }
        }
        if (index != -1 && index != ShapeList.size() - 1) {
            ShapeComponent tempShape = ShapeList.get(index + 1);
            ShapeList.set(index, tempShape);
            ShapeList.set(index + 1, theShape);
        }
    }
}