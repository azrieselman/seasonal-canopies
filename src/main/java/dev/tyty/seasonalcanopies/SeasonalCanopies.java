package dev.tyty.seasonalcanopies;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.tyty.seasonalcanopies.particle.SeasonalCanopiesParticles;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(SeasonalCanopies.MOD_ID)
public class SeasonalCanopies {
    public static final String MOD_ID = "seasonalcanopies";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SeasonalCanopies(IEventBus modEventBus, ModContainer modContainer) {
        SeasonalCanopiesParticles.PARTICLE_TYPES.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.CLIENT, SeasonalCanopiesConfig.SPEC);
        LOGGER.info("Seasonal Canopies loaded. Dynamic Trees foliage will tint when that mod is present.");
    }
}
