package cse.ucdenver.csci5593.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by max on 4/26/16.
 */
public class HelperMemoryManager extends MemoryManager {
    public HelperMemoryManager(int slots, TreeMap<Integer, Integer> values, List<MemoryModule> modules, RegisterMemoryModule registers) {
        super(slots);

        this.values = (TreeMap<Integer, Integer>)values.clone();
        this.registers = registers.clone();

        List<MemoryModule> newModules = new ArrayList<>(modules.size());

        newModules.add(modules.get(0).duplicate());
        newModules.add(modules.get(1).duplicate());
        newModules.add(modules.get(2));
        newModules.add(modules.get(3));

        this.modules = newModules;
    }


}
