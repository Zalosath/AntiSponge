package com.jordna.sponge;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new SpongePlace(this), this);
	}
	
}
