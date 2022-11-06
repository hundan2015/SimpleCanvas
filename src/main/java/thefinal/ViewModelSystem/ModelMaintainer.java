package thefinal.ViewModelSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

// MVVM Test
public class ModelMaintainer {
    HashMap<Object, ArrayList<Object>> relationTree;
    ArrayList<Function> contrast;

    ModelMaintainer() {
        relationTree = new HashMap<>();
    }

    void tick(Object object) {

    }

}
