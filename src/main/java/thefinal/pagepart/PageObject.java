package thefinal.pagepart;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

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
        ShapeList.add(
                new ShapeComponent("R1", new Point2D.Double(2., 2.), new Point2D.Double(10., 10.),
                        new Rectangle(10, 10)));
        ShapeList.add(
                new ShapeComponent("R2", new Point2D.Double(0., 0.), new Point2D.Double(20., 20.),
                        new Rectangle(20, 20)));
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

    class PageMouseMoveListener implements MouseInputListener {
        PageObject pageObject;
        ShapeComponent currentShape;

        PageMouseMoveListener(PageObject pageObject) {
            this.pageObject = pageObject;
        }

        int currentOperation = 2;
        double lastMousePosX = 0, lastMousePosY = 0;

        private void setLastMousePos(double x, double y) {
            lastMousePosX = x;
            lastMousePosY = y;
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            if (currentShape != null) {
                if (currentOperation == 1) {

                } else if (currentOperation == 2) {

                }
                setLastMousePos(e.getX(), e.getY());
                //
                pageObject.thePage.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            /*
             * if (pageObject.getTheShape(e.getX(), e.getY()) != null) {
             * System.out.println("Not Null");
             * }
             */
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // currentShape = pageObject.getTheShape(e.getX(), e.getY());
            if (e.getButton() == 3) {

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            currentShape = pageObject.getTheShape(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }
}

class Page extends JPanel {
    ArrayList<ShapeComponent> shapeArray;

    Page(ArrayList<ShapeComponent> arr) {
        shapeArray = arr;

    }

    @Override
    public void paint(Graphics g) {
        setBackground(Color.white);
        Graphics2D theNewG = (Graphics2D) g;
        theNewG.clearRect(0, 0, getWidth(), getHeight());
        // theNewG.dr
        for (ShapeComponent shapeComponent : shapeArray) {
            AffineTransform saveShit = theNewG.getTransform();
            // theNewG.translate(shapeComponent.lastX, shapeComponent.lastY);
            theNewG.transform(shapeComponent.getShapeTransform());
            theNewG.draw(shapeComponent.getShape());
            theNewG.setTransform(saveShit);
            // theNewG.setTransform(new AffineTransform());
        }
    }
}