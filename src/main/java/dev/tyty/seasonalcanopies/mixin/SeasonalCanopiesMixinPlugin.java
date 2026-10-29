package dev.tyty.seasonalcanopies.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

/**
 * Skips the Dynamic Trees mixin when that mod is not installed, so this mod still loads.
 */
public class SeasonalCanopiesMixinPlugin implements IMixinConfigPlugin {
    private static final String DYNAMIC_LEAVES = "com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock";

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.endsWith("DynamicLeavesBlockMixin")) {
            return classExists(DYNAMIC_LEAVES);
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean classExists(String name) {
        try {
            Class.forName(name, false, SeasonalCanopiesMixinPlugin.class.getClassLoader());
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }
}
