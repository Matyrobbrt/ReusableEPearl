package com.matyrobbrt.reusableepearl.common.events;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.core.init.KeybindsInit;
import com.matyrobbrt.reusableepearl.core.network.PearlNetwork;
import com.matyrobbrt.reusableepearl.core.network.message.UseEPearlMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ReusableEPearl.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {

    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        final Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.screen != null) return;

        System.out.println("PRESS");
        if (KeybindsInit.throwPearl.isDown()) {
            System.out.println("IS DOWN");
            PearlNetwork.CHANNEL.sendToServer(new UseEPearlMessage());
        }
    }

}
