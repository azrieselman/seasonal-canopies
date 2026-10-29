package dev.tyty.seasonalcanopies.season;

public enum SeasonKind {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;

    public static SeasonKind byIndex(int index) {
        SeasonKind[] values = values();
        int wrapped = Math.floorMod(index, values.length);
        return values[wrapped];
    }
}
