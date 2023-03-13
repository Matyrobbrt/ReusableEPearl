package com.matyrobbrt.reusableepearl.common.events;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.core.init.KeybindsInit;
import com.matyrobbrt.reusableepearl.core.network.PearlNetwork;
import com.matyrobbrt.reusableepearl.core.network.message.UseEPearlMessage;
import com.mojang.blaze3d.platform.InputConstants;
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

        if (event.getAction() == InputConstants.RELEASE && KeybindsInit.throwPearl.isActiveAndMatches(InputConstants.Type.KEYSYM.getOrCreate(event.getKey()))) {
            PearlNetwork.CHANNEL.sendToServer(new UseEPearlMessage());
        }
    }

}
