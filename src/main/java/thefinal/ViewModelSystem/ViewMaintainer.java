package thefinal.ViewModelSystem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A singliton class to maintain the view.
 */
public class ViewMaintainer {
    static HashMap<Object, ArrayList<ViewConstract>> modelViewRelationTree = new HashMap<Object, ArrayList<ViewConstract>>();

    /**
     * Tick all the event to upgrade all the fucking view.
     * 
     * @param object The object to fuck up every thing.
     */
    public static void updateView(Object object) {
        ArrayList<ViewConstract> viewConstracts = modelViewRelationTree.get(object);
        if (viewConstracts == null) {
            // FIXME: A super error of the hashmap. We can't change the object!
            modelViewRelationTree.put(object, new ArrayList<ViewConstract>());
            viewConstracts = modelViewRelationTree.get(object);
        }
        for (ViewConstract viewConstract : viewConstracts) {
            viewConstract.updateView();
        }
    }

    public static ArrayList<ViewConstract> preToUpdateView(Object object) {
        ArrayList<ViewConstract> tList = modelViewRelationTree.get(object);
        modelViewRelationTree.remove(object);
        return modelViewRelationTree.get(tList);
    }

    /**
     * A method to bind view to a model.You need to offer the rules of data and
     * view.
     * <p>
     * For Java, it's not visible to know how to upgrade the view. Therefore you
     * need to offer the viewConstract to define it.
     * 
     * @param object        The model relation to the view.
     * @param viewConstract The method interface define the how the model upgrade
     *                      the view.
     */
    public static void bindView(Object object, ViewConstract viewConstract) {
        ArrayList<ViewConstract> viewConstracts = modelViewRelationTree.get(object);
        if (viewConstracts == null) {
            modelViewRelationTree.put(object, new ArrayList<ViewConstract>());
            viewConstracts = modelViewRelationTree.get(object);
        }
        viewConstracts.add(viewConstract);
    }
}