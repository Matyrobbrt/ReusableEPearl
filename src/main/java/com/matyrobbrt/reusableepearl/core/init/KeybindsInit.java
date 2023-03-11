package com.matyrobbrt.reusableepearl.core.init;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.event.KeyEvent;

@Mod.EventBusSubscriber(modid = ReusableEPearl.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindsInit {
	public static KeyMapping throwPearl;

	@SubscribeEvent
	static void register(final RegisterKeyMappingsEvent event) {
		event.register(throwPearl = create("throw_pearl", KeyEvent.VK_V));
		throwPearl = create("throw_pearl", KeyEvent.VK_V);
	}
	
	private static KeyMapping create(String name, int key) {
		return new KeyMapping("key." + ReusableEPearl.MOD_ID + "." + name, key, "key.category" + ReusableEPearl.MOD_ID);
	}
}
