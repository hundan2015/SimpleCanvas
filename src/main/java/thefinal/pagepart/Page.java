package thefinal.pagepart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import thefinal.pagepart.ShapePart.ShapeComponent;

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
            shapeComponent.getShapeChanged();
            AffineTransform saveShit = theNewG.getTransform();
            // theNewG.translate(shapeComponent.lastX, shapeComponent.lastY);
            theNewG.transform(shapeComponent.getShapeTransform());
            theNewG.draw(shapeComponent.getShape());
            theNewG.setTransform(saveShit);
            // theNewG.setTransform(new AffineTransform());
        }
    }
}