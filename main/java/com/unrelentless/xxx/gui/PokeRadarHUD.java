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

import com.unrelentless.xxx.XxxMod;
import com.unrelentless.xxx.handlers.KeybindHandler;
import com.unrelentless.xxx.lib.Config;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PokeRadarHUD extends Gui{

	private Minecraft mc;
	public static long lastPressed=System.currentTimeMillis();
	ArrayList<String> pokemon = new ArrayList<String>();

	public PokeRadarHUD(Minecraft mc) {
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
			this.mc.fontRenderer.drawString("Pokemon of interest:", 2, 2, 0xFFFFFF);
			findPokemon(mc.thePlayer);
			Iterator iterator = pokemon.iterator();
			while(iterator.hasNext()){
				textBuffer+=10;
				this.mc.fontRenderer.drawString((String)iterator.next(), 2, 2+textBuffer, 0xFFFFFF);
			}
			pokemon.removeAll(pokemon);
			textBuffer = 0;
		}
	}


	private void findPokemon(EntityPlayer player){

		World world = player.worldObj;
		int xPos = (int)player.posX;
		int yPos = (int)player.posY;
		int zPos = (int)player.posZ;

		int maxX = Config.pokemon_search_radius;
		int maxY = 10;
		int maxZ = Config.pokemon_search_radius;

		int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		float closest = (float)Config.pokemon_search_radius;
		Entity thisEntity;
		String entityName;
		for (int l = 0; l < world.loadedEntityList.size(); l++){

			if (((Entity)world.loadedEntityList.get(l)).getDistanceToEntity(player)<closest)
			{
				if (world.loadedEntityList.get(l) instanceof EntityAnimal)
				{
					thisEntity = ((Entity)world.loadedEntityList.get(l));
					int entityPosX = (int)((Entity)world.loadedEntityList.get(l)).posX;
					int entityPosY = (int)((Entity)world.loadedEntityList.get(l)).posY;
					int entityPosZ = (int)((Entity)world.loadedEntityList.get(l)).posZ;	
					NBTTagCompound compound = new NBTTagCompound();
					thisEntity.writeToNBT(compound);

					int hypoPlayer = (int) Math.sqrt(Math.pow(xPos, 2) + Math.pow(zPos, 2));
					int hypoEntity = (int) Math.sqrt(Math.pow(entityPosX, 2) + Math.pow(entityPosZ, 2));

					int absHypo = Math.abs(hypoPlayer - hypoEntity);

					if(compound.getBoolean("IsShiny")){	
						pokemon.add("Shiny " + EnumChatFormatting.GOLD + compound.getString("Name") +EnumChatFormatting.WHITE+" due: "+ checkDir(player, dir, entityPosX, entityPosZ) +
								" ("+ absHypo +" blocks away.)");
					}else if(compound.getShort("BossMode")>0){
						pokemon.add("Boss " + EnumChatFormatting.RED + compound.getString("Name") +EnumChatFormatting.WHITE+" due: "+ checkDir(player, dir, entityPosX, entityPosZ) +
								" ("+ absHypo +" blocks away.)");
					}/*else if(compound.getShort("Growth")==6){
						pokemon.add("Enormous " + EnumChatFormatting.BOLD + compound.getString("Name") +EnumChatFormatting.WHITE+" due: "+ checkDir(player, dir, entityPosX, entityPosZ) +
								"("+entityPosX+","+entityPosY+","+entityPosZ +")");
					}*/
				}
			}
		}
	}

	public String checkDir(EntityPlayer player, int dir, int posX, int posZ){
		switch(dir){
		case 0: //South
			if((int)player.posX > posX && (int)player.posZ < posZ){
				return "South West";
			}else if((int)player.posX < posX && (int)player.posZ > posZ){
				return "North East";
			}else if((int)player.posX < posX && (int)player.posZ < posZ){
				return "South East";
			}else if((int)player.posX > posX && (int)player.posZ > posZ){
				return "North West";
			}
		case 1: //West
			if((int)player.posX > posX && (int)player.posZ < posZ){
				return "South West";
			}else if((int)player.posX < posX && (int)player.posZ > posZ){
				return "North East";
			}else if((int)player.posX < posX && (int)player.posZ < posZ){
				return "South East";
			}else if((int)player.posX > posX && (int)player.posZ > posZ){
				return "North West";
			}
		case 2: //North
			if((int)player.posX < posX && (int)player.posZ > posZ){
				return "North East";
			}else if((int)player.posX > posX && (int)player.posZ < posZ){
				return "South West";
			}else if((int)player.posX < posX && (int)player.posZ < posZ){
				return "South East";
			}else if((int)player.posX > posX && (int)player.posZ > posZ){
				return "North West";
			}
		case 3: //East
			if((int)player.posX > posX && (int)player.posZ < posZ){
				return "South West";
			}else if((int)player.posX < posX && (int)player.posZ > posZ){
				return "North East";
			}else if((int)player.posX < posX && (int)player.posZ < posZ){
				return "South East";
			}else if((int)player.posX > posX && (int)player.posZ > posZ){
				return "North West";
			}
		default:
		}
		return "Straight Ahead";
	}
}
