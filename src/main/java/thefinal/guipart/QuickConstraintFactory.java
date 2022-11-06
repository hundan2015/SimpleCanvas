package thefinal.guipart;

import java.awt.GridBagConstraints;
import java.util.Vector;

public class QuickConstraintFactory {
    int rowCount = 0;
    Vector<Integer> rowMat = new Vector<>();

    /**
     * A factory with simple state control.
     * Offering forward and backward line control, and a get function.
     * Then use the built-in state control, to get she fucking state.
     * Can offer you a great experience if you have to add something above your
     * frame again and agian. So it's just a lazy dog action. It's easier to just
     * modify the constraint object.
     * <p>
     * <b>(IF IT'S NOT TOO MUCH LOL)</b>
     */
    QuickConstraintFactory() {
        rowCount = 0;
        rowMat = new Vector<>();
        rowMat.add(0);
    }

    void forwardRow() {
        rowCount++;
        if (rowMat.size() == rowCount) {
            rowMat.add(0);
        }
    }

    void backwardRow() {
        rowCount--;
    }

    /**
     * @param width
     * @return A constraint created by state machine.
     */
    GridBagConstraints getConstraints(int width) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = rowCount;
        c.gridx = rowMat.get(rowCount);
        c.gridheight = 1;
        c.gridwidth = width;
        rowMat.set(rowCount, rowMat.get(rowCount) + width);
        // HORIZONTAL hard code version.
        c.fill = 2;
        return c;
    }
}
