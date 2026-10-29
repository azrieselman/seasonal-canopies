package dev.tyty.seasonalcanopies.client;

import dev.tyty.seasonalcanopies.SeasonalCanopies;
import dev.tyty.seasonalcanopies.particle.SeasonalCanopiesParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = SeasonalCanopies.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = SeasonalCanopies.MOD_ID, value = Dist.CLIENT)
public class SeasonalCanopiesClient {
    public SeasonalCanopiesClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        if (ModList.get().isLoaded("dynamictrees")) {
            SeasonalCanopies.LOGGER.info("Seasonal Canopies will tint Dynamic Trees foliage.");
        } else {
            SeasonalCanopies.LOGGER.info("Dynamic Trees is not installed. Seasonal Canopies has no foliage to tint.");
        }
    }

    @SubscribeEvent
    static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SeasonalCanopiesParticles.FALLING_LEAF.get(), FallingLeafParticle.Provider::new);
    }
}
