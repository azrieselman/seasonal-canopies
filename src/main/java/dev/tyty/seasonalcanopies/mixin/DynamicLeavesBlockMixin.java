package dev.tyty.seasonalcanopies.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.tyty.seasonalcanopies.client.LeafShed;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(targets = "com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock", remap = false)
public abstract class DynamicLeavesBlockMixin {
    @Inject(method = "animateTick", at = @At("RETURN"), remap = false, require = 0)
    private void seasonalcanopies$shedLeaves(BlockState state, Level level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        LeafShed.trySpawn(level, pos, state, random);
    }
}
