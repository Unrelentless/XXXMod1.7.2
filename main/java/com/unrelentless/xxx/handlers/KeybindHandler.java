package com.unrelentless.xxx.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.unrelentless.xxx.XxxMod;
import com.unrelentless.xxx.lib.Config;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeybindHandler {
	long lastPressed=System.nanoTime();
	public static boolean isHUD = false;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {

		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;

		if(XxxMod.scan.isPressed() && System.nanoTime()>lastPressed+1000) {
			lastPressed=System.nanoTime();
			int maxX = Config.block_search_radius;
			int maxY = Config.block_search_radius_y;
			int maxZ = Config.block_search_radius;

			double playerPosX = player.posX;
			double playerPosY = player.posY;
			double playerPosZ = player.posZ;

			outerloop:
				for(int i=-maxX;i<=maxX;i++){
					for(int j=-maxZ;j<=maxZ;j++){
						for(int k=-maxY;k<=0;k++){

							int blockX = (int) (playerPosX+i);
							int blockY = (int) (playerPosY+k);
							int blockZ = (int) (playerPosZ+j);

							Block block = player.worldObj.getBlock(blockX, blockY, blockZ);

							if(block.getLocalizedName().equals("Fossil")){
								player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Fossil "+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!"));
								break outerloop;
							}
						}
					}
				}
			if(Config.do_gem_search){
				outerloop:
					for(int i=-maxX;i<=maxX;i++){
						for(int j=-maxZ;j<=maxZ;j++){
							for(int k=-maxY;k<=0;k++){

								int blockX = (int) (playerPosX+i);
								int blockY = (int) (playerPosY+k);
								int blockZ = (int) (playerPosZ+j);

								Block block = player.worldObj.getBlock(blockX, blockY, blockZ);

								if(block.getLocalizedName().equals("Diamond Ore")){
									player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Diamond Cluster"+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!"));
									break outerloop;
								}
							}
						}
					}
			outerloop:
				for(int i=-maxX;i<=maxX;i++){
					for(int j=-maxZ;j<=maxZ;j++){
						for(int k=-maxY;k<=0;k++){

							int blockX = (int) (playerPosX+i);
							int blockY = (int) (playerPosY+k);
							int blockZ = (int) (playerPosZ+j);

							Block block = player.worldObj.getBlock(blockX, blockY, blockZ);

							if(block.getLocalizedName().equals("Emerald Ore")){
								player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Emerald "+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!"));
								break outerloop;
							}
						}
					}
				}
					outerloop:
						for(int i=-maxX;i<=maxX;i++){
							for(int j=-maxZ;j<=maxZ;j++){
								for(int k=-maxY;k<=0;k++){

									int blockX = (int) (playerPosX+i);
									int blockY = (int) (playerPosY+k);
									int blockZ = (int) (playerPosZ+j);

									Block block = player.worldObj.getBlock(blockX, blockY, blockZ);

									if(block.getLocalizedName().equals("Gold Ore")){
										player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Gold Cluster "+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!"));
										break outerloop;
									}
								}
							}
						}

				maxX = 64;
				maxY = 20;
				maxZ = 64;

				for(int i=-maxX;i<=maxX;i++){
					for(int j=-maxZ;j<=maxZ;j++){
						for(int k=-20;k<=maxY;k++){

							int blockX = (int) (playerPosX+i);
							int blockY = (int) (playerPosY+k);
							int blockZ = (int) (playerPosZ+j);

							Block block = player.worldObj.getBlock(blockX, blockY, blockZ);

							if(block.getLocalizedName().equals("Aura Node")){
								player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Node "+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!"));
							}
						}
					}
				}
			}
		}else if(XxxMod.scanPoke.isPressed() && System.nanoTime()>lastPressed+1000) {
			isHUD=!isHUD;
		}
	}
}