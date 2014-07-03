package com.unrelentless.xxx.handlers;

import java.io.File;
import java.util.logging.Level;

import com.unrelentless.xxx.XxxMod;
import com.unrelentless.xxx.lib.Config;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	static Configuration config = new Configuration();

	public static void init(File file){

		config = new Configuration(file);

		try{

			config.load();

			Config.pokemon_search_radius = config.get(config.CATEGORY_GENERAL, "Radius to search for pokemon around you. (Default: 64)", Config.POKEMON_SEARCH_RADIUS_DEFAULT).getInt(Config.POKEMON_SEARCH_RADIUS_DEFAULT);
			Config.block_search_radius = config.get(config.CATEGORY_GENERAL, "Radius to search for fossils around you. (Default: 8)", Config.BLOCK_SEARCH_RADIUS_DEFAULT).getInt(Config.BLOCK_SEARCH_RADIUS_DEFAULT);
			Config.block_search_radius_y = config.get(config.CATEGORY_GENERAL, "How far below you to search for fossils. (Default: 60)", Config.BLOCK_SEARCH_RADIUS_Y_DEFAULT).getInt(Config.BLOCK_SEARCH_RADIUS_Y_DEFAULT);
			
		}

		catch(Exception e){

			System.out.println("Config shat itself.");
		}

		finally{

			config.save();
		}
	}
}