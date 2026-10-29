package dev.tyty.seasonalcanopies.particle;

import dev.tyty.seasonalcanopies.SeasonalCanopies;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class SeasonalCanopiesParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, SeasonalCanopies.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAF =
            PARTICLE_TYPES.register("falling_leaf", () -> new SimpleParticleType(false));

    private SeasonalCanopiesParticles() {
    }
}
