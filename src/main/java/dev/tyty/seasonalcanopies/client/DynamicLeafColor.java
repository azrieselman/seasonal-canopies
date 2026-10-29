package dev.tyty.seasonalcanopies.client;

import org.jetbrains.annotations.Nullable;

import dev.tyty.seasonalcanopies.compat.DynamicTreesLeafTint;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Gates the leaf tint so Dynamic Trees classes are never loaded when that mod is absent.
 */
public final class DynamicLeafColor {
    private static final boolean DYNAMIC_TREES = ModList.get().isLoaded("dynamictrees");

    private DynamicLeafColor() {
    }

    public static void tint(BlockState state, @Nullable BlockAndTintGetter level, CallbackInfoReturnable<Integer> original) {
        if (!DYNAMIC_TREES || !DynamicTreesLeafTint.isLeaves(state.getBlock())) {
            return;
        }
        original.setReturnValue(DynamicTreesLeafTint.tint(original.getReturnValueI(), level));
    }
}
