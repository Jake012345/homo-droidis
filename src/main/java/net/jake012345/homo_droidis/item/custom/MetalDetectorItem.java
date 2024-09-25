package net.jake012345.homo_droidis.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties pProperties) {
        super(pProperties.durability(5));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i < positionClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                if (isValuableBlock(state)) {
                    assert player != null;
                    outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }
            if (!foundBlock) {
                assert player != null;
                player.sendSystemMessage(Component.literal("No valuable found!"));
            }
            player.sendSystemMessage(Component.literal(
                    String.valueOf(getMaxDamage(player.getItemInHand(player.getUsedItemHand()))) + "/" +
                    String.valueOf(getDamage(player.getItemInHand(player.getUsedItemHand())))));
        }
        ItemStack itemStack = pContext.getItemInHand();
        if (pContext.getPlayer() instanceof ServerPlayer serverPlayer) {
            itemStack.hurt(1, pContext.getLevel().getRandom(), serverPlayer);
        }
        if (itemStack.getDamageValue() >= itemStack.getMaxDamage()) {
            pContext.getPlayer().broadcastBreakEvent(pContext.getPlayer().getUsedItemHand());
            pContext.getPlayer().getInventory().removeItem(itemStack);

        }

        return InteractionResult.SUCCESS;
    }


    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.DIAMOND_ORE);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }
}
