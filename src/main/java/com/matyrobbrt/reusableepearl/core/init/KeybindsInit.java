package com.matyrobbrt.reusableepearl.core.init;

import java.awt.event.KeyEvent;

import com.matyrobbrt.reusableepearl.ReusableEPearl;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class KeybindsInit {
	public static KeyBinding throwPearl;
	
	public static void register(final FMLClientSetupEvent event) {
		throwPearl = create("throw_pearl", KeyEvent.VK_P);
		
		ClientRegistry.registerKeyBinding(throwPearl);
	}
	
	private static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + ReusableEPearl.MOD_ID + "." + name, key, "key.category" + ReusableEPearl.MOD_ID);
	}
}
