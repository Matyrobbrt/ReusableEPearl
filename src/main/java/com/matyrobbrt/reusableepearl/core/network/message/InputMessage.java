package com.matyrobbrt.reusableepearl.core.network.message;

import java.util.function.Supplier;

import com.matyrobbrt.reusableepearl.core.init.ItemInit;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.network.NetworkEvent;

public class InputMessage {

	public static int slot;

	public int key;

	public InputMessage() {

	}

	public InputMessage(int key) {
		this.key = key;
	}

	public static void encode(InputMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.key);
	}

	public static InputMessage decode(FriendlyByteBuf buffer) {
		return new InputMessage(buffer.readInt());
	}

	public static void handle(InputMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayer playerEntity = context.getSender();
			ServerLevel worldIn = playerEntity.getLevel();
			ItemStack stack = null;

			// Off hand
			if (playerEntity.getOffhandItem().getItem() == ItemInit.ULTRA_PEARL.get()) {
				stack = playerEntity.getOffhandItem();
			} else {
				// Inventory
				for (int i = 0; i <= 35; ++i) {
					if (playerEntity.getInventory().getItem(i).getItem() == ItemInit.ULTRA_PEARL.get()) {
						stack = playerEntity.getInventory().getItem(i);
						break;
					}
				}
			}

			if (stack != null) {
				ThrownEnderpearl entity = new ThrownEnderpearl(worldIn, playerEntity);
				entity.setOwner(playerEntity);
				entity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 1.5F,
						1.0F);
				worldIn.addFreshEntity(entity);

				// Durability remove
				int dmgValue = stack.getDamageValue() + 1;
				stack.setDamageValue(dmgValue);
				if (dmgValue == stack.getMaxDamage()) {
					stack.shrink(1);
				}
			} else {
				playerEntity.sendMessage(new TextComponent("\u00A74" + "You don't have an Ultra Ender Pearl on you!"),
						playerEntity.getUUID());
			}
		});
		context.setPacketHandled(true);
	}
}
