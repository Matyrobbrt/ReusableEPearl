package com.matyrobbrt.reusableepearl;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.matyrobbrt.reusableepearl.core.config.PearlConfig;
import com.matyrobbrt.reusableepearl.core.init.ItemInit;
import com.matyrobbrt.reusableepearl.core.network.PearlNetwork;

@Mod("reusableepearl")
public class ReusableEPearl {
	
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "reusableepearl";

	public ReusableEPearl() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		bus.addListener(this::commonSetup);
		
		ItemInit.ITEMS.register(bus);
		
		
		//ModLoadingContext.get().registerConfig(Type.COMMON, PearlConfig.SPEC, "reusableepearl-common.toml");
		
		MinecraftForge.EVENT_BUS.register(this);
		
	}
	
	public void commonSetup(final FMLCommonSetupEvent event) {
		PearlNetwork.init();
	}
}
