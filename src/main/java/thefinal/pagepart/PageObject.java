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
        ShapeList.add(new ShapeComponent(new Rectangle(10, 10)));
        ShapeList.add(new ShapeComponent(new Rectangle(20, 20)));
    }

    public ShapeComponent getTheShape(double x, double y) {
        for (ShapeComponent shapeComponent : ShapeList) {
            if (shapeComponent.getShape().contains((x - shapeComponent.x) / shapeComponent.scaleX,
                    (y - shapeComponent.y) / shapeComponent.scaleY)) {
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

    class PageMouseMoveListener implements MouseMotionListener, MouseInputListener {
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

                    currentShape.theTransform.translate(currentShape.x - currentShape.lastX,
                            currentShape.y - currentShape.lastY);
                    currentShape.setPos(e.getX(), e.getY());

                    /*
                     * currentShape.theTransform.translate(e.getX() - lastMousePosX,
                     * e.getY() - lastMousePosY);
                     * currentShape.setPos(e.getX(), e.getY());
                     */
                    // TODO:使用方框来进行大小缩放
                } else if (currentOperation == 2) {
                    currentShape.setScale(e.getX(), e.getY());
                    currentShape.theTransform.scale((currentShape.scaleX - currentShape.lastScaleX) * 0.1 + 1,
                            (currentShape.scaleY - currentShape.lastScaleY) * 0.1 + 1);
                }
                setLastMousePos(e.getX(), e.getY());
                //
                pageObject.thePage.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            // TODO Auto-generated method stub
            /*
             * if (pageObject.getTheShape(e.getX(), e.getY()) != null) {
             * System.out.println("Not Null");
             * }
             */
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

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
            theNewG.transform(shapeComponent.theTransform);
            theNewG.draw(shapeComponent.getShape());
            theNewG.setTransform(saveShit);
            // theNewG.setTransform(new AffineTransform());
        }
    }
}