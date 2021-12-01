package com.matyrobbrt.reusableepearl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.matyrobbrt.reusableepearl.core.config.PearlConfig;
import com.matyrobbrt.reusableepearl.core.init.ItemInit;
import com.matyrobbrt.reusableepearl.core.network.PearlNetwork;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("reusableepearl")
public class ReusableEPearl {
	
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "reusableepearl";

	public static final String CONFIG_DIR_PATH = "config/" + MOD_ID + "/";

	public ReusableEPearl() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		bus.addListener(this::constructMod);
		bus.addListener(this::commonSetup);
		
		ItemInit.ITEMS.register(bus);
		
		MinecraftForge.EVENT_BUS.register(this);
		
	}
	
	public void commonSetup(final FMLCommonSetupEvent event) {
		PearlNetwork.init();
	}

	public void constructMod(final FMLConstructModEvent event) {
		PearlConfig.register();
	}
}
