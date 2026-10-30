# Seasonal Canopies

A small NeoForge 1.21.1 mod. Dynamic Trees leaves change color with the season and shed leaf particles. Autumn sheds the most. The other seasons shed a little.

Vanilla leaves, grass, and crops are not recolored. Tree growth, drops, and worldgen are left alone. Only overworld canopies change. Nether, End, and other dimensions are left as Dynamic Trees drew them.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.1 or newer
- Java 21

Dynamic Trees is optional. Without it, the mod still loads and has nothing to tint. Serene Seasons is optional. When it is installed, its current season is used. When it is not, the mod counts in-game days: spring, summer, autumn, then winter.

Neither mod is bundled in this jar.

## Install

1. Install NeoForge for Minecraft 1.21.1.
2. Put `seasonal-canopies` into the `mods` folder.
3. Install [Dynamic Trees](https://modrinth.com/mod/dynamictrees) for NeoForge 1.21.1 if you want to see the effect.
4. Optionally install [Serene Seasons](https://modrinth.com/mod/serene-seasons) for NeoForge 1.21.1.

## Try a season

With Serene Seasons, op yourself and run:

```
/season set mid_autumn
```

`early_spring`, `mid_summer`, and `mid_winter` work the same way.

Without Serene Seasons, each season lasts `fallbackSeasonDays` in-game days (7 by default), and a new world starts in spring. Move one season with:

```
/time add 168000
```

That is 7 days. Run it again for summer, autumn, then winter. Or set `fallbackSeasonDays` to `1` in the client config and use `/time add 24000`.

## Config

Client config: `config/seasonalcanopies-client.toml`

- `tintStrength` — `0` to `1`, default `0.75`
- `particleAmount` — `0` to `4`, default `1`. Autumn is still the heavy shed.
- `fallbackSeasonDays` — days per season when Serene Seasons is absent, default `7`

The same screen is under Mods, Seasonal Canopies, Config.

## Build

Dynamic Trees 1.7.2 and Serene Seasons 10.1.0.9 are `compileOnly` dependencies from Modrinth. They are not shaded into the jar.

```
bash gradlew build
```

On Windows, run `gradlew.bat build`. The wrapper jar is not committed. The script downloads the Gradle 9.2.1 wrapper the first time it runs, then Gradle downloads its own distribution. The mod jar is written to `build/libs/`.
