package dev.tyty.seasonalcanopies.client;

import dev.tyty.seasonalcanopies.SeasonalCanopiesConfig;
import dev.tyty.seasonalcanopies.particle.SeasonalCanopiesParticles;
import dev.tyty.seasonalcanopies.season.SeasonKind;
import dev.tyty.seasonalcanopies.season.SeasonMath;
import dev.tyty.seasonalcanopies.season.SeasonSource;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class LeafShed {
    private LeafShed() {
    }

    public static void trySpawn(Level level, BlockPos pos, BlockState state, RandomSource random) {
        if (!level.isClientSide() || state.isAir() || !SeasonSource.affects(level)) {
            return;
        }
        float amount = SeasonalCanopiesConfig.PARTICLE_AMOUNT.get().floatValue();
        if (amount <= 0.0F) {
            return;
        }
        SeasonKind season = SeasonSource.get(level);
        float chance = SeasonMath.shedChance(season) * amount;
        if (random.nextFloat() >= chance) {
            return;
        }
        double x = pos.getX() + 0.15D + random.nextDouble() * 0.7D;
        double y = pos.getY() + 0.15D + random.nextDouble() * 0.35D;
        double z = pos.getZ() + 0.15D + random.nextDouble() * 0.7D;
        double xSpeed = (random.nextDouble() - 0.5D) * 0.04D;
        double ySpeed = -0.01D - random.nextDouble() * 0.02D;
        double zSpeed = (random.nextDouble() - 0.5D) * 0.04D;
        level.addParticle(SeasonalCanopiesParticles.FALLING_LEAF.get(), x, y, z, xSpeed, ySpeed, zSpeed);
    }
}
