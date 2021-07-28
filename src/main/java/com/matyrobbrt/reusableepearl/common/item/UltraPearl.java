package com.matyrobbrt.reusableepearl.common.item;

//import com.matyrobbrt.reusableepearl.core.config.PearlConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class UltraPearl extends Item {

	public UltraPearl() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(ItemGroup.TAB_MISC).durability(180)
				.rarity(Rarity.EPIC));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {

		ItemStack stack = player.getMainHandItem();

		EnderPearlEntity entity = new EnderPearlEntity(worldIn, player);
		entity.setOwner(player);
		entity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
		worldIn.addFreshEntity(entity);

		// Durability remove
		int DamageValue = stack.getDamageValue() + 1;
		stack.setDamageValue(DamageValue);
		if (DamageValue == 180) {
			player.getItemInHand(hand).shrink(1);
		}

		return super.use(worldIn, player, hand);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

		if (enchantment.isAllowedOnBooks()) {
			return false;
		}

		if (enchantment.isCompatibleWith(Enchantments.MENDING)) {
			return false;
		}

		return false;
	}
}
