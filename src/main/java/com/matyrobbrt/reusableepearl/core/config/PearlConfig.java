package com.matyrobbrt.reusableepearl.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class PearlConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	public static final ForgeConfigSpec.ConfigValue<Integer> advanced_pearl_count;
	public static final ForgeConfigSpec.ConfigValue<Integer> ultra_pearl_count;
	
	static {
		BUILDER.push("Config for reusable ender pearls.");
		
		advanced_pearl_count = BUILDER.comment("This is the amount of durability (ender pearls) an Advanced Ender Pearl has. Default is 20.").define("Advanced Pearl Count", 20);
		ultra_pearl_count = BUILDER.comment("This is the amount of durability (ender pearls) an Ultra Ender Pearl has. Default is 200.").define("Ultra Pearl Count", 200);
		
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
