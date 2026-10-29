package dev.tyty.seasonalcanopies.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.tyty.seasonalcanopies.client.DynamicLeafColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BlockColors.class)
public abstract class BlockColorsMixin {
    @Inject(method = "getColor", at = @At("RETURN"), cancellable = true)
    private void seasonalcanopies$tintDynamicLeaves(BlockState state, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int tintIndex, CallbackInfoReturnable<Integer> cir) {
        DynamicLeafColor.tint(state, level, cir);
    }
}
