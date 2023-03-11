package com.matyrobbrt.reusableepearl.core.network.message;

import com.matyrobbrt.reusableepearl.common.item.ReusablePearl;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class UseEPearlMessage {
	public static final UseEPearlMessage INSTANCE = new UseEPearlMessage();

	public void encode(FriendlyByteBuf buffer) {
	}

	public static UseEPearlMessage decode(FriendlyByteBuf buffer) {
		return INSTANCE;
	}

	public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
		final NetworkEvent.Context context = contextSupplier.get();
		final ServerPlayer playerEntity = context.getSender();
		if (playerEntity == null) return;

		final Predicate<ItemStack> isGood = stack -> stack.getItem() instanceof ReusablePearl pearl && pearl.config.supportsKeybind
				&& !playerEntity.getCooldowns().isOnCooldown(pearl);

		ItemStack stack = null;
		InteractionHand hand = null;

		// Off-hand and main-hand
		for (final InteractionHand hand1 : InteractionHand.values()) {
			final ItemStack inHand = playerEntity.getItemInHand(hand1);
			if (isGood.test(inHand)) {
				stack = inHand;
				hand = hand1;
			}
		}

		// Inventory
		if (stack == null) {
			for (int i = 0; i < Inventory.INVENTORY_SIZE; ++i) {
				final ItemStack inv = playerEntity.getInventory().getItem(i);
				if (isGood.test(inv)) {
					stack = inv;
					break;
				}
			}
		}

		if (stack != null) {
			((ReusablePearl) stack.getItem()).teleport(playerEntity, stack, hand);
		} else {
			playerEntity.sendSystemMessage(Component.translatable("message.reusableepearl.no_pearl").withStyle(ChatFormatting.GOLD));
		}
	}
}
