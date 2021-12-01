package com.matyrobbrt.reusableepearl.core.init;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.common.item.ReusablePearl;
import com.matyrobbrt.reusableepearl.core.config.PearlConfig;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			ReusableEPearl.MOD_ID);

	public static final RegistryObject<ReusablePearl> ADVANCED_PEARL = ITEMS
			.register("advanced_pearl", () -> new ReusablePearl(new Item.Properties().tab(CreativeModeTab.TAB_MISC)
					.stacksTo(1).defaultDurability(PearlConfig.CONFIG.advancedPearlUses).setNoRepair()));
	public static final RegistryObject<ReusablePearl> ULTRA_PEARL = ITEMS.register(
			"ultra_pearl", () -> new ReusablePearl(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)
					.defaultDurability(PearlConfig.CONFIG.ultraPearlUses).rarity(Rarity.EPIC).setNoRepair()));
}
