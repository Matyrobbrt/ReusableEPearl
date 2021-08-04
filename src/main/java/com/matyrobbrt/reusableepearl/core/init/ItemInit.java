package com.matyrobbrt.reusableepearl.core.init;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.common.item.AdvancedPearl;
import com.matyrobbrt.reusableepearl.common.item.UltraPearl;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReusableEPearl.MOD_ID);
	
	public static final net.minecraftforge.fmllegacy.RegistryObject<AdvancedPearl> ADVANCED_PEARL = ITEMS.register("advanced_pearl", AdvancedPearl::new);
	public static final net.minecraftforge.fmllegacy.RegistryObject<UltraPearl> ULTRA_PEARL = ITEMS.register("ultra_pearl", UltraPearl::new);
}
