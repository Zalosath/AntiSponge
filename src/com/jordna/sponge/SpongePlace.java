package com.jordna.sponge;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpongePlace implements Listener
{

	private Main main;
	public SpongePlace(Main ins)
	{
		this.main = ins;
	}
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e)
	{
		if (e.getBlock().getType() == Material.SPONGE)
		{
			List<Block> blockList = surveyArea(e.getBlock().getLocation());
			e.getBlock().getLocation().getBlock().setType(Material.SPONGE);
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() 
			{
				@Override
				public void run() 
				{
					for(Block b : blockList)
					{
						e.getBlock().getWorld().getBlockAt(b.getLocation()).setType(Material.STATIONARY_WATER);
					}
				}
			}, 5);
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() 
			{
				@Override
				public void run() 
				{
					e.getBlock().getLocation().getBlock().setType(Material.SPONGE);
				}
			}, 6);
		}
	}
	
	private List<Block> surveyArea(Location center)
	{
		List<Block> blockList = new ArrayList<Block>();
		for (int x = center.getBlockX()-7; x <= center.getBlockX()+7; x++)
		{
			for (int y = center.getBlockY()-7; y <= center.getBlockY()+7; y++)
			{
				for (int z = center.getBlockZ()-7; z <= center.getBlockZ()+7; z++)
				{
					if (center.getWorld().getBlockAt(new Location(center.getWorld(),x,y,z)).getType().equals(Material.STATIONARY_WATER))
					{
						blockList.add(center.getWorld().getBlockAt(new Location(center.getWorld(),x,y,z)));
					}
				}	
			}	
		}
		return blockList;
	}
	
}
