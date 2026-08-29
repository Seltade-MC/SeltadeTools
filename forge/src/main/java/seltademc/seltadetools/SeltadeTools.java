package seltademc.seltadetools;

import net.minecraft.world.InteractionResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class SeltadeTools {

    public SeltadeTools() {
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();

        MinecraftForge.EVENT_BUS.addListener((PlayerInteractEvent.RightClickBlock event) -> {
            if (event.getEntity() == null) {
                return;
            }

            InteractionResult result = CommonClass.handleBlockUse(
                    event.getEntity(),
                    event.getLevel(),
                    event.getHand(),
                    event.getPos(),
                    event.getFace()
            );

            if (result != InteractionResult.PASS) {
                event.setCanceled(true);
                event.setCancellationResult(result);
            }
        });
    }
}