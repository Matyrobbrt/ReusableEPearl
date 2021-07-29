package com.matyrobbrt.reusableepearl.core.network.message;

import java.util.ArrayList;
import java.util.function.Supplier;

import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

public class InputMessage {

	public static int slot;

	public static void findSlotMatchingUnusedItem(ItemStack p_194014_1_, PlayerEntity player) {
		for (int i = 0; i < 36; ++i) {
			ItemStack itemstack = player.inventory.getItem(i);
			if (!player.inventory.getItem(i).isEmpty()
					&& itemstack.getTagElement("id") == p_194014_1_.getTagElement("id")) {
				slot = i;
			}
		}
	}

	public int key;

	public InputMessage() {

	}

	public InputMessage(int key) {
		this.key = key;
	}

	public static void encode(InputMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static InputMessage decode(PacketBuffer buffer) {
		return new InputMessage(buffer.readInt());
	}

	public static void handle(InputMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayerEntity playerEntity = context.getSender();
			ServerWorld worldIn = playerEntity.getLevel();
			//findSlotMatchingUnusedItem(new ItemStack(ItemInit.ULTRA_PEARL.get()), playerEntity);
			String stringList = playerEntity.inventory.items.toString();
			String stringList2 = "";
			String subString = "";
			
			if (stringList.contains("ultra_pearl")) {
				ArrayList<String> listArray = new ArrayList<String>(36);
				
				stringList = stringList.substring(1);
				System.out.println(stringList);
				
				for (int i = 0; i <= 34; ++i) {
					subString = stringList.substring(0, stringList.indexOf(","));
					listArray.add(i, subString);
					int counT = listArray.get(i).length() + 2;
					stringList2 = stringList.substring(counT);
					stringList = stringList2;
					//System.out.println(listArray.get(i));
					//System.out.println(stringList);
					subString = "";
				}
				listArray.add(35, stringList);
				//System.out.println("index of ultra_pearl: " + listArray.indexOf("1 ultra_pearl"));
				
				ItemStack stack = playerEntity.inventory.getItem(listArray.indexOf("1 ultra_pearl"));
				
				EnderPearlEntity entity = new EnderPearlEntity(worldIn, playerEntity);
				entity.setOwner(playerEntity);
				entity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot, 0.0F, 1.5F, 1.0F);
				worldIn.addFreshEntity(entity);

				// Durability remove
				int DamageValue = stack.getDamageValue() + 1;
				stack.setDamageValue(DamageValue);
				if (DamageValue == 180) {
					stack.shrink(1);
				}
		
				
			} else {
				playerEntity.sendMessage(new StringTextComponent("\u00A74" + "You don't have an Ultra Ender Pearl on you!"),
						playerEntity.getUUID());
			}
			//System.out.println(playerEntity.inventory.items);
		});
		context.setPacketHandled(true);
	}
}
