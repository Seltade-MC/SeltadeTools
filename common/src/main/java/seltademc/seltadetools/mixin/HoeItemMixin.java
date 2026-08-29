package seltademc.seltadetools.mixin;

import com.mojang.datafixers.util.Pair;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HoeItem.class)
public abstract class HoeItemMixin {
    @Shadow
    protected static Map<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> TILLABLES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void seltade$addVanillaTillables(CallbackInfo ci) {
        TILLABLES.put(
            Blocks.GRASS_BLOCK,
            Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState()))
        );
        TILLABLES.put(
            Blocks.PODZOL,
            Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState()))
        );
        TILLABLES.put(
            Blocks.MYCELIUM,
            Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState()))
        );
        TILLABLES.put(
            Blocks.DIRT_PATH,
            Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState()))
        );
        TILLABLES.put(
            Blocks.DIRT,
            Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState()))
        );
        TILLABLES.put(
            Blocks.COARSE_DIRT,
            Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState()))
        );
        TILLABLES.put(
            Blocks.ROOTED_DIRT,
            Pair.of(ctx -> true, HoeItem.changeIntoStateAndDropItem(Blocks.FARMLAND.defaultBlockState(), net.minecraft.world.item.Items.HANGING_ROOTS))
        );
    }
}
