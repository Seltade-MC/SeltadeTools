package seltademc.seltaderoads;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;

public class SeltadeRoads implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            return CommonClass.handleBlockUse(player, world, hand, hitResult.getBlockPos(), hitResult.getDirection());
        });
    }
}