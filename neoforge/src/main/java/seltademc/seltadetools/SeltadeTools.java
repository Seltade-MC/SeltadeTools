package seltademc.seltadetools;

import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@Mod(Constants.MOD_ID)
public class SeltadeTools {

    public SeltadeTools(IEventBus eventBus) {
        CommonClass.init();

        NeoForge.EVENT_BUS.addListener((PlayerInteractEvent.RightClickBlock event) -> {
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