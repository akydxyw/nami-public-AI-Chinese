package namidevelopment.kiriyaga.api.model.feature;

import java.util.*;

public class FeatureCategory {
    private static final Map<String, FeatureCategory> CATEGORIES = new LinkedHashMap<>();

    private static final List<String> FIXED_ORDER = List.of(
            "Combat", "Exploits", "Miscellaneous", "Movement", "Render", "World", "HUD", "Client", "Other"
    );

    private static final Map<String, String> DISPLAY_NAMES = new HashMap<>();
    static {
        DISPLAY_NAMES.put("Combat", "战斗");
        DISPLAY_NAMES.put("Exploits", "漏洞");
        DISPLAY_NAMES.put("Miscellaneous", "杂项");
        DISPLAY_NAMES.put("Movement", "移动");
        DISPLAY_NAMES.put("Render", "视觉");
        DISPLAY_NAMES.put("World", "世界");
        DISPLAY_NAMES.put("HUD", "HUD");
        DISPLAY_NAMES.put("Client", "客户端");
        DISPLAY_NAMES.put("Other", "其他");
    }

    private final String name;

    private FeatureCategory(String name) {
        this.name = name;
    }

    public static FeatureCategory of(String name) {
        if (name == null || name.isBlank())
            name = "Other";

        for (String fixed : FIXED_ORDER) {
            if (fixed.equalsIgnoreCase(name)) {
                return CATEGORIES.computeIfAbsent(fixed, FeatureCategory::new);
            }
        }

        return CATEGORIES.computeIfAbsent("Other", FeatureCategory::new);
    }

    public static List<FeatureCategory> getAll() {
        List<FeatureCategory> sorted = new ArrayList<>();

        Set<String> added = new HashSet<>();

        for (String key : FIXED_ORDER) {
            FeatureCategory cat = CATEGORIES.get(key);
            if (cat != null) {
                sorted.add(cat);
                added.add(cat.name);
            }
        }

        for (FeatureCategory cat : CATEGORIES.values()) {
            if (!added.contains(cat.name)) {
                sorted.add(cat);
            }
        }

        return sorted;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return DISPLAY_NAMES.getOrDefault(name, name);
    }

    @Override
    public String toString() {
        return name;
    }
}