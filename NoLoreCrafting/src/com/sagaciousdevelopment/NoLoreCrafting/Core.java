package com.sagaciousdevelopment.NoLoreCrafting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener{
	private String f;
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		f = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prohibit-message"));
	}
	
	@EventHandler
	public void onCraft(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player)e.getWhoClicked();
			if(p.getOpenInventory()!=null&&p.getOpenInventory().getType().equals(InventoryType.WORKBENCH)||p.getOpenInventory().getType().equals(InventoryType.CRAFTING)) {
				if(!e.getClickedInventory().getType().equals(InventoryType.WORKBENCH)&&!e.getClickedInventory().getType().equals(InventoryType.CRAFTING)) {
					if(e.getCurrentItem().hasItemMeta()) {
						if(e.getCurrentItem().getItemMeta().hasLore()) {
							e.setCancelled(true);
							p.sendMessage(f);
						}
					}
				}
			}
		}
	}

}
