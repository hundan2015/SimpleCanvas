package thefinal.SceneSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SmallPort extends JPanel {
    StageObject target;

    public StageObject getTarget() {
        return target;
    }

    public SmallPort(StageObject target) {
        setPreferredSize(new Dimension(100, 100));
        this.target = target;
        SmallPort shit = this;
        setBorder(BorderFactory.createLineBorder(Color.gray));
        // Fuck yeah! The lambda expression!
        // With the lambda capture, we can get the smallport itself ticked!
        Runnable ano = () -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                shit.repaint();
            }
        };
        new Thread(ano).start();
        ;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, 100));
        Graphics2D newG = (Graphics2D) g;
        AffineTransform tempTransform = newG.getTransform();
        for (ActorObject actorObject : target.actorList) {
            newG.setColor(actorObject.color);
            newG.transform(actorObject.getActorTransform(0.5));
            newG.draw(actorObject.getShape());
            if (actorObject.isFilled)
                newG.fill(actorObject.getShape());
            newG.setTransform(tempTransform);
        }
    }

}
