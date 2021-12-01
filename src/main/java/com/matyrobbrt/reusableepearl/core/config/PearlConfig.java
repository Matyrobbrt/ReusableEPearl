package com.matyrobbrt.reusableepearl.core.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.matyrobbrt.reusableepearl.ReusableEPearl;

public class PearlConfig {

	private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
			.create();
	protected String root = ReusableEPearl.CONFIG_DIR_PATH;
	protected String extension = ".json";

	public void generateConfig() {
		this.reset();

		try {
			this.writeConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File getConfigFile() { return new File(this.root + this.getName() + this.extension); }

	public PearlConfig readConfig() {
		try {
			return GSON.fromJson(new FileReader(this.getConfigFile()), this.getClass());
		} catch (FileNotFoundException e) {
			this.generateConfig();
		}

		return this;
	}

	public void writeConfig() throws IOException {
		File dir = new File(this.root);
		if (!dir.exists() && !dir.mkdirs())
			return;
		if (!this.getConfigFile().exists() && !this.getConfigFile().createNewFile())
			return;
		FileWriter writer = new FileWriter(this.getConfigFile());
		GSON.toJson(this, writer);
		writer.flush();
		writer.close();
	}

	@Expose
	public int advancedPearlUses;
	@Expose
	public int ultraPearlUses;

	protected void reset() {
		this.advancedPearlUses = 20;
		this.ultraPearlUses = 180;
	}
	
	public String getName() { return "pearl_config"; }
	
	public static PearlConfig CONFIG;

	public static void register() {
		CONFIG = new PearlConfig().readConfig();
		ReusableEPearl.LOGGER.info("Configs loaded!");
	}
}
