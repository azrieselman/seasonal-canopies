package dev.tyty.seasonalcanopies.compat;

import org.jetbrains.annotations.Nullable;

import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;

import dev.tyty.seasonalcanopies.client.SeasonTint;
import dev.tyty.seasonalcanopies.season.SeasonSource;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

/**
 * Loaded only when Dynamic Trees is installed.
 */
public final class DynamicTreesLeafTint {
    private DynamicTreesLeafTint() {
    }

    public static boolean isLeaves(Block block) {
        return block instanceof DynamicLeavesBlock;
    }

    public static int tint(int color, @Nullable BlockAndTintGetter level) {
        if (color == -1) {
            return color;
        }
        Level world = level instanceof Level typed ? typed : Minecraft.getInstance().level;
        if (world == null || !SeasonSource.affects(world)) {
            return color & 0xFFFFFF;
        }
        return SeasonTint.apply(color & 0xFFFFFF, SeasonSource.get(world));
    }
}
