package com.matyrobbrt.reusableepearl.core.init;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.common.item.ReusablePearl;
import com.matyrobbrt.reusableepearl.core.config.PearlConfig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			ReusableEPearl.MOD_ID);

	static {
		PearlConfig.CONFIG.forEach((name, cfg) -> ITEMS.register(name, () -> new ReusablePearl(new Item.Properties()
				.rarity(cfg.supportsKeybind ? Rarity.EPIC : Rarity.UNCOMMON).tab(CreativeModeTab.TAB_MISC).setNoRepair(), cfg)));
	}

}
