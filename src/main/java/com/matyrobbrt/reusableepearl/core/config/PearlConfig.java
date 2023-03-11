package com.matyrobbrt.reusableepearl.core.config;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.matyrobbrt.reusableepearl.ReusableEPearl;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PearlConfig {

	public final int uses;
	public final boolean supportsKeybind;
	public final int cooldown;

	public PearlConfig(int uses, boolean supportsKeybind, int cooldown) {
		this.uses = uses;
		this.supportsKeybind = supportsKeybind;
		this.cooldown = cooldown;
	}

	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	private static final Map<String, PearlConfig> DEFAULT = Map.of(
			"advanced_pearl", new PearlConfig(20, false, 30),
			"ultra_pearl", new PearlConfig(180, true, 15)
	);
	public static Map<String, PearlConfig> CONFIG = DEFAULT;

	public static void register() {
		final Path configPath = FMLPaths.CONFIGDIR.get().resolve("reusableepearls.json");
		if (Files.exists(configPath)) {
			try (final var reader = Files.newBufferedReader(configPath)) {
				CONFIG = GSON.fromJson(reader, new TypeToken<Map<String, PearlConfig>>() {}.getType());
			} catch (Exception ex) {
				ReusableEPearl.LOGGER.error("Encountered exception reading config: ", ex);
				ReusableEPearl.LOGGER.info("Resetting config.");
				writeDefault(configPath);
			}
		} else {
			writeDefault(configPath);
		}
		ReusableEPearl.LOGGER.info("Configs loaded!");
	}

	private static void writeDefault(Path path) {
		try (final var writer = Files.newBufferedWriter(path)) {
			GSON.toJson(DEFAULT, writer);
		} catch (Exception ex) {
			ReusableEPearl.LOGGER.error("Encountered exception writing config: ", ex);
		}
	}

}
