package thefinal.pagepart;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.event.MouseInputListener;

import thefinal.pagepart.ShapePart.ShapeComponent;

class PageMouseMoveListener implements MouseInputListener {
    PageObject pageObject;
    ShapeComponent currentShape;
    Point2D.Double currentAnchor;

    PageMouseMoveListener(PageObject pageObject) {
        this.pageObject = pageObject;
    }

    int currentOperation = 1;
    double lastMousePosX = 0, lastMousePosY = 0;

    private void setLastMousePos(double x, double y) {
        lastMousePosX = x;
        lastMousePosY = y;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (currentShape != null) {
            if (currentOperation == 1) {
                if (currentAnchor != null) {
                    currentAnchor.x = e.getX();
                    currentAnchor.y = e.getY();
                }
            } else if (currentOperation == 2) {

            }
            setLastMousePos(e.getX(), e.getY());
            //
            pageObject.thePage.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentShape = pageObject.getTheShape(e.getX(), e.getY());
        if (currentShape != null) {
            if (e.getButton() == 1) {
                currentAnchor = currentShape.getAnchor(e.getPoint());
            }
        } else {
            currentAnchor = null;
        }
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