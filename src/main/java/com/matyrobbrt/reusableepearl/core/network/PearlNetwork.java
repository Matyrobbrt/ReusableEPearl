package com.matyrobbrt.reusableepearl.core.network;

import com.matyrobbrt.reusableepearl.ReusableEPearl;
import com.matyrobbrt.reusableepearl.core.network.message.InputMessage;

import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PearlNetwork {

	public static final String NETWORK_VERSION = "0.1.0";

	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(ReusableEPearl.MOD_ID, "network"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	
	public static void init() {
		
		CHANNEL.registerMessage(0, InputMessage.class, InputMessage::encode, InputMessage::decode, InputMessage::handle);
		
	}
	
}
