package seltademc.seltadetools;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import seltademc.seltadetools.platform.Services;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {

    public static void init() {
        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("Loaded {}", Constants.MOD_NAME);
        }
    }

    public static InteractionResult handleBlockUse(Player player, Level world, InteractionHand hand, BlockPos pos, Direction direction) {
        if (direction == Direction.DOWN) {
            return InteractionResult.PASS;
        }

        BlockState state = world.getBlockState(pos);

        if (state.is(Blocks.DIRT_PATH) && player.getItemInHand(hand).is(ItemTags.SHOVELS)) {
            player.setPos(player.getX(), player.getY() + 0.0625, player.getZ());
            world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 1);
            player.getItemInHand(hand).hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            world.playSound(null, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }

        if (state.is(Blocks.FARMLAND) && player.getItemInHand(hand).is(ItemTags.HOES)) {
            player.setPos(player.getX(), player.getY() + 0.0625, player.getZ());
            world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 1);
            player.getItemInHand(hand).hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            world.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}