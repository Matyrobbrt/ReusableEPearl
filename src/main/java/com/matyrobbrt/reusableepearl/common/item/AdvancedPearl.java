package com.matyrobbrt.reusableepearl.common.item;

//import com.matyrobbrt.reusableepearl.core.config.PearlConfig;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

public class AdvancedPearl extends Item {
	
	public AdvancedPearl() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(CreativeModeTab.TAB_MISC).durability(20));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
		
		ItemStack stack = player.getMainHandItem();
		
		ThrownEnderpearl entity = new ThrownEnderpearl(worldIn, player);
		entity.setOwner(player);
		entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
		worldIn.addFreshEntity(entity);
		
		//Durability remove
		int DamageValue = stack.getDamageValue() + 1;
		stack.setDamageValue(DamageValue);
		if (DamageValue == 20) {
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
