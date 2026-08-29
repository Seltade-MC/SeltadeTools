package seltademc.seltadetools.mixin;

import java.util.Map;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin {
    @Shadow
    protected static Map<Block, BlockState> FLATTENABLES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void seltade$addVanillaFlattenables(CallbackInfo ci) {
        FLATTENABLES.put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.PODZOL,      Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.MYCELIUM,    Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.DIRT,        Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState());
        FLATTENABLES.put(Blocks.FARMLAND,    Blocks.DIRT_PATH.defaultBlockState());
    }
}
