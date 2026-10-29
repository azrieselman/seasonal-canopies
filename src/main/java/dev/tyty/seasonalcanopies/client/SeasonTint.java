package dev.tyty.seasonalcanopies.client;

import dev.tyty.seasonalcanopies.SeasonalCanopiesConfig;
import dev.tyty.seasonalcanopies.season.SeasonKind;
import dev.tyty.seasonalcanopies.season.SeasonMath;

public final class SeasonTint {
    private SeasonTint() {
    }

    public static int apply(int baseColor, SeasonKind season) {
        float strength = SeasonalCanopiesConfig.TINT_STRENGTH.get().floatValue();
        float blend = SeasonMath.blendFor(season, strength);
        return SeasonMath.tint(baseColor, SeasonMath.targetColor(season), blend);
    }
}
