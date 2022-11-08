package thefinal.ViewModelSystem;

import java.util.ArrayList;
import java.util.HashMap;

// MVVM Test
// A data relation to other datas. 
// So if a data changed,the relation should be change immediatly.
public class ModelMaintainer {
    static HashMap<Object, ArrayList<ModelConstract>> relationTree = new HashMap<>();

    // Model maintainer is a manager class to maintain the relation of different
    // data.

    static void tick(Object object) {
        ArrayList<ModelConstract> contrast = relationTree.get(object);
        for (ModelConstract modelConstract : contrast) {
            modelConstract.updateModel();
        }
    }

    static void addContrast(Object object, ModelConstract modelConstract) {
        ArrayList<ModelConstract> contrast = relationTree.get(modelConstract);
        if (contrast == null) {
            relationTree.put(object, new ArrayList<ModelConstract>());
            contrast = relationTree.get(object);
        }
        contrast.add(modelConstract);
    }
}