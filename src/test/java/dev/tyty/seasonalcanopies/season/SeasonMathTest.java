package dev.tyty.seasonalcanopies.season;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SeasonMathTest {
    @Test
    void calendarWalksSpringSummerAutumnWinter() {
        int days = 7;
        assertEquals(0, SeasonMath.seasonIndex(0L, days));
        assertEquals(1, SeasonMath.seasonIndex(24000L * 7L, days));
        assertEquals(2, SeasonMath.seasonIndex(24000L * 14L, days));
        assertEquals(3, SeasonMath.seasonIndex(24000L * 21L, days));
        assertEquals(0, SeasonMath.seasonIndex(24000L * 28L, days));
    }

    @Test
    void dayStillInsideASeasonDoesNotAdvance() {
        assertEquals(0, SeasonMath.seasonIndex(24000L * 6L + 12000L, 7));
    }

    @Test
    void zeroStrengthKeepsTheOriginalColor() {
        int oak = 0x48B518;
        assertEquals(oak, SeasonMath.tint(oak, SeasonMath.AUTUMN_TARGET, SeasonMath.blendFor(SeasonKind.AUTUMN, 0.0F)));
    }

    @Test
    void autumnShiftsFurtherThanSummer() {
        assertTrue(SeasonMath.blendFor(SeasonKind.AUTUMN, 1.0F) > SeasonMath.blendFor(SeasonKind.SUMMER, 1.0F));
        int oak = 0x48B518;
        int autumn = SeasonMath.tint(oak, SeasonMath.AUTUMN_TARGET, SeasonMath.blendFor(SeasonKind.AUTUMN, 0.75F));
        int summer = SeasonMath.tint(oak, SeasonMath.SUMMER_TARGET, SeasonMath.blendFor(SeasonKind.SUMMER, 0.75F));
        assertTrue(red(autumn) > red(oak));
        assertTrue(red(autumn) > red(summer));
    }

    @Test
    void autumnShedsMoreThanTheOtherSeasons() {
        float autumn = SeasonMath.shedChance(SeasonKind.AUTUMN);
        assertTrue(autumn > SeasonMath.shedChance(SeasonKind.SPRING));
        assertTrue(autumn > SeasonMath.shedChance(SeasonKind.SUMMER));
        assertTrue(autumn > SeasonMath.shedChance(SeasonKind.WINTER));
        assertTrue(SeasonMath.shedChance(SeasonKind.SUMMER) < SeasonMath.shedChance(SeasonKind.SPRING));
    }

    private static int red(int color) {
        return (color >> 16) & 255;
    }
}
