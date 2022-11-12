package thefinal.SceneSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class SequenceGeneratorTest {
        @Test
        public void testGetActorString() {
                String test1 = SequenceGenerator
                                .getActorString(new ActorObject(new Ellipse2D.Double(0, 0, 10, 10), new Point(10, 10),
                                                new Point(10, 10)));
                String res1 = "actorBegin\nclass java.awt.geom.Ellipse2D$Double\n10 10 1.0 1.0 0.0 0.0\nactorEnd\n";
                assertTrue(test1.equals(res1));
                String test2 = SequenceGenerator
                                .getActorString(new ActorObject(new Rectangle.Double(0, 0, 10, 10), new Point(10, 10),
                                                new Point(10, 10)));
                String res2 = "actorBegin\nclass java.awt.geom.Rectangle2D$Double\n10 10 1.0 1.0 0.0 0.0\nactorEnd\n";
                assertTrue(test2.equals(res2));
                String test3 = SequenceGenerator
                                .getActorString(new ActorObject(new Line2D.Double(0, 0, 10, 10), new Point(10, 10),
                                                new Point(10, 10)));
                String res3 = "actorBegin\nclass java.awt.geom.Line2D$Double\n10 10 1.0 1.0 0.0 0.0\nactorEnd\n";
                assertTrue(test3.equals(res3));
                Font f = new Font("Microsoft Yahei", Font.BOLD, 30);
                String text = "SB";
                GlyphVector v = f.createGlyphVector(new JPanel().getFontMetrics(f).getFontRenderContext(), text);
                Shape shape = v.getOutline();
                Rectangle2D shit = shape.getBounds2D();
                String test4 = SequenceGenerator.getActorString(
                                new ActorObject(shape, new Point(10, 10),
                                                new Point((int) shit.getWidth(), (int) shit.getHeight()),
                                                shit.getHeight(), text,
                                                "Microsoft Yahei"));
                String res4 = "actorBegin\nclass java.awt.geom.GeneralPath\n10 10 1.0 1.0 0.0 23.46875\nSB\nactorEnd\n";
                assertTrue(test4.equals(res4));
        }

        @Test
        public void testGetActorJson() {
                ActorObject line = new ActorObject(new Line2D.Double(0, 0, 10, 10), new Point(10, 10),
                                new Point(10, 10));
                ActorObject elapse = new ActorObject(new Ellipse2D.Double(0, 0, 10, 10), new Point(10, 10),
                                new Point(10, 10));
                String res1 = SequenceGenerator.getActorJson(line).toJSONString();
                // {"posX":10,"scaleX":1.0,"posY":10,"scaleY":1.0,"R":0,"B":0,"shape":"class
                // java.awt.geom.Line2D$Double","rotation":0.0,"G":0,"textShift":0.0,"font":""}

        }

        @Test
        public void testGetActorObjectFromJSONString() {
                ActorObject lineOri = new ActorObject(new Line2D.Double(0, 0, 10, 10), new Point(10, 10),
                                new Point(10, 10));
                JSONObject shit = SequenceGenerator.getActorJson(lineOri);
                ActorObject res = SequenceGenerator.getActorObjectFromJSON(shit);
                
                //assertEquals(lineOri, res);
        }

        @Test
        public void testGetStageObjectFromJSON() {

        }

        @Test
        public void testGetStageObjectJSON() {

        }
}
