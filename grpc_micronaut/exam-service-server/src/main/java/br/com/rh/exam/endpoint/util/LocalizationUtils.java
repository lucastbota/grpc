package br.com.rh.exam.endpoint.util;

import java.util.Set;

public class LocalizationUtils {
    private static final Set<String> AVAILABLE_PLACES = Set.of("648 Lindbergh Trail",
            "700 Autumn Leaf Court",
            "318 Morning Road",
            "9 Mallard Crossing",
            "595 Grim Road",
            "72 Kenwood Circle",
            "9993 Heath Court",
            "28 Burning Wood Alley",
            "149 Gerald Point",
            "18731 Washington Hill");

    public static String getPlace() {
        return AVAILABLE_PLACES.stream().findFirst().orElse("046 Donald Road");
    }
}
