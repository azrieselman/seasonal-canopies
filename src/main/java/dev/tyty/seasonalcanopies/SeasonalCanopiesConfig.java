package dev.tyty.seasonalcanopies;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class SeasonalCanopiesConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue TINT_STRENGTH = BUILDER
            .comment("How strongly Dynamic Trees leaves shift toward the season color.", "0 leaves the original color. 1 uses the full seasonal blend.")
            .defineInRange("tintStrength", 0.75D, 0.0D, 1.0D);

    public static final ModConfigSpec.DoubleValue PARTICLE_AMOUNT = BUILDER
            .comment("Multiplier for leaf particles shed by Dynamic Trees foliage.", "Autumn already sheds more than the other seasons. 0 disables the extra particles.")
            .defineInRange("particleAmount", 1.0D, 0.0D, 4.0D);

    public static final ModConfigSpec.IntValue FALLBACK_SEASON_DAYS = BUILDER
            .comment("In-game days per season when Serene Seasons is not installed.", "Day 0 starts in spring, then summer, autumn, and winter.")
            .defineInRange("fallbackSeasonDays", 7, 1, 1000);

    static final ModConfigSpec SPEC = BUILDER.build();

    private SeasonalCanopiesConfig() {
    }
}
