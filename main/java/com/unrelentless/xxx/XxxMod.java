package com.unrelentless.xxx;


import java.io.File;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.unrelentless.xxx.gui.PokeRadarHUD;
import com.unrelentless.xxx.gui.PokelootRadarHUD;
import com.unrelentless.xxx.handlers.ConfigHandler;
import com.unrelentless.xxx.handlers.KeybindHandler;
import com.unrelentless.xxx.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = XxxMod.MODID, version = XxxMod.VERSION)
public class XxxMod
{
	public static final String MODID = "xxxmod";
	public static final String VERSION = "0.3.7";

	//Keybinds
	public static KeyBinding scan, scanPoke;

	//says where the client and server 'proxy' code is loaded
	@SidedProxy(clientSide = "com.unrelentless.xxx.proxy.ClientProxy", serverSide = "com.unrelentless.xxx.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws IOException{

		ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + this.MODID + File.separator + this.MODID + ".cfg"));

	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{        
		proxy.registerRenderers();

		//keybinding
		scan = new KeyBinding("key.scanBlock", Keyboard.KEY_F, "key.categories.xxxmod");
		scanPoke = new KeyBinding("key.scanPoke", Keyboard.KEY_G, "key.categories.xxxmod");
		ClientRegistry.registerKeyBinding(scan);
		ClientRegistry.registerKeyBinding(scanPoke);

		//event registration
		FMLCommonHandler.instance().bus().register(new KeybindHandler());

	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new PokeRadarHUD(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new PokelootRadarHUD(Minecraft.getMinecraft()));
	}
}
