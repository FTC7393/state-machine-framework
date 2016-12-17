package ftc.electronvolts.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This file was made by the electronVolts, FTC team 7393
 */
public class StateMap extends HashMap<Enum<?>, EndCondition> {
    private static final long serialVersionUID = 3897898537401872088L;

    public static class Entry implements Map.Entry<Enum<?>, EndCondition> {
        private final Enum<?> key;
        private EndCondition value;

        public Entry(Enum<?> key, EndCondition value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Enum<?> getKey() {
            return key;
        }

        @Override
        public EndCondition getValue() {
            return value;
        }

        @Override
        public EndCondition setValue(EndCondition value) {
            EndCondition old = this.value;
            this.value = value;
            return old;
        }
    }

    public static StateMap of() {
        return new StateMap();
    }

    public static StateMap of(Enum<?> k1, EndCondition v1) {
        return of(new Entry(k1, v1));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2) {
        return of(new Entry(k1, v1), new Entry(k2, v2));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3, Enum<?> k4, EndCondition v4) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3, Enum<?> k4, EndCondition v4, Enum<?> k5, EndCondition v5) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3, Enum<?> k4, EndCondition v4, Enum<?> k5, EndCondition v5, Enum<?> k6, EndCondition v6) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3, Enum<?> k4, EndCondition v4, Enum<?> k5, EndCondition v5, Enum<?> k6, EndCondition v6, Enum<?> k7, EndCondition v7) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3, Enum<?> k4, EndCondition v4, Enum<?> k5, EndCondition v5, Enum<?> k6, EndCondition v6, Enum<?> k7, EndCondition v7, Enum<?> k8, EndCondition v8) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7), new Entry(k8, v8));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3, Enum<?> k4, EndCondition v4, Enum<?> k5, EndCondition v5, Enum<?> k6, EndCondition v6, Enum<?> k7, EndCondition v7, Enum<?> k8, EndCondition v8, Enum<?> k9, EndCondition v9) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7), new Entry(k8, v8), new Entry(k9, v9));
    }

    public static StateMap of(Enum<?> k1, EndCondition v1, Enum<?> k2, EndCondition v2, Enum<?> k3, EndCondition v3, Enum<?> k4, EndCondition v4, Enum<?> k5, EndCondition v5, Enum<?> k6, EndCondition v6, Enum<?> k7, EndCondition v7, Enum<?> k8, EndCondition v8, Enum<?> k9, EndCondition v9, Enum<?> k10, EndCondition v10) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7), new Entry(k8, v8), new Entry(k9, v9), new Entry(k10, v10));
    }

    /**
     * if separate entries have the same key, they are combined into one entry
     * by using EndConditions.any()
     */
    public static StateMap of(Entry... entries) {
        //create a map that relates each next state with a list of end conditions
        Map<Enum<?>, List<EndCondition>> stateListMap = new HashMap<>();
        for (Entry entry : entries) {
            //if the next state name already has an end condition associated with it
            if (stateListMap.containsKey(entry.key)) {
                //add another one
                stateListMap.get(entry.key).add(entry.value);
            } else {
                //otherwise, create a list with one element and add it to the map
                List<EndCondition> list = new ArrayList<EndCondition>();
                list.add(entry.value);
                stateListMap.put(entry.key, list);
            }
        }
        
        //now we will convert the stateListMap into a StateMap by combining all the EndConditions
        StateMap stateMap = new StateMap();
        for (Map.Entry<Enum<?>, List<EndCondition>> entry : stateListMap.entrySet()) {
            Enum<?> stateName = entry.getKey();
            //if the list has one item
            List<EndCondition> endConditions = entry.getValue();
            if (endConditions.size() == 1) {
                //add only it to the stateMap
                stateMap.put(stateName, endConditions.get(0));
            } else {
                //otherwise combine all the items in an OR configuration
                stateMap.put(stateName, EndConditions.any(endConditions));
            }
        }
        return stateMap;
    }
}
