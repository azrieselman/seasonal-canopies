package dev.tyty.seasonalcanopies.season;

import dev.tyty.seasonalcanopies.SeasonalCanopiesConfig;
import dev.tyty.seasonalcanopies.compat.SereneSeasonsSeason;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

public final class SeasonSource {
    private static final boolean SERENE_SEASONS = ModList.get().isLoaded("sereneseasons");

    private SeasonSource() {
    }

    public static SeasonKind get(Level level) {
        if (SERENE_SEASONS) {
            return SereneSeasonsSeason.get(level);
        }
        return calendar(level);
    }

    public static SeasonKind calendar(Level level) {
        int days = SeasonalCanopiesConfig.FALLBACK_SEASON_DAYS.get();
        return SeasonKind.byIndex(SeasonMath.seasonIndex(level.getDayTime(), days));
    }
}
