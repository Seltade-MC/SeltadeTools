package seltademc.seltaderoads.mixin;

import seltademc.seltaderoads.Constants;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        
        Constants.LOG.info("This line is printed by {} common mixin!", Constants.MOD_ID);
        Constants.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}