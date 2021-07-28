package com.matyrobbrt.reusableepearl.common.events;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.core.init.KeybindsInit;
import com.matyrobbrt.reusableepearl.core.network.PearlNetwork;
import com.matyrobbrt.reusableepearl.core.network.message.InputMessage;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ReusableEPearl.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {
	
	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.level == null) return;
		onInput(mc, event.getKey(), event.getAction());
	}
	
	private static void onInput(Minecraft mc, int key, int action) {
		if(mc.screen == null && KeybindsInit.throwPearl.isDown()) {
			PearlNetwork.CHANNEL.sendToServer(new InputMessage(key));
		}
		
	}
	
}
