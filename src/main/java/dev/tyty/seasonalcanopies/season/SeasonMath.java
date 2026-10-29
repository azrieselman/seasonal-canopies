package dev.tyty.seasonalcanopies.season;

/**
 * Season calendar and color blend. Kept free of Minecraft types so it can be tested on its own.
 */
public final class SeasonMath {
    public static final int SPRING_TARGET = 0xA4C85A;
    public static final int SUMMER_TARGET = 0x2F7D32;
    public static final int AUTUMN_TARGET = 0xD2691E;
    public static final int WINTER_TARGET = 0xA7A89A;

    private SeasonMath() {
    }

    public static int seasonIndex(long dayTime, int seasonDays) {
        int length = Math.max(1, seasonDays);
        long day = Math.floorDiv(dayTime, 24000L);
        if (day < 0L) {
            day = 0L;
        }
        return (int) ((day / length) % 4L);
    }

    public static int targetColor(SeasonKind season) {
        return switch (season) {
            case SPRING -> SPRING_TARGET;
            case SUMMER -> SUMMER_TARGET;
            case AUTUMN -> AUTUMN_TARGET;
            case WINTER -> WINTER_TARGET;
        };
    }

    /**
     * Autumn moves the furthest. Summer only deepens the existing green.
     */
    public static float blendFor(SeasonKind season, float strength) {
        float clamped = Math.max(0.0F, Math.min(1.0F, strength));
        float weight = switch (season) {
            case SPRING -> 0.45F;
            case SUMMER -> 0.25F;
            case AUTUMN -> 0.82F;
            case WINTER -> 0.70F;
        };
        return clamped * weight;
    }

    public static int tint(int baseColor, int targetColor, float blend) {
        float t = Math.max(0.0F, Math.min(1.0F, blend));
        int br = (baseColor >> 16) & 255;
        int bg = (baseColor >> 8) & 255;
        int bb = baseColor & 255;
        int tr = (targetColor >> 16) & 255;
        int tg = (targetColor >> 8) & 255;
        int tb = targetColor & 255;
        int r = Math.round(br + (tr - br) * t);
        int g = Math.round(bg + (tg - bg) * t);
        int b = Math.round(bb + (tb - bb) * t);
        return (r << 16) | (g << 8) | b;
    }

    public static float shedChance(SeasonKind season) {
        return switch (season) {
            case SPRING -> 0.020F;
            case SUMMER -> 0.008F;
            case AUTUMN -> 0.070F;
            case WINTER -> 0.025F;
        };
    }
}
