package ftc.electronvolts.statemachine;

import java.util.HashMap;
import java.util.Map;

/**
 * This file was made by the electronVolts, FTC team 7393
 */
public class StateMap extends HashMap<StateName, EndCondition> {
    private static final long serialVersionUID = 3897898537401872088L;

    public static class Entry implements Map.Entry<StateName, EndCondition> {
        private final StateName key;
        private EndCondition value;

        public Entry(StateName key, EndCondition value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public StateName getKey() {
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

    public static StateMap of(StateName k1, EndCondition v1) {
        return of(new Entry(k1, v1));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2) {
        return of(new Entry(k1, v1), new Entry(k2, v2));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3, StateName k4, EndCondition v4) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3, StateName k4, EndCondition v4, StateName k5, EndCondition v5) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3, StateName k4, EndCondition v4, StateName k5, EndCondition v5, StateName k6, EndCondition v6) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3, StateName k4, EndCondition v4, StateName k5, EndCondition v5, StateName k6, EndCondition v6, StateName k7, EndCondition v7) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3, StateName k4, EndCondition v4, StateName k5, EndCondition v5, StateName k6, EndCondition v6, StateName k7, EndCondition v7, StateName k8, EndCondition v8) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7), new Entry(k8, v8));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3, StateName k4, EndCondition v4, StateName k5, EndCondition v5, StateName k6, EndCondition v6, StateName k7, EndCondition v7, StateName k8, EndCondition v8, StateName k9, EndCondition v9) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7), new Entry(k8, v8), new Entry(k9, v9));
    }

    public static StateMap of(StateName k1, EndCondition v1, StateName k2, EndCondition v2, StateName k3, EndCondition v3, StateName k4, EndCondition v4, StateName k5, EndCondition v5, StateName k6, EndCondition v6, StateName k7, EndCondition v7, StateName k8, EndCondition v8, StateName k9, EndCondition v9, StateName k10, EndCondition v10) {
        return of(new Entry(k1, v1), new Entry(k2, v2), new Entry(k3, v3), new Entry(k4, v4), new Entry(k5, v5), new Entry(k5, v5), new Entry(k6, v6), new Entry(k7, v7), new Entry(k8, v8), new Entry(k9, v9), new Entry(k10, v10));
    }

    /**
     * @throws IllegalArgumentException if separate entries have the same key
     */
    public static StateMap of(Entry... entries) {
        StateMap stateMap = new StateMap();
        for (Entry entry : entries) {
            if (stateMap.containsKey(entry.key)) {
                throw new IllegalArgumentException("Separate entries cannot have the same key.");
            }
            stateMap.put(entry.key, entry.value);
        }
        return stateMap;
    }
}
