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
import net.minecraft.world.item.ItemStack;
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

        ItemStack item = player.getItemInHand(hand);

        if (!item.is(ItemTags.HOES) && !item.is(ItemTags.SHOVELS)) {
            return InteractionResult.PASS;
        }

        BlockState block = world.getBlockState(pos);

        if (item.is(ItemTags.HOES)) {
            if (block.is(Blocks.FARMLAND)) {
                world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 1);

                if (player.blockPosition().equals(pos)) {
                    player.setPos(player.getX(), pos.getY() + 1, player.getZ());
                }

                item.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                world.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
            if (block.is(Blocks.MYCELIUM) || block.is(Blocks.PODZOL)) {
                world.setBlock(pos, Blocks.FARMLAND.defaultBlockState(), 1);
                item.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                world.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }
        
        if (item.is(ItemTags.SHOVELS)) {
            if (block.is(Blocks.DIRT_PATH)) {
                world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 1);

                if (player.blockPosition().equals(pos)) {
                    player.setPos(player.getX(), pos.getY() + 1, player.getZ());
                }

                item.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                world.playSound(null, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
            if (block.is(Blocks.FARMLAND)) {
                world.setBlock(pos, Blocks.DIRT_PATH.defaultBlockState(), 1);
                item.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                world.playSound(null, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}