package com.matyrobbrt.reusableepearl.core.init;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.common.item.ReusablePearl;
import com.matyrobbrt.reusableepearl.core.config.PearlConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegisterEvent;

public class ItemInit {

	public static void register(final RegisterEvent event) {
		event.register(Registry.ITEM_REGISTRY, helper -> PearlConfig.CONFIG.forEach((name, cfg) -> helper.register(new ResourceLocation(ReusableEPearl.MOD_ID, name), new ReusablePearl(new Item.Properties()
				.rarity(cfg.supportsKeybind ? Rarity.EPIC : Rarity.UNCOMMON).tab(CreativeModeTab.TAB_MISC).setNoRepair(), cfg))));
	}

}
