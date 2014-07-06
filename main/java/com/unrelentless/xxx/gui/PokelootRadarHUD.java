package com.unrelentless.xxx.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import com.sun.javafx.binding.StringFormatter;
import com.unrelentless.xxx.XxxMod;
import com.unrelentless.xxx.handlers.KeybindHandler;
import com.unrelentless.xxx.lib.Config;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PokelootRadarHUD extends Gui{

	private Minecraft mc;
	public static long lastPressed=System.currentTimeMillis();
	ArrayList<String> pokeloots = new ArrayList<String>();

	public PokelootRadarHUD(Minecraft mc) {
		super();

		//we need this to invoke the render engine
		this.mc = mc;
	}

	//
	// This event is called by GuiIngameForge during each frame by
	// GuiIngameForge.pre() and GuiIngameForce.post().
	//
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		// The center of the screen can be gotten like this during this event:
		int xMax = event.resolution.getScaledWidth();
		int yMax = event.resolution.getScaledHeight();

		if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{      
			return;
		}
		// Starting position for the buff bar - 2 pixels from the top left corner.
		int xPos = 2;
		int yPos = 2;

		if(KeybindHandler.isHUD) {
			int textBuffer = 0;
	    	if(lastPressed+(long)5000  < System.currentTimeMillis()) {
	    		lastPressed=System.currentTimeMillis();
	    		pokeloots.removeAll(pokeloots);
				findPokeloots(mc.thePlayer);
				textBuffer = 0;
	    	}
	    	for(int i = 0;i<pokeloots.size();i++){
				textBuffer+=10;
				this.mc.fontRenderer.drawString(pokeloots.get(i), xMax-this.mc.fontRenderer.getStringWidth(pokeloots.get(i)), yMax-textBuffer, 0xFFFFFF);
			}
		}
	}


	private void findPokeloots(EntityPlayer player){

		World world = player.worldObj;
		int xPos = (int)player.posX;
		int yPos = (int)player.posY;
		int zPos = (int)player.posZ;

		int maxX = Config.pokemon_search_radius;
		int maxY = 10;
		int maxZ = Config.pokemon_search_radius;

		for(int i=-maxX;i<=maxX;i++){
			for(int j=-maxZ;j<=maxZ;j++){
				for(int k=-maxY;k<=10;k++){

					int blockX = (int) (xPos+i);
					int blockY = (int) (yPos+k);
					int blockZ = (int) (zPos+j);

					Block block = player.worldObj.getBlock(blockX, blockY, blockZ);

					if(block.getLocalizedName().contains("Chest")){
						pokeloots.add(EnumChatFormatting.GREEN + "Pokeloots "+EnumChatFormatting.WHITE+"at: "+blockX+", "+blockY+", "+blockZ+"!!");
					}
				}
			}
		}
	}
}
