package ui;


import java.util.ArrayList;
import java.util.List;

public class Plot {

    public List<Component> plotAll(List<? extends Plottable> sourceBoxes) {
        List<Component> allPlottedComponents = new ArrayList<Component>();
        for (Plottable sourceBox : sourceBoxes) {
            allPlottedComponents.add(sourceBox);
        }
        return allPlottedComponents;
    }
}
