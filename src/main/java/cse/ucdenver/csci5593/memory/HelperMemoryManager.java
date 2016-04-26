package cse.ucdenver.csci5593.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by max on 4/26/16.
 */
public class HelperMemoryManager extends MemoryManager {
    public HelperMemoryManager(int slots, HashMap<Integer, Integer> values, List<MemoryModule> modules, RegisterMemoryModule registers) {
        super(slots);

        this.values = (HashMap<Integer, Integer>)values.clone();
        this.registers = registers.clone();

        List<MemoryModule> newModules = new ArrayList<>(modules.size());
        System.out.println(modules);
        try {

            newModules.add(modules.get(0).getClass().newInstance());
            newModules.add(modules.get(1).getClass().newInstance());
            newModules.add(modules.get(2));
            newModules.add(modules.get(3));
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.println("Could not duplicate memory modules for helper thread");
        }
    }





}
