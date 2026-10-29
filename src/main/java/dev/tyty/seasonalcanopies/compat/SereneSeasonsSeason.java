package dev.tyty.seasonalcanopies.compat;

import dev.tyty.seasonalcanopies.SeasonalCanopies;
import dev.tyty.seasonalcanopies.season.SeasonKind;
import dev.tyty.seasonalcanopies.season.SeasonSource;
import net.minecraft.world.level.Level;
import sereneseasons.api.season.ISeasonState;
import sereneseasons.api.season.Season;
import sereneseasons.api.season.SeasonHelper;

/**
 * Loaded only when Serene Seasons is installed. Keeps that mod off the hard classpath at runtime.
 */
public final class SereneSeasonsSeason {
    private static boolean failed;

    private SereneSeasonsSeason() {
    }

    public static SeasonKind get(Level level) {
        if (failed) {
            return SeasonSource.calendar(level);
        }
        try {
            if (SeasonHelper.dataProvider == null) {
                return SeasonSource.calendar(level);
            }
            ISeasonState state = SeasonHelper.getSeasonState(level);
            if (state == null || state.getSeason() == null) {
                return SeasonSource.calendar(level);
            }
            return map(state.getSeason());
        } catch (Throwable throwable) {
            failed = true;
            SeasonalCanopies.LOGGER.warn("Could not read the Serene Seasons calendar. Using the day count instead.", throwable);
            return SeasonSource.calendar(level);
        }
    }

    private static SeasonKind map(Season season) {
        return switch (season) {
            case SPRING -> SeasonKind.SPRING;
            case SUMMER -> SeasonKind.SUMMER;
            case AUTUMN -> SeasonKind.AUTUMN;
            case WINTER -> SeasonKind.WINTER;
        };
    }
}
