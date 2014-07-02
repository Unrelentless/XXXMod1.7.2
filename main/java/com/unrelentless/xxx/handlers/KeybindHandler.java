package com.unrelentless.xxx.handlers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.unrelentless.xxx.XxxMod;

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
			int maxX = 8;
			int maxY = 64;
			int maxZ = 8;

			double playerPosX = player.posX;
			double playerPosY = player.posY;
			double playerPosZ = player.posZ;
			boolean found = false;

			outerloop:
				for(int i=-maxX;i<=maxX;i++){
					for(int j=-maxZ;j<=maxZ;j++){
						for(int k=-maxY;k<=0;k++){

							int blockX = (int) (playerPosX+i);
							int blockY = (int) (playerPosY+k);
							int blockZ = (int) (playerPosZ+j);

							Block block = player.worldObj.getBlock(blockX, blockY, blockZ);

							if(block.getIdFromBlock(block) == 489){
								player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Fossil "+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!"));
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

							if(block.getIdFromBlock(block) == 56){
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

								if(block.getIdFromBlock(block) == 129){
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

								if(block.getIdFromBlock(block) == 14){
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

								if(block.getIdFromBlock(block) == 551){
									player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Node "+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!"));
								}
							}
						}
					}
		}else if(XxxMod.scanPoke.isPressed() && System.nanoTime()>lastPressed+1000) {
			isHUD=!isHUD;
		}
	}
}